package com.kh.finalPrjAm.repository;

import com.kh.finalPrjAm.constant.ItemSellStatus;
import com.kh.finalPrjAm.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest // 단위 테스트 및 통합 테스트를 위해 스프링 부트에서 제공하는 어노테이션
@TestPropertySource(locations="classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired // 의존을 주입 받음
    ItemRepository itemRepository;


    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        for(int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000);
            item.setItemDetail("테스트 상품 상세 설명");
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNm("테스트상품5");
        for (Item e : itemList) {
            System.out.println("결과 : " + e.toString());
        }
    }

    @Test
    @DisplayName("상품명 or 상품상세설명")
    public void findByItemNmOrItemDetailTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트상품5", "테스트 상품 상세 설명7");
        for (Item e : itemList) {
            System.out.println("결과 : " + e.toString());
        }
    }

    @Test
    @DisplayName("입력 받은 가격보다 적은 상품 조회 테스트")
    public void findByPriceLessThanTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(10055);
        for (Item e : itemList) {
            System.out.println("결과 : " + e.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차 순 조회")
    public void findByPriceLessThanTestOrderByPriceDesc() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10076);
        for (Item e : itemList) {
            System.out.println("결과 : " + e.toString());
        }
    }

    @Test
    @DisplayName("가격범위로 상품 조회 테스트")
    public void findByPriceBetweenTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceBetween(10015, 10075);
        for (Item e : itemList) {
            System.out.println("결과 : " + e.toString());
        }
    }

    @Test
    @DisplayName("상품이름에 대한 부분 문자열 검색")
    public void findByItemNmContainingTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmContaining("테스트");
        for (Item e : itemList) {
            System.out.println("결과 : " + e.toString());
        }
    }
    @Test
    @DisplayName("@Query 를 이용한 상품 조회 테스트")
    public void findByItemDetailTest() {
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetail("설명");
        for(Item e : itemList) {
            System.out.println("결과 : " + e.toString());
        }
    }
}