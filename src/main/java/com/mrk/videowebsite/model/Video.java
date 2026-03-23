package com.mrk.videowebsite.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "video")
@Data
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;
    private String category;
    private List<String> tags;
    private int views;
    private LocalDateTime createdAt;
    private String youtubeUrl;
}