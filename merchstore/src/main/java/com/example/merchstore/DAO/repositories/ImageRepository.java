package com.example.merchstore.repositories;

import com.example.merchstore.DAO.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
