package tikal.analyzer.handlers.redis;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisHandlerImpl implements RedisHandler {
	public static final String USERS = "users_%s";
	public static final String CHANNELS = "channels_%s";

	@Resource(name="redisStringTemplate")
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public int storeUsers(String key, List<String> users) {
		int count = 0;
		for (String user : users) {
			String redisKey = String.format(USERS, key);
			long result = redisTemplate.opsForHash().increment(redisKey, user, 1);
			count += result;
		}
		return count;
	}

	@Override
	public int storeChannels(String key, List<String> channels) {
		String redisKey = String.format(CHANNELS, key);
		int count = 0;
		for (String channel : channels) {
			long result = redisTemplate.opsForHash().increment(redisKey, channel, 1);
			count += result;
		}
		return count;
	}



}

