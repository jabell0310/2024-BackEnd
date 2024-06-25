package com.example.demo.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Member;

@Repository
public class MemberRepositoryJpa implements MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Member insert(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    @Transactional
    public List<Member> findAll() {
        String jpql = "SELECT m FROM Member m";
        return em.createQuery(jpql, Member.class).getResultList();
    }

    @Override
    @Transactional
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    @Transactional
    public Member update(Member member) {
        em.merge(member);
        return member;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.remove(findById(id));
    }
}
