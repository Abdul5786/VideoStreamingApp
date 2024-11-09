package com.Abdul.VideoStreamingApp.services;

import com.Abdul.VideoStreamingApp.Repositories.VideoRepository;
import com.Abdul.VideoStreamingApp.entities.Video;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    // Hardcoded directory paths
    private final String DIR = "C:/videos";  // Example hardcoded directory for storing videos
    private final String HSL_DIR = "C:/hls";  // Example hardcoded directory for HLS files

    @PostConstruct
    public void init() {
        // Ensure directories exist for video storage
        createDirectoryIfNotExists(DIR);
        createDirectoryIfNotExists(HSL_DIR);
    }

    private void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directoryPath);
            } else {
                System.err.println("Failed to create directory: " + directoryPath);
            }
        } else {
            System.out.println("Directory already exists: " + directoryPath);
        }
    }

    @Override
    public Video addVideo(Video video, MultipartFile file) {
        try {
            // Original file name and content type
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            if (filename == null) {
                throw new RuntimeException("Filename is null");
            }

            // Clean file name and prepare folder path
            String cleanFileName = StringUtils.cleanPath(filename);
            String cleanFolder = StringUtils.cleanPath(DIR);
            Path path = Paths.get(cleanFolder, cleanFileName);

            // Log content type and path for debugging
            System.out.println("Content Type: " + contentType);
            System.out.println("Saving file to: " + path);

            // Copy the file to the designated folder
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            }

            // Save video metadata to the database
            video.setContentType(contentType);
            video.setFilePath(path.toString());
            Video savedVideo = videoRepository.save(video);

            // Optional: Process the video if needed (e.g., encoding)
            // processVideo(savedVideo.getVideoId());

            return savedVideo;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error in processing video", e);
        }
    }

    @Override
    public Video getVideoById(String videoId) {
        return videoRepository.findById(videoId).orElse(null);
    }

    @Override
    public Video getVideoByTitle(String title) {
//        return videoRepository.findByTitle(title);
        return null;
    }
}
