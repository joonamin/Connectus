package com.social.eventservice.common.utils.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.social.eventservice.application.rest.request.MakeEventRequest;
import com.social.eventservice.common.utils.converter.command.meta.MakeEventCommandMetadata;
import com.social.eventservice.domain.dto.MakeEventCommand;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MapperUtil {
	private final ModelMapper modelMapper;

	// 아래에는 각각의 클래스에 대한 mapping 정보를 리턴하는
	// static factory method를 정의합니다.
	public MakeEventCommand requestToCommand(MakeEventRequest request, MakeEventCommandMetadata additional) {
		MakeEventCommand result = modelMapper.map(request, MakeEventCommand.class);
		result.setSpotList(additional.getSpotList());
		result.setImageUrl(additional.getImageUrl());
		return result;
	}

	public MakeEventCommand requestToCommand(MakeEventRequest request) {
		return modelMapper.map(request, MakeEventCommand.class);
	}
}
