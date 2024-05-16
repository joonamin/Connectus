package social.connectus.location.common.config;

import io.milvus.client.MilvusClient;
import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import io.milvus.param.collection.LoadCollectionParam;
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

    @Value("${spring.ai.vectorstore.milvus.databaseName}")
    private String databaseName;

    @Value("${spring.ai.vectorstore.milvus.client.username}")
    private String username;

    @Value("${spring.ai.vectorstore.milvus.client.password}")
    private String password;

    @Value("${spring.ai.vectorstore.milvus.collectionName}")
    private String collectionName;

    private static MilvusServiceClient clientInstance;
    @Bean
    public MilvusServiceClient createMilvusClient(){
        if (clientInstance == null) {
            String url = "https://" + this.host + ":" + this.port;

            // Milvus 연결 설정
//            ConnectConfig connectConfig = ConnectConfig.builder()
//                    .uri(url)
//                    .build();
            ConnectParam connect = ConnectParam.newBuilder()
                    .withUri(url)
                    .build();
            MilvusServiceClient client = new MilvusServiceClient(connect);

            // Collection 로드
            LoadCollectionParam loadReq = LoadCollectionParam.newBuilder()
                    .withCollectionName(this.collectionName)
                    .build();

            client.loadCollection(loadReq);
            clientInstance = client;
        }
        return clientInstance;
    }
}
