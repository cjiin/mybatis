package com.kh.finalPrjAm.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="cart_item")
public class CartItem {
    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne // 다대일
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne // 하나의 상품은 여러 장바구니의 상품으로 포함될 수 있으므로 다대일 관계 매핑
    @JoinColumn(name = "item_id") // 장바구니에 담을 상품 정보를 알아야 하므로 상품 엔티티를 매핑
    private Item item;

    private int count; // 상품을 몇개 담을지 지정
}
