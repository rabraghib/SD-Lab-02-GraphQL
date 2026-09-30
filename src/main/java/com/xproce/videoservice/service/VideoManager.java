package com.xproce.videoservice.service;

import com.xproce.videoservice.dao.entities.Creator;
import com.xproce.videoservice.dao.entities.Video;
import com.xproce.videoservice.dao.repositories.CreatorRepository;
import com.xproce.videoservice.dao.repositories.VideoRepository;
import com.xproce.videoservice.dto.VideoDTO;
import com.xproce.videoservice.dto.VideoRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoManager implements VideoService {

    private final VideoRepository videoRepository;
    private final CreatorRepository creatorRepository;
    private final ModelMapper modelMapper;

    public VideoManager(
            VideoRepository videoRepository,
            CreatorRepository creatorRepository,
            ModelMapper modelMapper
    ) {
        this.videoRepository = videoRepository;
        this.creatorRepository = creatorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VideoDTO> findAll() {
        return videoRepository.findAll()
            .stream()
            .map(video -> modelMapper.map(video, VideoDTO.class))
            .toList();
    }

    @Override
    public VideoDTO findById(Long id) {
        Video video = videoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(String.format("Video %s not found", id)));

        return modelMapper.map(video, VideoDTO.class);
    }

    @Override
    public VideoDTO saveVideo(VideoRequest videoRequest) {
        Creator creator = modelMapper.map(videoRequest.getCreator(), Creator.class);
        creator = creatorRepository.save(creator);

        Video video = modelMapper.map(videoRequest, Video.class);
        video.setCreator(creator);
        video = videoRepository.save(video);

        return modelMapper.map(video, VideoDTO.class);
    }

    @Override
    public VideoDTO updateVideo(VideoDTO videoDTO) {
        Video video = modelMapper.map(videoDTO, Video.class);
        video = videoRepository.save(video);
        return modelMapper.map(video, VideoDTO.class);
    }
}
