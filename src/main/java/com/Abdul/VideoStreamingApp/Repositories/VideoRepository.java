package com.Abdul.VideoStreamingApp.Repositories;

import com.Abdul.VideoStreamingApp.entities.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Videos, String>
{
    // methods
    // native query
    // criteria api
}
