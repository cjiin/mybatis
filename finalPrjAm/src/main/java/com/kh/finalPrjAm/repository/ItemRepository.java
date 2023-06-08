package com.kh.finalPrjAm.repository;

import com.kh.finalPrjAm.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository는 첫 번째에는 엔티티 타입 클래스를 넣어주고, 두번쨰는 기본키
// 기본적인 CRUD 및 페이징 처리를 위한 메소드는 정의 되어 있음
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findItemNm(String itemNm);

    // or 조건 처리하기
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    //LessThan : 매개변수로 전달 받은 값보다 적은 상품 조회
    List<Item> findByPriceLessThan(Integer price);

    //OrderBy로 정렬 : OrderBy + 속성명 + Acs or Desc
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price) ;

}
