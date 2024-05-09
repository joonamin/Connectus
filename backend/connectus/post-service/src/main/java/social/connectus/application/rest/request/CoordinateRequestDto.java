package social.connectus.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CoordinateRequestDto {
	private double longitude;
	private double latitude;
}
