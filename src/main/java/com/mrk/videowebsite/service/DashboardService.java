package com.mrk.videowebsite.service;


import com.mrk.videowebsite.repository.ContactRepository;
import com.mrk.videowebsite.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final VideoRepository videoRepository;
    private final ContactRepository contactRepository;

    public Map<String, Long> getStats(){

        long totalVideos = videoRepository.count();
        long totalMessages = contactRepository.count();

        Map<String, Long> stats = new HashMap<>();
        stats.put("totalVideos", totalVideos);
        stats.put("totalMessages", totalMessages);

        return stats;
    }
}
