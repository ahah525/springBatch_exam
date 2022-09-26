package com.ll.exam.app_2022_09_22.app.cart.entity;

import com.ll.exam.app_2022_09_22.app.base.entity.BaseEntity;
import com.ll.exam.app_2022_09_22.app.member.entity.Member;
import com.ll.exam.app_2022_09_22.app.product.entity.ProductOption;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class CartItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;                  // 장바구니 담은 회원

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOption productOption;    // 장바구니 담은 상품 옵션

    private int quantity;;                  // 장바구니 상품 옵션 수량
}
