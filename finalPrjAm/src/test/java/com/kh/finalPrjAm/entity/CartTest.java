package com.kh.finalPrjAm.entity;

import com.kh.finalPrjAm.repository.CartRepository;
import com.kh.finalPrjAm.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 스프링 컨텍스트를 로드하여 테스트 환경을 설정
@SpringBootTest
// 데이터베이스의 논리적인 작업 단위, 모두 성공하는 경우가 아니면 롤백 (테스트에서는 자동 롤백)
@Transactional
// 로깅 데이터를 처리하기 위해 사용
@Slf4j
@TestPropertySource(locations="classpath:application-test.properties")
class CartTest {
    @Autowired // 스프링 컨테이너에서 해당 빈에 해당하는 의존성을 주입 받음
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext // EntityManager를 사용하기 위해
    EntityManager em;

    // 회원 엔티티 생성
    public Member createMemberInfo() {
        Member member = new Member();
        member.setUserId("jks2024");
        member.setPassword("1234");
        member.setName("곰돌이사육사");
        member.setEmail("jks2024@gmail.com");
        member.setJoinTime(LocalDateTime.now());
        return member;
    }
    @Test
    @DisplayName("장바구니 회원 매핑 조회 테스트")
    public void findCartAndMemberTest(){
        Member member = createMemberInfo();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cart.setCartName("테스트용 장바구니");
        cartRepository.save(cart);

        em.flush(); // 실제 데이터베이스에 반영
        em.clear();


        Cart saveCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new);
        System.out.println("회원 userID : + " + member.getUserId());
        System.out.println("장바구니 userID : " + saveCart.getMember().getUserId());
        assertEquals(saveCart.getMember().getUserId(), member.getUserId());
    }
}