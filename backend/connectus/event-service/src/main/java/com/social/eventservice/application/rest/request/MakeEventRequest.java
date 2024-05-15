package com.social.eventservice.application.rest.request;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.social.eventservice.common.type.Position;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MakeEventRequest {
	private Long userId;
	private String title;
	private MultipartFile image;
	private String description;
	private List<Position> positions;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int reward;
}
