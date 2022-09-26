package com.ll.exam.app_2022_09_22.app.order.service;

import com.ll.exam.app_2022_09_22.app.cart.entity.CartItem;
import com.ll.exam.app_2022_09_22.app.cart.service.CartService;
import com.ll.exam.app_2022_09_22.app.member.entity.Member;
import com.ll.exam.app_2022_09_22.app.order.entity.Order;
import com.ll.exam.app_2022_09_22.app.order.entity.OrderItem;
import com.ll.exam.app_2022_09_22.app.order.repository.OrderRepository;
import com.ll.exam.app_2022_09_22.app.product.entity.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createFromCart(Member member) {
        // 해당 회원의 장바구니 아이템을 모두 가져옴
        List<CartItem> cartItems = cartService.getItemsByMember(member);
        List<OrderItem> orderItems = new ArrayList<>(); // 주문 아이템 목록

        // 만약 특정 장바구니의 상품옵션이 판매불가이면 삭제
        // 만약 특정 장바구니의 상품옵션이 판매가능이면 주문품목으로 옮긴 후 삭제
        for(CartItem cartItem : cartItems) {
            ProductOption productOption = cartItem.getProductOption();

            // 해당 상품옵션 주문가능한 경우
            if(productOption.isOrderable(cartItem.getQuantity())) {
                orderItems.add(new OrderItem(productOption, cartItem.getQuantity()));
            }
            cartService.deleteItem(cartItem);
        }

        return create(member, orderItems);
    }

    @Transactional
    public Order create(Member member, List<OrderItem> orderItems) {
        Order order = Order.builder()
                .member(member)
                .orderItems(orderItems)
                .build();

        for(OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        orderRepository.save(order);

        return order;
    }
}
