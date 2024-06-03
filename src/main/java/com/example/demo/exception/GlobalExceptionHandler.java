package com.example.demo.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestNullExistException.class)
    public ResponseEntity<ErrorResponse> RequestNullExistException() {
        ErrorResponse response = new ErrorResponse(ErrorCode.REQUEST_NULL_VALUE);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(MemberNotExistException.class)
    public ResponseEntity<ErrorResponse> MemberNotExistException() {
        ErrorResponse response = new ErrorResponse(ErrorCode.MEMBER_NOT_EXIST);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(BoardNotExistException.class)
    public ResponseEntity<ErrorResponse> BoardNotExistException() {
        ErrorResponse response = new ErrorResponse(ErrorCode.BOARD_NOT_EXIST);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ErrorResponse> ArticleNotFoundException() {
        ErrorResponse response = new ErrorResponse(ErrorCode.ARTICLE_NOT_EXIST);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> MemberNotFoundException() {
        ErrorResponse response = new ErrorResponse(ErrorCode.MEMBER_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<ErrorResponse> BoardNotFoundException() {
        ErrorResponse response = new ErrorResponse(ErrorCode.BOARD_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(AlreadyHasEmailException.class)
    public ResponseEntity<ErrorResponse> AlreadyHasEmailException() {
        ErrorResponse response = new ErrorResponse(ErrorCode.EMAIL_EXIST);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(MemberHasArticleException.class)
    public ResponseEntity<ErrorResponse> MemberHasArticleException() {
        ErrorResponse response = new ErrorResponse(ErrorCode.MEMBER_ARTICLE_EXIST);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(BoardHasArticleException.class)
    public ResponseEntity<ErrorResponse> BoardHasArticleException() {
        ErrorResponse response = new ErrorResponse(ErrorCode.BOARD_ARTICLE_EXIST);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
    }
}
