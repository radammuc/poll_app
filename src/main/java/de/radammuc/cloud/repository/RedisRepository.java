package de.radammuc.cloud.repository;

import de.radammuc.cloud.domain.RedisPoll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<RedisPoll, String> {
}

