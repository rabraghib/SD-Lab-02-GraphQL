package com.xproce.videoservice.service;

import com.xproce.videoservice.dao.entities.Creator;
import com.xproce.videoservice.dao.repositories.CreatorRepository;
import com.xproce.videoservice.dto.CreatorDTO;
import com.xproce.videoservice.dto.CreatorRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CreatorManager implements CreatorService {

    private final CreatorRepository creatorRepository;
    private final ModelMapper modelMapper;

    public CreatorManager(CreatorRepository creatorRepository, ModelMapper modelMapper) {
        this.creatorRepository = creatorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreatorDTO findById(Long id) {
        Creator creator = creatorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
        return modelMapper.map(creator, CreatorDTO.class);
    }

    @Override
    public CreatorDTO saveCreator(CreatorRequest creatorRequest) {
        Creator creator = modelMapper.map(creatorRequest, Creator.class);
        Creator savedCreator = creatorRepository.save(creator);
        return modelMapper.map(savedCreator, CreatorDTO.class);
    }
}
