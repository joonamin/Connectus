package com.social.eventservice.infrastructure.external;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.social.eventservice.domain.port.outbound.ImagePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ImageAdapter implements ImagePort {

	private final AmazonS3 amazonS3;

	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucket;
	@Override
	public String uploadImage(MultipartFile imageFile) throws IOException {

		String originalFilename = imageFile.getOriginalFilename();

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(imageFile.getSize());
		metadata.setContentType(imageFile.getContentType());

		amazonS3.putObject(bucket, originalFilename, imageFile.getInputStream(),
			metadata);
		return amazonS3.getUrl(bucket, originalFilename).toString();
	}

}
