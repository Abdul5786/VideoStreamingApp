package com.Abdul.VideoStreamingApp.services;


import com.Abdul.VideoStreamingApp.entities.Video;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    public Video addVideo(Video video, MultipartFile file);

    public Video getVideoById(String videoId);

    public Video getVideoByTitle(String title);
}
