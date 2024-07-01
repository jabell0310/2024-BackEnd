package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ArticleRepository articleRepository;

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board("Test Board");
    }

    @Test
    @Transactional
    void testCascadePersist() {
        Article article1 = new Article(null, 1L, 1L, "Title1", "Content1", LocalDateTime.now(), LocalDateTime.now());
        Article article2 = new Article(null, 2L, 1L, "Title2", "Content2", LocalDateTime.now(), LocalDateTime.now());
        board.getArticles().add(article1);
        board.getArticles().add(article2);
        article1.setBoard(board);
        article2.setBoard(board);

        boardRepository.save(board);

        List<Article> articles = articleRepository.findAll();

        assertThat(articles).hasSize(2);
        assertThat(articles.get(0).getBoard().getName()).isEqualTo("Test Board");
        assertThat(articles.get(1).getBoard().getName()).isEqualTo("Test Board");
    }

    @Test
    @Transactional
    void testCascadeUpdate() {
        Article article = new Article(null, 1L, 1L, "Title", "Content", LocalDateTime.now(), LocalDateTime.now());
        board.getArticles().add(article);
        article.setBoard(board);

        boardRepository.save(board);

        board.setName("Updated Board");
        article.setTitle("Updated Title");
        boardRepository.save(board);

        Board updatedBoard = boardRepository.findById(board.getId()).get();
        Article updatedArticle = articleRepository.findById(article.getId()).get();

        assertThat(updatedBoard.getName()).isEqualTo("Updated Board");
        assertThat(updatedArticle.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    @Transactional
    void testCascadeRemove() {
        Article article = new Article(null, 1L, 1L, "Title", "Content", LocalDateTime.now(), LocalDateTime.now());
        board.getArticles().add(article);
        article.setBoard(board);

        boardRepository.save(board);

        boardRepository.delete(board);

        List<Article> articles = articleRepository.findAll();
        assertThat(articles).isEmpty();
    }
}
