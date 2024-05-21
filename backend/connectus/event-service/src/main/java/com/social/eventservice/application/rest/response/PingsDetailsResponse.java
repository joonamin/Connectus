package com.social.eventservice.application.rest.response;

import com.social.eventservice.common.type.Position;
import com.social.eventservice.common.type.Spot;
import com.social.eventservice.common.type.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PingsDetailsResponse extends Position {
	private Type type;
	private Long domainId; // 타입에 해당하는 domain entity의 id

	public PingsDetailsResponse(double longitude, double latitude, Type type, Long domainId) {
		super(longitude, latitude);
		this.type = type;
		this.domainId = domainId;
	}

	public static PingsDetailsResponse from(Spot spot) {
		return PingsDetailsResponse.builder()
			.latitude(spot.getLatitude())
			.longitude(spot.getLongitude())
			.type(Type.EVENT)
			.domainId(spot.getId())
			.build();
	}
}
