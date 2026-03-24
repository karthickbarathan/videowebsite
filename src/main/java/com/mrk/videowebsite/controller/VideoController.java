package com.mrk.videowebsite.controller;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.mrk.videowebsite.service.VideoService;
import com.mrk.videowebsite.model.Video;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VideoController {

    private final VideoService service;

    @PostMapping
    public Video create(@RequestBody Video video) {
        return service.createVideo(video);
    }

    @GetMapping
    public List<Video> getAll() {
        return service.getAllVideos();
    }

    @GetMapping("/{id}")
    public Video getById(@PathVariable String id) {
        return service.getVideoById(id);
    }

    @PutMapping("/{id}")
    public Video update(@PathVariable String id, @RequestBody Video video) {
        return service.updateVideo(id, video);
    }

    @GetMapping("/search")
    public List<Video> search(@RequestParam String keyword) {
        return service.searchByTitle(keyword);
    }

    @GetMapping("/paginated")
    public Page<Video> getPaginated(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        return service.getPaginatedVideos(page, size, sortBy);
    }

    @GetMapping("/category")
    public List<Video> getByCategory(@RequestParam String category) {
        return service.filterByCategory(category);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        service.deleteVideo(id);
        return "Video deleted successfully";
    }
}
