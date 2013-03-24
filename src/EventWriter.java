import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;


public class EventWriter {
	private static final String OUTPUT_FILE = "events.log"; 
	
	public static void writeEvent(JSONObject event) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(OUTPUT_FILE, true));
		out.write(event.toString() + "\n");
		out.close();
	}
}
