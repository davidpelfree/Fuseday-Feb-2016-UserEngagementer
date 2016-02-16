package tikal.analyzer.handlers.mongo;

import java.util.Date;
import java.util.List;

public interface MongoHandler {
	
	public List<MongoSlackRawData> getRawData(Date from, Date to);

}
