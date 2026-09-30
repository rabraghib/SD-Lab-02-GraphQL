package com.xproce.videoservice.web;

import com.xproce.videoservice.dto.CreatorDTO;
import com.xproce.videoservice.dto.CreatorRequest;
import com.xproce.videoservice.dto.VideoDTO;
import com.xproce.videoservice.dto.VideoRequest;
import com.xproce.videoservice.service.CreatorService;
import com.xproce.videoservice.service.VideoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
public class VideoGraphQlController {
    private final CreatorService creatorService;
    private final VideoService videoService;

    public VideoGraphQlController(CreatorService creatorService, VideoService videoService) {
        this.creatorService = creatorService;
        this.videoService = videoService;
    }

    @QueryMapping
    public List<VideoDTO> videoList() {
        return videoService.findAll();
    }

    @QueryMapping
    public CreatorDTO creatorById(@Argument Long id) {
        return creatorService.findById(id);
    }

    @MutationMapping
    public CreatorDTO saveCreator(@Argument CreatorRequest creator) {
        return creatorService.saveCreator(creator);
    }

    @MutationMapping
    public VideoDTO saveVideo(@Argument VideoRequest video) {
        return videoService.saveVideo(video);
    }

    @SubscriptionMapping
    public Flux<VideoDTO> notifyVideoChange() {
        return Flux.fromStream(Stream.generate(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Random random = new Random();
            CreatorRequest creatorRequest = CreatorRequest.builder()
                .name("x" + random.nextInt())
                .email("x@gmail.com").build();
            CreatorDTO creator = creatorService.saveCreator(creatorRequest);
            VideoDTO video = videoService.findById(1L);
            video.setCreator(creator);
            videoService.updateVideo(video);
            return video;
        }));
    }
}
