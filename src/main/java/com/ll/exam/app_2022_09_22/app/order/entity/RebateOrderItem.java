package com.ll.exam.app_2022_09_22.app.order.entity;

import com.ll.exam.app_2022_09_22.app.base.entity.BaseEntity;
import com.ll.exam.app_2022_09_22.app.product.entity.ProductOption;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class RebateOrderItem extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
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

    // 추가 컬럼
    // 상품
    private String productName;
    // 상품옵션
    private String productOptionColor;
    private String productOptionSize;
    private String productOptionDisplayColor;
    private String productOptionDisplaySize;

    // 주문 품목
    private LocalDateTime orderItemCreateDate;


    public RebateOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
        order = orderItem.getOrder();
        productOption = orderItem.getProductOption();
        quantity = orderItem.getQuantity();
        price = orderItem.getPrice();
        salePrice = orderItem.getSalePrice();
        wholesalePrice = orderItem.getWholesalePrice();
        pgFee = orderItem.getPgFee();
        payPrice = orderItem.getPayPrice();
        refundPrice = orderItem.getRefundPrice();
        refundQuantity = orderItem.getRefundQuantity();
        isPaid = orderItem.isPaid();

        // 상품
        productName = orderItem.getProductOption().getProduct().getName();
        // 상품옵션
        productOptionColor = orderItem.getProductOption().getColor();
        productOptionSize = orderItem.getProductOption().getSize();
        productOptionDisplayColor = orderItem.getProductOption().getDisplayColor();
        productOptionDisplaySize = orderItem.getProductOption().getDisplaySize();
        // 주문품목
        orderItemCreateDate = orderItem.getCreateDate();
    }
}
