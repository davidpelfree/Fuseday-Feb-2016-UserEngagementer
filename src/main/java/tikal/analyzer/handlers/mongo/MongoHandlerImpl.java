package tikal.analyzer.handlers.mongo;

import java.util.List;
import java.util.ArrayList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import tikal.analyzer.controller.AnalyzerController;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.mongodb.MongoException;
import com.mongodb.MongoClient;

@Component
public class MongoHandlerImpl implements MongoHandler {
	
	private final Logger LOGGER = LoggerFactory.getLogger(MongoHandlerImpl.class);

	
	private MongoCollection<Document> collection;

	public MongoHandlerImpl() {
		super();
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase database = mongoClient.getDatabase("test");
		this.collection = database.getCollection("slackPost");
	}

	public MongoHandlerImpl(String host) {
		super();
		MongoClient mongoClient = new MongoClient( host , 27017 );
		MongoDatabase database = mongoClient.getDatabase("test");
		this.collection = database.getCollection("slackPost");
	}

	public boolean deleteRawData(String msgId) {
		try {
			Document query = new Document("_id", new ObjectId(msgId));
			collection.deleteOne(query);

		} catch (MongoException ex) {
			LOGGER.error("deleteMessage method", ex);
			return false;
		} catch (IllegalArgumentException e) {
			LOGGER.error("invalid Id for Msg - " + msgId);
			return false;
		}

		return true;
	}

	public List<MongoSlackRawData> getRawData(long from, long to) {
		List<MongoSlackRawData> list = null;

		try {
			MongoCursor<Document> cursor;
			Document query = new Document("timestamp", new Document("$gte", from).append("$lt", to));

			cursor = collection.find(query).iterator();
			list = new ArrayList<MongoSlackRawData>();

			try {
				while (cursor.hasNext()) {
					list.add(convertToEntity(cursor.next()));
				}
			} finally {
				cursor.close();
			}
		} catch (MongoException ex) {
//			log.error("getRawData method", ex);
			return null;
		}
		return list;
	}

	private static final MongoSlackRawData convertToEntity(Document data) {
		MongoSlackRawData result = new MongoSlackRawData();
		result.setUserName(data.getString("user"));
		result.setChannel(data.getString("channel"));
		result.setTimestamp(data.getLong("timestamp"));

		return result;
	}
}
