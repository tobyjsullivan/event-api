import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.json.JSONObject;

public abstract class Event {
	private Date _timestamp;
	
	public Event() {
		this(new Date());
	}
	
	public Event(Date timestamp) {
		this._timestamp = timestamp;
	}
	
	public Date getTimestamp() {
		return this._timestamp;
	}
	
	public abstract String getType();
	
	public abstract Map<String, String> getProperties();
	
	public String toJson() {
		Map<String, String> map = new Hashtable<String, String>();
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		map.put("timestamp", fmt.format(this._timestamp));
		map.put("event_type", this.getType());
		
		for(Map.Entry<String, String> entry : this.getProperties().entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		
		JSONObject json = new JSONObject(map);
		
		return json.toString();
	}
}
