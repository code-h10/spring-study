package com.spring.oauth2.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BookDto {

    private int id;
    private String name;
    private String publisher;
    private String author;
    private String isbn;
    private Date year;
    private String create_date;
    private Date update_date;
}
