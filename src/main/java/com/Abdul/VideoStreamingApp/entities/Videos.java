package com.Abdul.VideoStreamingApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "videos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Videos {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  String videoId;

    private  String title;

    private  String description;

    private  String  contentType;

    private  String filePath;

    @ManyToOne
    private Course course;
}
