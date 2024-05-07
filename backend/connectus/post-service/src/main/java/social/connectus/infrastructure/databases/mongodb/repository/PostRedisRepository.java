package social.connectus.infrastructure.databases.mongodb.repository;

import org.springframework.data.repository.CrudRepository;

import social.connectus.domain.model.mongoDB.PostRedis;

public interface PostRedisRepository extends CrudRepository<PostRedis, String> {
}
