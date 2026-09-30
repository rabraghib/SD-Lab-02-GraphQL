package com.xproce.videoservice.dao.repositories;

import com.xproce.videoservice.dao.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Long> {
}
