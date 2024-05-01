package com.social.eventservice.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pings extends Spot {
	private Type type;
	private java.lang.Long domainId; // 타입에 해당하는 domain entity의 id
}
