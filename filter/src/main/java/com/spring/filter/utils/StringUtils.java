package com.spring.filter.utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class StringUtils {

    public static byte[] escape(byte[] values) {
        String str = new String(values);
        return str
                .replace(">", "@@")
                .replace("<", "$$")
                .replace("&", "@@")
                .replace("(", "##")
                .replace(")", "$$")
                .getBytes(StandardCharsets.UTF_8);
    }

    public static String escape(String values) {
        return values
            .replace(">", "@@")
            .replace("<", "$$")
            .replace("&", "@@")
            .replace("(", "##")
            .replace(")", "$$");

    }

    public static byte[] unescape(byte[] values) {
        String str = new String(values);
        System.out.println(Arrays.toString(values));
        return str
            .replace("@@", ">")
            .replace("$$", "<")
            .replace("@@", "&")
            .replace("**", "(")
            .replace("%%", ")")
            .getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] unescape(String values) {
        return values
            .replace("@@", ">")
            .replace("$$", "<")
            .replace("@@", "&")
            .replace("**", "(")
            .replace("%%", ")")
            .getBytes(StandardCharsets.UTF_8);
    }

}
