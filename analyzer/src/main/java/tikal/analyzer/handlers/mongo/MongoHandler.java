package tikal.analyzer.handlers.mongo;

import java.util.List;

public interface MongoHandler {
	
	public List<MongoSlackRawData> getRawData(long from, long to);

}
