import java.io.IOException;

public class Tester {
	public static void main(String[] args) throws IOException {
		String message;
		do {
			System.out.print("Message: ");
			
			int maxLength = 256;
			byte[] input = new byte[256];
			int length = System.in.read(input, 0, maxLength);
			
			message = new String(input, 0, length);
			message = message.trim();
			
			Event event = new MessageSentEvent(message);
			
			EventRecorder.RecordEvent(event);
		} while(message != "exit");
	}
}
