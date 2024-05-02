package application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class CoordinateRequestDto {
	private double longitude;
	private double latitude;
}
