package com.prudential.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> {
    private String code;
    private String message;
    private T data;

    public static CustomResponse<Object> ok() {
        return new CustomResponse<>("200", "OK", null);
    }

    public static CustomResponse<Object> ok(String message) {
        return new CustomResponse<>("200", "OK", message);
    }

    public static CustomResponse<Object> ok(Object data) {
        return new CustomResponse<>("200", "OK", data);
    }
}
