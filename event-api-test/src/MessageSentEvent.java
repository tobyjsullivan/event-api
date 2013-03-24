import java.util.Hashtable;
import java.util.Map;


public class MessageSentEvent extends Event {
	private String _message;
	
	public MessageSentEvent(String message) {
		this._message = message;
	}

	@Override
	public String getType() {
		return "message_sent";
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> result = new Hashtable<String, String>();
		
		result.put("message", this._message);
		
		return result;
	}

}
