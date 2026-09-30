package com.xproce.videoservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoDTO {
    private Long id;
    private String name;
    private String url;
    private String description;
    private String datePublication;
    private CreatorDTO creator;
}
