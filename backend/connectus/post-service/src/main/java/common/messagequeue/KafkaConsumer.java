package common.messagequeue;

import java.util.HashMap;
import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.rest.request.CoordinateRequestDto;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {
	//방명록의 위치랑 유저 현재의 위치
	@KafkaListener(topics = "location-post-check")
	public CoordinateRequestDto checkLocation(String kafkaMessage) {
		log.info("Kafka Message[Check location] : " + kafkaMessage);

		Map<Object,Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try{
			map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
		} catch (JsonProcessingException e ){
			e.printStackTrace();
		}
		double longitude = (double)map.get("longitude");
		double latitude = (double)map.get("latitude");
		CoordinateRequestDto coordinateRequestDto = new CoordinateRequestDto(longitude, latitude);

		return coordinateRequestDto;
	}
}
