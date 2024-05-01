package com.social.eventservice.domain.port.outbound;

import org.springframework.web.multipart.MultipartFile;

public interface ImagePort extends FilePort {
	// todo: describe image save, download, delete
	String imageUpload(MultipartFile imageFile);
}
