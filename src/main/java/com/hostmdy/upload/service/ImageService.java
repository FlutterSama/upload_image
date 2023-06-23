package com.hostmdy.upload.service;

import com.hostmdy.upload.entity.Image;

public interface ImageService {
	Image saveImage(Image image);
	Image findByName(String name);
}
