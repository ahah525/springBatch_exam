package com.ll.exam.app_2022_09_22.app.product.service;

import com.ll.exam.app_2022_09_22.app.product.entity.ProductOption;
import com.ll.exam.app_2022_09_22.app.product.entity.Product;
import com.ll.exam.app_2022_09_22.app.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product create(String name, int price, int wholesalePrice, String makerShopName, List<ProductOption> options) {
        Product product = Product.builder()
                .name(name)
                .price(price)
                .wholesalePrice(wholesalePrice)
                .makerShopName(makerShopName)
                .build();

        for(ProductOption productOption : options) {
            product.addOption(productOption);
        }

        productRepository.save(product);

        return product;
    }
}
