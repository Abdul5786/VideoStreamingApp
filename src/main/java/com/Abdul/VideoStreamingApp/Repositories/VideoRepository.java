package com.Abdul.VideoStreamingApp.Repositories;

import com.Abdul.VideoStreamingApp.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, String>
{
    // methods
    // native query
    // criteria api
}
