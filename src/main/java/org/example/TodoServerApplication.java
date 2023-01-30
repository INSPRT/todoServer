package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class TodoServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoServerApplication.class, args);

    }
}