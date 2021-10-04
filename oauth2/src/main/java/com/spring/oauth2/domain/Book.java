package com.spring.oauth2.domain;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int id;
    private String name;
    private String publisher;
    private String author;
    private String isbn;
    private Date year;
    private String createDate;
    private Date updateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(createDate, book.createDate) && Objects.equals(updateDate, book.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, updateDate);
    }
}
