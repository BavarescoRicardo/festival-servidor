package api.nxmu.festival;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class RedisCacheConfiguration {
	
	@Bean
	RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
	    return RedisCacheManager.create(connectionFactory);
	}
}
