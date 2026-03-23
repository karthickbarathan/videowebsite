package com.mrk.videowebsite.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.mrk.videowebsite.repository.VideoRepository;
import com.mrk.videowebsite.model.Video;
import com.mrk.videowebsite.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository repository;

    public Video createVideo(Video video) {
        video.setCreatedAt(LocalDateTime.now());
        video.setViews(0);

        // Generate thumbnail automatically
        video.setThumbnailUrl(extractThumbnail(video.getYoutubeUrl()));

        return repository.save(video);
    }

    public List<Video> getAllVideos() {
        return repository.findAll();
    }

    public Video getVideoById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found"));
    }

    public void deleteVideo(String id) {

        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Video not found");
        }

        repository.deleteById(id);
    }

    public Video updateVideo(String id, Video updatedVideo) {
        Video existing = getVideoById(id);

        existing.setTitle(updatedVideo.getTitle());
        existing.setDescription(updatedVideo.getDescription());
        existing.setCategory(updatedVideo.getCategory());
        existing.setYoutubeUrl((updatedVideo.getYoutubeUrl()));
        //existing.setThumbnailUrl(updatedVideo.getThumbnailUrl());
        //existing.setTags(updatedVideo.getTags());

        return repository.save(existing);
    }

//    public Page<Video> getPaginatedVideos(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return repository.findAll(pageable);
//    }

    public Page<Video> getPaginatedVideos(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return repository.findAll(pageable);
    }

    public List<Video> searchByTitle(String keyword) {
        return repository.findByTitleContainingIgnoreCase(keyword);
    }

    public List<Video> filterByCategory(String category) {
        return repository.findByCategoryIgnoreCase(category);
    }

    public String extractThumbnail(String youtubeUrl) {

        String videoId = youtubeUrl.split("v=")[1];

        return "https://img.youtube.com/vi/" + videoId + "/maxresdefault.jpg";
    }
}
