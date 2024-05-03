package com.social.eventservice.common.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
public class Spot extends Position {
	long id;

	Spot(double longitude, double latitude) {
		super(longitude, latitude);
	}

	public Spot(double longitude, double latitude, long id) {
		super(longitude, latitude);
		this.id = id;
	}
}
