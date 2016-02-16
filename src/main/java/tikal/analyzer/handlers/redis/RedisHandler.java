package tikal.analyzer.handlers.redis;

import java.util.List;

public interface RedisHandler {
	
	public int store (List<RedisKey> keys);
	

}
