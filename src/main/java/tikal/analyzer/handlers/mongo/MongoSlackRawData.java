package tikal.analyzer.handlers.mongo;

import java.io.Serializable;

public class MongoSlackRawData implements Serializable {
	
	private static final long serialVersionUID = -4273579576563219153L;
	
	private String userName;
	private String channel;
	private String day;
	private String hour;
	
	public MongoSlackRawData(){}
	public MongoSlackRawData(String userName, String channel, String day, String hour) {
		super();
		this.userName = userName;
		this.channel = channel;
		this.day = day;
		this.hour = hour;
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
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}

	public void setTimestamp (long timestamp) {
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MongoSlackRawData other = (MongoSlackRawData) obj;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (hour == null) {
			if (other.hour != null)
				return false;
		} else if (!hour.equals(other.hour))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "SlackRawData [userName=" + userName + ", channel=" + channel + ", day=" + day + ", hour=" + hour + "]";
	}
	
	
	
	
	
	
}
