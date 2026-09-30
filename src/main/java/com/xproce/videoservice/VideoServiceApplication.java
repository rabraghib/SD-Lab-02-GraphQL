package com.xproce.videoservice;

import com.xproce.videoservice.dao.entities.Creator;
import com.xproce.videoservice.dao.entities.Video;
import com.xproce.videoservice.dao.repositories.CreatorRepository;
import com.xproce.videoservice.dao.repositories.VideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Random;

@SpringBootApplication
public class VideoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner start(VideoRepository videoRepository, CreatorRepository creatorRepository) {
        return args -> {
            Random random = new Random();

            String[] names = {"Ahmed", "Mouad", "Amina", "Karim", "Yasser", "Hiba", "Hanane", "Sara", "Abdellah"};
            Creator[] creators = new Creator[names.length];

            for (int i = 0; i < names.length; i++) {

                creators[i] = Creator.builder()
                    .name(names[i])
                    .email(names[i].toLowerCase() + "@example.com")
                    .build();
            }

            creatorRepository.saveAll(Arrays.asList(creators));

            Video[] videos = new Video[100];
            for (int i = 0; i < videos.length; i++) {
                videos[i] = Video.builder()
                    .name("Video #" + i)
                    .url("https://video.link/" + (123 + i))
                    .description("Video description...")
                    .datePublication("22/09/2026")
                    .creator(creators[random.nextInt(creators.length)])
                    .build();
            }
            videoRepository.saveAll(Arrays.asList(videos));
        };
    }
}
