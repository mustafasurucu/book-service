package com.etiya.cb.demo.service;

import com.etiya.cb.demo.client.BookAuthorServiceClient;
import com.etiya.cb.demo.client.BookNameServiceClient;
import com.etiya.cb.demo.model.response.BookResponse;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookNameServiceClient bookNameServiceClient;
    private final BookAuthorServiceClient bookAuthorServiceClient;

    public BookService(BookNameServiceClient bookNameServiceClient,
                       BookAuthorServiceClient bookAuthorServiceClient) {
        this.bookNameServiceClient = bookNameServiceClient;
        this.bookAuthorServiceClient = bookAuthorServiceClient;
    }

    public BookResponse getBook() {
        String bookName = bookNameServiceClient.getBookName();
        String bookAuthor = bookAuthorServiceClient.getBookAuthor();
        return new BookResponse(bookName, bookAuthor);
    }
}
