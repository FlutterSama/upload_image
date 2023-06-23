package com.hostmdy.upload.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.upload.entity.Image;

public interface ImageRepository extends CrudRepository<Image,Long>{
	Image findByName(String name);
}
