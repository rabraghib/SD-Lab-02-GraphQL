package com.xproce.videoservice.service;

import com.xproce.videoservice.dto.CreatorDTO;
import com.xproce.videoservice.dto.CreatorRequest;

public interface CreatorService {
    CreatorDTO findById(Long id);
    CreatorDTO saveCreator(CreatorRequest creatorRequest);
}
