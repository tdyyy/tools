package com.example.demo.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Employee {
    private String name;
    private int age;
    private Map<String,String> work;
}
