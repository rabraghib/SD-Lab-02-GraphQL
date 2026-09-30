package com.xproce.videoservice.service;

import com.xproce.videoservice.dto.VideoDTO;
import com.xproce.videoservice.dto.VideoRequest;

import java.util.List;

public interface VideoService {
    List<VideoDTO> findAll();
    VideoDTO findById(Long id);
    VideoDTO saveVideo(VideoRequest videoRequest);
    VideoDTO updateVideo(VideoDTO video);
}

