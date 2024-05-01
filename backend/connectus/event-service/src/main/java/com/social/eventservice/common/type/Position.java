package com.social.eventservice.common.type;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class Position {
	double longitude;
	double latitude;
}
