package com.Abdul.VideoStreamingApp.services;

import com.Abdul.VideoStreamingApp.Repositories.VideoRepository;
import com.Abdul.VideoStreamingApp.entities.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoServiceImpl  implements VideoService {


      @Autowired
      private VideoRepository videoRepository;


    @Override
    public void addVideo(Videos video, MultipartFile file)
    {

           try {
               String originalFilename = file.getOriginalFilename();
               String contentType = file.getContentType();
               InputStream inputStream = file.getInputStream();


                // file path : create

               String cleanFolder = StringUtils.cleanPath(originalFilename);

               // folder path with  filename
               Path path = Paths.get(cleanFolder, cleanFolder);

               System.out.println(contentType);
               System.out.println(path);


               // video meta data

               video.setContentType(contentType);
               video.setFilePath(path.toString());

               videoRepository.save(video);

           } catch (IOException e) {
               throw new RuntimeException(e);
           }
    }

    @Override
    public Videos getVideoById(String videoId) {
        return null;
    }

    @Override
    public Videos getVideoByTitle(String title) {
        return null;
    }
}
