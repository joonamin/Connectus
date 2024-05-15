package social.connectus.walk.domain.ports.outbound;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImagePort {
    String uploadImage(MultipartFile image) throws IOException;
}
