package tikal.analyzer.handlers.redis;

import java.util.List;

public interface RedisHandler {
	
	public int storeUsers (String key, List<String> users);
	
	public int storeChannels (String key, List<String> channels);

}