package com.example.demo.services;

import feign.*;

import java.util.Map;

public interface StanderInvoke {
    @RequestLine("POST {path}")
    @Body("{body}")
    @Headers("Content-Type:{contentType}")
    String post(@Param("path") String path, @Param("body") String body, @Param("contentType") String contentType);

    @RequestLine("POST {path}")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    String postWithForm(@Param("path") String path, Map<String, ?> param);

    @RequestLine("POST {path}")
    @Body("{body}")
    @Headers("Content-Type: application/json")
    String postWithJson(@Param("path") String path, @Param("body") String body);

    @RequestLine("GET {path}")
    String getWithMap(@Param("path") String path, @QueryMap Map<String, ?> param);
}
