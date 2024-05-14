package social.connectus.domain.ports.outbound;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImagePort {
	String uploadImage(MultipartFile image) throws IOException;
}
