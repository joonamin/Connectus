package com.social.eventservice.domain.port.outbound;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImagePort extends FilePort {
	// todo: describe image save, download, delete
	String uploadImage(MultipartFile imageFile) throws IOException;
}
