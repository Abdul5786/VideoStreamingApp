package com.Abdul.VideoStreamingApp.controllers;

import com.Abdul.VideoStreamingApp.entities.Videos;
import com.Abdul.VideoStreamingApp.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class VideosControllers
{

    @Autowired
    private VideoService  videoService;


    public ResponseEntity<?> create(
            @RequestParam("file")MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description
            )

    {
        Videos videos = new Videos();
        videos.setVideoId(UUID.randomUUID().toString());
        videos.setTitle(title);
        videos.setDescription(description);

        videoService.addVideo(videos,file);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
