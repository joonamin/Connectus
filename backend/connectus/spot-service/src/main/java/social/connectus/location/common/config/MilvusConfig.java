package social.connectus.location.common.config;

import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.collection.request.LoadCollectionReq;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MilvusConfig {
    @Value("${spring.ai.vectorstore.milvus.client.host}")
    private String host;

    @Value("${spring.ai.vectorstore.milvus.client.port}")
    private String port;

    @Value("${spring.ai.vectorstore.milvus.client.databaseName}")
    private String databaseName;

    @Value("${spring.ai.vectorstore.milvus.client.collectionName}")
    private String collectionName;

    private static MilvusClientV2 clientInstance;
    @Bean
    public MilvusClientV2 createMilvusClient(){
        if (clientInstance == null) {
            // 동기화를 통한 하나의 접근 허용
            synchronized (MilvusConfig.class) {
                // Double-checked locking 방식을 통한 스레드 동시성 회피
                if (clientInstance == null) {
                    String url = "https://" + this.host + ":" + this.port;

                    // Milvus 연결 설정
                    ConnectConfig connectConfig = ConnectConfig.builder()
                            .uri(url)
                            .build();
                    MilvusClientV2 client = new MilvusClientV2(connectConfig);

                    // Collection 로드
                    LoadCollectionReq loadReq = LoadCollectionReq.builder()
                            .collectionName(this.collectionName)
                            .build();

                    client.loadCollection(loadReq);
                    clientInstance = client;
                }
            }
        }
        return clientInstance;
    }
}
