package com.example.demo.repository;

import com.example.demo.domain.Article;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ArticleRepositoryJpaTest {

    private final ArticleRepositoryJpa articleRepository;

    @Autowired
    public ArticleRepositoryJpaTest(ArticleRepositoryJpa articleRepository) {
        this.articleRepository = articleRepository;
    }

    private Article article;

    @BeforeEach
    void setUp() {
        article = new Article(null, 1L, 1L, "New Title", "New Content", LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    @DisplayName("게시물이 생성되는지 확인하는 테스트")
    void insert() {
        //Given
        Article savedArticle = articleRepository.insert(article);
        //When
        assertNotNull(savedArticle.getId());
        //Then
        assertEquals(article.getTitle(), savedArticle.getTitle());
        assertEquals(article.getContent(), savedArticle.getContent());
        assertNotNull(savedArticle.getCreatedAt());
        assertNotNull(savedArticle.getModifiedAt());
    }

    @Test
    @DisplayName("게시물이 모두 찾아지는지 확인하는 테스트")
    void findAll() {
        //Given
        List<Article> articles = articleRepository.findAll();
        //When
        assertFalse(articles.isEmpty());
        //Then
        assertEquals(16, articles.size());
    }

    @Test
    @DisplayName("특정 게시물이 찾아지는지 확인하는 테스트")
    void findById() {
        //Given
        Article foundArticle = articleRepository.findById(1L);
        //When
        assertNotNull(foundArticle);
        //Then
        assertEquals(1L, foundArticle.getId());
        assertEquals("자그레우스", foundArticle.getTitle());
        assertEquals("Hades의 주인공", foundArticle.getContent());
    }

    @Test
    @DisplayName("특정 게시물이 수정되는지 확인하는 테스트")
    void update() {
        //Given
        Article savedArticle = articleRepository.insert(article);
        //When
        savedArticle.update(2L, "Updated Title", "Updated Content");
        Article updatedArticle = articleRepository.update(savedArticle);
        //Then
        assertEquals("Updated Title", updatedArticle.getTitle());
        assertEquals("Updated Content", updatedArticle.getContent());
        assertEquals(2L, updatedArticle.getBoardId());
        assertNotNull(updatedArticle.getModifiedAt());
        assertTrue(updatedArticle.getModifiedAt().isAfter(updatedArticle.getCreatedAt()));
    }

    @Test
    @DisplayName("특정 게시물이 삭제되는지 확인하는 테스트")
    void deleteById() {
        //Given
        Article savedArticle = articleRepository.insert(article);
        //When
        articleRepository.deleteById(savedArticle.getId());
        Article deletedArticle = articleRepository.findById(savedArticle.getId());
        //Then
        assertNull(deletedArticle);
    }
}