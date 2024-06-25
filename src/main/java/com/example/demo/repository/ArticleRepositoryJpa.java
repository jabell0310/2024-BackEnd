package com.example.demo.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Article;

@Repository
public class ArticleRepositoryJpa implements ArticleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Article insert(Article article) {
        em.persist(article);
        return article;
    }

    @Override
    @Transactional
    public List<Article> findAll() {
        String jpql = "SELECT a FROM Article a";
        return em.createQuery(jpql, Article.class).getResultList();
    }

    @Override
    @Transactional
    public List<Article> findAllByBoardId(Long boardId) {
        String jpql = "SELECT a FROM Article a WHERE a.boardId = :boardId";
        return em.createQuery(jpql, Article.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Article> findAllByMemberId(Long memberId) {
        String jpql = "SELECT a FROM Article a WHERE a.authorId = :memberId";
        return em.createQuery(jpql, Article.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    @Transactional
    public Article findById(Long id) {
        return em.find(Article.class, id);
    }

    @Override
    @Transactional
    public Article update(Article article) {
        em.merge(article);
        return article;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.remove(findById(id));
    }
}
