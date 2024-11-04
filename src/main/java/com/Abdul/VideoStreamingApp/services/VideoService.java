package com.Abdul.VideoStreamingApp.services;


import com.Abdul.VideoStreamingApp.entities.Videos;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    public void addVideo(Videos video, MultipartFile file);

    public Videos getVideoById(String videoId);

    public Videos getVideoByTitle(String title);
}
