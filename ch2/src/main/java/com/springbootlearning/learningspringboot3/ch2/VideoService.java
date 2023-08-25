package com.springbootlearning.learningspringboot3.ch2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VideoService {
    
    List<Video> videos = List.of(
        new Video("Need help with SB 3"),
        new Video("Don't do this to your own code"),
        new Video("SECRETS to fixe broken code")
    );

    public List<Video> getVideos() {
        return videos;
    }

    public Video create(Video newVideo) {
        List<Video> extend = new ArrayList<>(videos);
        extend.add(newVideo);
        this.videos = List.copyOf(extend);
        return newVideo;
    }
}
