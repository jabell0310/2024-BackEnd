package com.example.demo.repository;

import com.example.demo.domain.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryJpaTest {

    private final MemberRepositoryJpa memberRepository;

    @Autowired
    public MemberRepositoryJpaTest(MemberRepositoryJpa memberRepository) {
        this.memberRepository = memberRepository;
    }

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("user7", "member7@gmail.com", "password7");
    }

    @Test
    @DisplayName("회원이 생성되는지 확인하는 테스트")
    void insert() {
        //Given
        Member savedMember = memberRepository.insert(member);
        //When
        assertNotNull(savedMember.getId());
        //Then
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getPassword(), savedMember.getPassword());
    }

    @Test
    @DisplayName("회원이 모두 찾아지는지 확인하는 테스트")
    void findAll() {
        //Given
        List<Member> members = memberRepository.findAll();
        //When
        assertFalse(members.isEmpty());
        //Then
        assertEquals(6, members.size());
    }

    @Test
    @DisplayName("특정 회원이 찾아지는지 확인하는 테스트")
    void findById() {
        //Given
        Member foundMember = memberRepository.findById(1L);
        //When
        assertNotNull(foundMember);
        //Then
        assertEquals(1L, foundMember.getId());
        assertEquals("user1", foundMember.getName());
        assertEquals("user1@gmail.com", foundMember.getEmail());
        assertEquals("password1", foundMember.getPassword());
    }

    @Test
    @DisplayName("특정 회원이 수정되는지 확인하는 테스트")
    void update() {
        //Given
        Member savedMember = memberRepository.insert(member);
        //When
        savedMember.update("User1", "User1@gmail.com");
        Member updatedMember = memberRepository.update(savedMember);
        //Then
        assertEquals("User1", updatedMember.getName());
        assertEquals("User1@gmail.com", updatedMember.getEmail());
    }

    @Test
    @DisplayName("특정 게시판이 삭제되는지 확인하는 테스트")
    void deleteById() {
        //Given
        Member savedMember = memberRepository.insert(member);
        //When
        memberRepository.deleteById(savedMember.getId());
        Member deletedMember = memberRepository.findById(savedMember.getId());
        //Then
        assertNull(deletedMember);
    }
}