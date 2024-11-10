package com.Abdul.VideoStreamingApp.controllers;

import com.Abdul.VideoStreamingApp.entities.Video;
import com.Abdul.VideoStreamingApp.payloads.CustomMessage;
import com.Abdul.VideoStreamingApp.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/videos")
@CrossOrigin("*")
public class VideosControllers
{

    @Autowired
    private VideoService  videoService;


    @PostMapping(value = "/add")
    public ResponseEntity<?> create(
            @RequestParam("file")MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description
            )

    {
        Video videos = new Video();
        videos.setVideoId(UUID.randomUUID().toString());
        videos.setTitle(title);
        videos.setDescription(description);

        Video savedVideo = videoService.addVideo(videos, file);

        if (savedVideo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(videos);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CustomMessage.builder().message("Video not uploaded ").success(false).build());
        }
    }

    @GetMapping("/stream/{videoId}")
    public ResponseEntity<Resource> getVideoById(@PathVariable String videoId) {
        Video video = videoService.getVideoById(videoId);
        String contentType = video.getContentType();
        String filePath = video.getFilePath();
        FileSystemResource resource = new FileSystemResource(filePath);

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }


}
