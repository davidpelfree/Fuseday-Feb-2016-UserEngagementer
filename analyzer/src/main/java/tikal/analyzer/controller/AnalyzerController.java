package tikal.analyzer.controller;

import java.util.Date;
import java.util.List;

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
	
	private final Logger LOGGER = LoggerFactory.getLogger(AnalyzerController.class);
	 
	@Autowired
	private MongoHandler mongoHandler;
	
	@Autowired
	private RedisHandler redisHandler;
	
	@Scheduled(cron="0 */1 * * * *")
	public void doWork() {
		
		LOGGER.info("Starting fetch and store cycle");
		
		Date from = new Date();
		Date to = new Date();
		List<MongoSlackRawData> rawData = mongoHandler.getRawData(from.getTime(), to.getTime());
		
		LOGGER.info("Fetched {} items from Mongo.  From: {}, To: {}", rawData.size(), from, to);
		
//		rawData.stream().map(item -> )
		
	}
	
}
