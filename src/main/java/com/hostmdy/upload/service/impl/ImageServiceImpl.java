package com.hostmdy.upload.service.impl;

import org.springframework.stereotype.Service;

import com.hostmdy.upload.entity.Image;
import com.hostmdy.upload.repository.ImageRepository;
import com.hostmdy.upload.service.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{
	
	private final ImageRepository imageRepository;

	@Override
	public Image saveImage(Image image) {
		// TODO Auto-generated method stub
		return imageRepository.save(image);
	}

	@Override
	public Image findByName(String name) {
		// TODO Auto-generated method stub
		return imageRepository.findByName(name);
	}

}
