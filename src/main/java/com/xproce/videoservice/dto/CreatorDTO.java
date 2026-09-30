package com.xproce.videoservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatorDTO {
    private Long id;
    private String name;
    private String email;
}
