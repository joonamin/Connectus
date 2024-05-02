package domain.service;

import application.rest.request.CoordinateRequestDto;
import application.rest.request.CreateFeedRequestDto;
import application.rest.request.PostRequestDto;
import common.annotation.UseCase;
import common.exception.GlobalException;
import common.exception.ParameterNotFoundException;
import domain.ports.inbound.CreateFeedUseCase;
import domain.ports.outbound.CreateFeedPort;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateFeedService implements CreateFeedUseCase {
	private final CreateFeedPort createFeedPort;
	@Override
	public String createFeed(CreateFeedRequestDto createFeedRequestDto) throws ParameterNotFoundException, GlobalException{
		try {
		}
		catch (Exception e) {
			throw new GlobalException("createPost : " + e.getMessage());
		}

		// port
		return createFeedPort.createFeed(createFeedRequestDto);
	}
}