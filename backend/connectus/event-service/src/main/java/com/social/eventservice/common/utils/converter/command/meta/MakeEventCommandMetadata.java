package com.social.eventservice.common.utils.converter.command.meta;

import java.util.List;

import com.social.eventservice.common.type.Spot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakeEventCommandMetadata {
	private List<Spot> spotList;
	private String imageUrl;

	public static MakeEventCommandMetadata of(List<Spot> spotList, String imageUrl) {
		return MakeEventCommandMetadata.builder()
			.imageUrl(imageUrl)
			.spotList(spotList)
			.build();
	}
}
