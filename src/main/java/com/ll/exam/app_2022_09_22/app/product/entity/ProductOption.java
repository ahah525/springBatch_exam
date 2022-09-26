package com.ll.exam.app_2022_09_22.app.product.entity;

import com.ll.exam.app_2022_09_22.app.base.entity.BaseEntity;
import com.ll.exam.app_2022_09_22.app.product.entity.Product;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ProductOption extends BaseEntity {
    private String color;       // 색상
    private String size;        // 사이즈
    private String displayColor;       // 표시 색상
    private String displaySize;        // 표시 사이즈

    private int price;                  // 최종 소비자가(옵션 가격을 포함)
    private int wholesalePrice;         // 옵션 도매가

    @ToString.Exclude
    @ManyToOne(fetch = LAZY)
    private Product product;    // 해당 옵션의 관련 상품

    private boolean isSoldOut; // 사입처에서의 품절여부
    private int stockQuantity; // 쇼핑몰에서 보유한 물건 개수

    public ProductOption(String color, String size) {
        this.color = color;
        this.displayColor = color;
        this.size = size;
        this.displaySize = size;
    }

    // 해당 개수만큼 상품 옵션 주문 가능한지
    public boolean isOrderable(int quantity) {
        // 품절이 아닌 경우
        if(!isSoldOut) {
            return true;
        }
        // 품절이 아니고 재고 >= 주문수량 인 경우
        return getStockQuantity() >= quantity;
    }
}