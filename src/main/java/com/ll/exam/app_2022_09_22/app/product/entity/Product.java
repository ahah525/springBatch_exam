package com.ll.exam.app_2022_09_22.app.product.entity;

import com.ll.exam.app_2022_09_22.app.base.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Product extends BaseEntity {
    private int price;          // 소비자가
    private int wholesalePrice; // 도매가(동대문 사입가격)
    private String name;
    private String makerShopName;   // 사입처명(원래는 사입처 table을 따로 만들어야 함)
    private boolean isSoldOut;      // 품절여부(관련 옵션들이 모두 판매불가 상태일 때)

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOption> productOptions = new ArrayList<>();

    public void addOption(ProductOption option) {
        option.setProduct(this);
        option.setPrice(getPrice());
        option.setWholesalePrice(getWholesalePrice());

        productOptions.add(option);
        System.out.println("option.getColor() = " + option.getColor());
    }
}