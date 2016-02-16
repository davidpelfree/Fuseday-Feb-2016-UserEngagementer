package tikal.analyzer.handlers.redis;

import tikal.analyzer.handlers.mongo.MongoSlackRawData;

public class RedisKey {
	
	private String key;
	
	public RedisKey (MongoSlackRawData mongoSlackRawData, RedisTimeFrame redisTimeFrame) {
		this.key = mongoSlackRawData.getUserName() + "-" + mongoSlackRawData.getChannel() + "-" + redisTimeFrame.name();
	}

	public String getKey() {
		return key;
	}

}
