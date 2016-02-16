package tikal.analyzer.handlers.mongo;

import java.io.Serializable;

public class MongoSlackRawData implements Serializable {
	
	private static final long serialVersionUID = -4273579576563219153L;
	
	private String userName;
	private String channel;
	private long timestamp;
	
	public MongoSlackRawData(){}
	public MongoSlackRawData(String userName, String channel, long timestamp) {
		super();
		this.userName = userName;
		this.channel = channel;
		this.timestamp = timestamp;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MongoSlackRawData that = (MongoSlackRawData) o;

		if (timestamp != that.timestamp) return false;
		if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
		return channel != null ? channel.equals(that.channel) : that.channel == null;

	}

	@Override
	public int hashCode() {
		int result = userName != null ? userName.hashCode() : 0;
		result = 31 * result + (channel != null ? channel.hashCode() : 0);
		result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "MongoSlackRawData{" +
				"userName='" + userName + '\'' +
				", channel='" + channel + '\'' +
				", timestamp=" + timestamp +
				'}';
	}
}
