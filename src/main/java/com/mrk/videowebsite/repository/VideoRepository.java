package com.mrk.videowebsite.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mrk.videowebsite.model.Video;

public interface VideoRepository extends MongoRepository<Video, String> {
    //List<Video> findByCategory(String category);
    List<Video> findByTitleContainingIgnoreCase(String title);
    List<Video> findByCategoryIgnoreCase(String category);
}