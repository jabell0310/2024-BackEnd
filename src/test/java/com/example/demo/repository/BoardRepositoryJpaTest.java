package com.example.demo.repository;

import com.example.demo.domain.Board;

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
class BoardRepositoryJpaTest {

    private final BoardRepositoryJpa boardRepository;

    @Autowired
    public BoardRepositoryJpaTest(BoardRepositoryJpa boardRepository) {
        this.boardRepository = boardRepository;
    }

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(null,"new board");
    }

    @Test
    @DisplayName("게시판이 생성되는지 확인하는 테스트")
    void insert() {
        //Given
        Board savedBoard = boardRepository.insert(board);
        //When
        assertNotNull(savedBoard.getId());
        //Then
        assertEquals(board.getName(), savedBoard.getName());
    }

    @Test
    @DisplayName("게시판이 모두 찾아지는지 확인하는 테스트")
    void findAll() {
        //Given
        List<Board> boards = boardRepository.findAll();
        //When
        assertFalse(boards.isEmpty());
        //Then
        assertEquals(5, boards.size());
    }

    @Test
    @DisplayName("특정 게시판이 찾아지는지 확인하는 테스트")
    void findById() {
        //Given
        Board foundBoard = boardRepository.findById(1L);
        //When
        assertNotNull(foundBoard);
        //Then
        assertEquals(1L, foundBoard.getId());
        assertEquals("하데스", foundBoard.getName());
    }

    @Test
    @DisplayName("특정 게시판이 수정되는지 확인하는 테스트")
    void update() {
        //Given
        Board savedBoard = boardRepository.insert(board);
        //When
        savedBoard.update("수정된 게시판1");
        Board updatedBoard = boardRepository.update(savedBoard);
        //Then
        assertEquals("수정된 게시판1", updatedBoard.getName());
    }

    @Test
    @DisplayName("특정 게시판이 삭제되는지 확인하는 테스트")
    void deleteById() {
        //Given
        Board savedBoard = boardRepository.insert(board);
        //When
        boardRepository.deleteById(savedBoard.getId());
        Board deletedBoard = boardRepository.findById(savedBoard.getId());
        //Then
        assertNull(deletedBoard);
    }
}