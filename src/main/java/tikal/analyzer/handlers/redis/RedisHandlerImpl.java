package tikal.analyzer.handlers.redis;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RedisHandlerImpl implements RedisHandler {

	@Override
	public int storeUsers(String key, List<String> users) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int storeChannels(String key, List<String> channels) {
		// TODO Auto-generated method stub
		return 0;
	}



}
