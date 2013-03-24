import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EventRecorder {
	public static void RecordEvent(Event event) {
		try {
			URL serverUrl = new URL("http://localhost:8080");
			
			HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setReadTimeout(1000);
			
			conn.connect();
			
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(event.toJson());
			wr.close();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line+"\n");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
