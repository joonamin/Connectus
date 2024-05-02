package domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import application.rest.request.CoordinateRequestDto;
import application.rest.response.FeedResponse;
import common.annotation.UseCase;
import common.exception.GlobalException;
import common.exception.ParameterNotFoundException;
import domain.ports.inbound.FeedUseCase;
import domain.ports.outbound.FeedPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FeedService implements FeedUseCase {
	private final FeedPort feedPort;
	@Override
	public FeedResponse feedDetail(Long walkId) throws GlobalException {
		try {
			if (walkId == null) {
				new ParameterNotFoundException("walkId");
			}
		} catch (Exception e) {
			throw new GlobalException("FeedService : " + e.getMessage());
		}
		return feedPort.feedDetail(walkId);
	}
}
