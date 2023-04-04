package com.binary.session.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class User {
    private int id;
    private String email;
    private LocalDateTime createdDate;
}
