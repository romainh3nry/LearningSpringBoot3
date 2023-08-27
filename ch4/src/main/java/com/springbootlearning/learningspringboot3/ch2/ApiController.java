package com.springbootlearning.learningspringboot3.ch2;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private final VideoService videoService;

    public ApiController(VideoService videoService) {
        this.videoService = videoService;
    }
}