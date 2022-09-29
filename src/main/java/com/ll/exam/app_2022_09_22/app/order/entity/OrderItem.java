package com.ll.exam.app_2022_09_22.app.order.entity;

import com.ll.exam.app_2022_09_22.app.base.entity.BaseEntity;
import com.ll.exam.app_2022_09_22.app.product.entity.ProductOption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class OrderItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Order order;

    private LocalDateTime payDate;  // 결제 일시

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOption productOption;

    private int quantity;

    // 주문 당시 가격 정보
    private int price;                  // 권장 판매가
    private int salePrice;              // 실제 판매가
    private int wholesalePrice;         // 도매가
    private int pgFee;                  // 결제 대행 수수료
    private int payPrice;               // 결제 금액
    private int refundPrice;            // 환불 가격
    private int refundQuantity;         // 환불 수량
    private boolean isPaid;             // 결제 여부


    public OrderItem(ProductOption productOption, int quantity) {
        this.productOption = productOption;
        this.quantity = quantity;
        this.price = productOption.getPrice();
        this.salePrice = productOption.getSalePrice();
        this.wholesalePrice = productOption.getWholesalePrice();
    }

    public int calculatePayPrice() {
        return salePrice * quantity;
    }

    public void setPaymentDone() {
        this.pgFee = 0;
        this.payPrice = calculatePayPrice();
        this.isPaid = true;
        this.payDate = LocalDateTime.now();
    }

    public void setRefundDone() {
        // 이미 모든 수량을 환불처리했다면 리턴
        if(refundQuantity == quantity) {
            return;
        }
        this.refundQuantity = quantity;
        this.refundPrice = payPrice;
    }
}
