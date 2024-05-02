package domain.ports.outbound;

import application.rest.request.CreateFeedRequestDto;

public interface CreateFeedPort {
	String createFeed(CreateFeedRequestDto feedRequestDto);
}
