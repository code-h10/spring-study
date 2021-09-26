package com.spring.common.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Response<T> {

    private int code;
    private boolean status;
    private String message;
    private T data;


    public static <T> Response<T> success(int code, boolean status, String message) {
        return Response.<T>builder()
                .code(code)
                .status(status)
                .message(message)
                .build();
    }

    public static <T> Response<T> success(int code, boolean status, String message, T data) {
        return Response.<T>builder()
                .code(code)
                .status(status)
                .message(message)
                .data(data)
                .build();
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
