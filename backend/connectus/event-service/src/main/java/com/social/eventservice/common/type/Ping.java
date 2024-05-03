package com.social.eventservice.common.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ping extends Position {
	private Type type;
	private Long domainId; // 타입에 해당하는 domain entity의 id

	public Ping(double longitude, double latitude, Type type, Long domainId) {
		super(longitude, latitude);
		this.type = type;
		this.domainId = domainId;
	}
}
