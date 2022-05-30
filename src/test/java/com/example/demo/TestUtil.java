package com.example.demo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestUtil {

    @SneakyThrows
    public static String readFileByPath(String path) {
        URI uri = TestUtil.class.getClassLoader().getResource(path).toURI();
        return new String(Files.readAllBytes(Paths.get(uri)), StandardCharsets.UTF_8);
    }

}
