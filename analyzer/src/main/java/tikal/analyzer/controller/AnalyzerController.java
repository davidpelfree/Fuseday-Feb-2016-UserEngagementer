package tikal.analyzer.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tikal.analyzer.handlers.mongo.MongoHandler;
import tikal.analyzer.handlers.mongo.MongoSlackRawData;
import tikal.analyzer.handlers.redis.RedisHandler;
import tikal.analyzer.handlers.redis.RedisKey;
import tikal.analyzer.handlers.redis.RedisTimeFrame;

@Component
public class AnalyzerController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(AnalyzerController.class);
	 
	@Autowired
	private MongoHandler mongoHandler;
	
	@Autowired
	private RedisHandler redisHandler;
	
	@Scheduled(cron="0 0 */1 * * *")
	public void doWorkHourly() {
		doWork(RedisTimeFrame.Hourly);
	}
	
	@Scheduled(cron="0 0 0 */1 * *")
	public void doWorkDaily() {
		doWork(RedisTimeFrame.Daily);
	}

	private void doWork(RedisTimeFrame timeFrame) {
		
		LOGGER.info("Starting {} fetch and store cycle", timeFrame);
		
		DateTime from = (timeFrame == RedisTimeFrame.Hourly) ? new DateTime().minusHours(1) : new DateTime().minusDays(1);
		DateTime to = new DateTime();
		List<MongoSlackRawData> rawData = mongoHandler.getRawData(from.getMillis(), to.getMillis());
		
		LOGGER.info("Fetched {} items from Mongo.  From: {}, To: {}", rawData.size(), from, to);
		
		List<RedisKey> redisKeys = rawData.stream().map(item -> createRedisKeyFromMongoRawData(item, RedisTimeFrame.Hourly)).collect(Collectors.toList());
		LOGGER.info("Created redisKeys successfully");
		
		int numItemsStored = redisHandler.store(redisKeys);
		LOGGER.info("Stored {} redisKeys successfully", numItemsStored);
	}

	private RedisKey createRedisKeyFromMongoRawData(MongoSlackRawData item, RedisTimeFrame timeFrame) {
		return new RedisKey(item, timeFrame);
	}
	
}
