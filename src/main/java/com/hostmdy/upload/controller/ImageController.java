package com.hostmdy.upload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.upload.entity.Image;
import com.hostmdy.upload.service.ImageService;
import com.hostmdy.upload.utility.ClasspathFileLoader;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImageController {
	
	private final ImageService imageService;
	private final Environment env;
	private final ClasspathFileLoader classpathFileLoader;
	
	@PostMapping("/upload")
	public ResponseEntity<Image> upload(@RequestParam("file") MultipartFile file) throws IOException{
		
			String uploadPath = env.getProperty("upload.path");

            String fileName = UUID.randomUUID().toString()+".jpg";
            
            Path filePath = Path.of(uploadPath+fileName);

            try {
				Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Image image = new Image();
            image.setName(fileName);
            Image savedImage = imageService.saveImage(image);
            
            return ResponseEntity.ok(savedImage);

	}
	
	@GetMapping("/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        ClassPathResource resource = new ClassPathResource("images/" + imageName);
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
	
	
	
	
}
