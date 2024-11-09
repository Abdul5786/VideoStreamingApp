package com.Abdul.VideoStreamingApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name ="Course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  String id;

    private  String title;

    @OneToMany(mappedBy = "course")
    private List<Video> list=new ArrayList<>();











}
