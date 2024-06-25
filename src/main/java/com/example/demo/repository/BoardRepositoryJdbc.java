package com.example.demo.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Board;

@Repository
public class BoardRepositoryJdbc implements BoardRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Board insert(Board board) {
        em.persist(board);
        return board;
    }

    @Override
    @Transactional
    public List<Board> findAll() {
        String jpql = "SELECT b from Board b";
        return em.createQuery(jpql, Board.class).getResultList();
    }

    @Override
    @Transactional
    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    @Override
    @Transactional
    public Board update(Board board) {
        em.merge(board);
        return board;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.remove(findById(id));
    }
}
