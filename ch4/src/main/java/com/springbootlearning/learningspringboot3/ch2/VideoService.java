package com.springbootlearning.learningspringboot3.ch2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;

@Service
public class VideoService {

    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
      }

    public List<VideoEntity> getVideos() {
        return repository.findAll();
      }
    

    public List<VideoEntity> search(VideoSearch videoSearch) {
        if (StringUtils.hasText(videoSearch.description())) {
            return repository.findByNameContainsOrDescriptionContainsAllIgnoreCase(
                videoSearch.name(), videoSearch.description()
             );
        }
        if (StringUtils.hasText(videoSearch.name())) {
            return repository.findByNameContainsIgnoreCase(videoSearch.name());
        };
        if (StringUtils.hasText(videoSearch.description())) {
            return repository.findByDescriptionContainsIgnoreCase(videoSearch.description());
        };

        return Collections.emptyList();
        }
}
