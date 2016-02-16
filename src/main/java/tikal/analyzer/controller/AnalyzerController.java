package tikal.analyzer.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tikal.analyzer.handlers.mongo.MongoHandler;
import tikal.analyzer.handlers.mongo.MongoSlackRawData;
import tikal.analyzer.handlers.redis.RedisHandler;

@Component
public class AnalyzerController {
	
	private static final String USERS_KEY = "USERS";
	private static final String CHANNELS_KEY = "USERS";

	private final Logger LOGGER = LoggerFactory.getLogger(AnalyzerController.class);
	 
	@Autowired
	private MongoHandler mongoHandler;
	
	@Autowired
	private RedisHandler redisHandler;
	
	@Scheduled(cron="0 0 */1 * * *")
	public void doWorkHourly() {
		
		DateTime from = new DateTime(DateTimeZone.UTC).minusHours(1);
		DateTime to = new DateTime(DateTimeZone.UTC);
		
		long currentTime = to.getMillis();
	    long roundedHourly = currentTime - (currentTime % (60*60*1000));
		
		LOGGER.info("Starting {} fetch and store cycle", roundedHourly);
		
		List<MongoSlackRawData> rawData = mongoHandler.getRawData(from.getMillis(), to.getMillis());
		
		LOGGER.info("Fetched {} items from Mongo.  From: {}, To: {}", rawData.size(), from, to);
		
		List<String> users = rawData.stream().map(item -> item.getUserName()).collect(Collectors.toList());
		int numUsersStored = redisHandler.storeUsers(roundedHourly + "_" + USERS_KEY, users);
		
		List<String> channels = rawData.stream().map(item -> item.getChannel()).collect(Collectors.toList());
		int numChannelsStored = redisHandler.storeChannels(roundedHourly + "_" + CHANNELS_KEY, channels);
		
		LOGGER.info("Stored {} users and {} channels to redis successfully", numUsersStored, numChannelsStored);
	}


	
}
