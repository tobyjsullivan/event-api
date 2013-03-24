import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RequestHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		String path = t.getRequestURI().getPath();
		String method = t.getRequestMethod();
		String query = t.getRequestURI().getQuery();
//		System.out.printf("Request received for path: %s; method: %s\n", path + (query != null ? '?' + query : ""), method);
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(t.getRequestBody()));
		
		ArrayList<JSONObject> events = new ArrayList<JSONObject>();
		String line;
		while((line = rd.readLine()) != null) {
			try {
				JSONObject event = new JSONObject(line);
				events.add(event);
			} catch (JSONException e) {
				// TODO Return error and forget this request
				
				// Skipped malformed objects
				continue;
			}
		}

		for(JSONObject event : events) {
			System.out.println(event.toString());
			EventWriter.writeEvent(event);
		}
		
		String response = "This is the hard-coded response. Deal with it.";
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

}
