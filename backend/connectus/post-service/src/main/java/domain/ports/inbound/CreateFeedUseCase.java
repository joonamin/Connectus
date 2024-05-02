package domain.ports.inbound;

import application.rest.request.CoordinateRequestDto;
import application.rest.request.CreateFeedRequestDto;
import application.rest.request.PostRequestDto;
import common.exception.GlobalException;
import common.exception.ParameterNotFoundException;

public interface CreateFeedUseCase {
	String createFeed(CreateFeedRequestDto createFeedRequestDto) throws ParameterNotFoundException,GlobalException;
}