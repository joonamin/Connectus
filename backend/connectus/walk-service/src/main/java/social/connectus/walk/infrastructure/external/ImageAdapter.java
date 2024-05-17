package social.connectus.walk.infrastructure.external;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import social.connectus.walk.domain.ports.outbound.ImagePort;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImageAdapter implements ImagePort {
    private final AmazonS3 amazonS3;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadImage(MultipartFile image) throws IOException {
        String originalFileName = image.getOriginalFilename();
        String fileName = UUID.randomUUID() + originalFileName;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());

        amazonS3.putObject(bucket, fileName, image.getInputStream(),metadata);
        return amazonS3.getUrl(bucket, fileName).toString();
    }

}
