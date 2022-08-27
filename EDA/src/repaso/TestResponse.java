package repaso;
import java.time.*;

public class TestResponse {

	private int arraySize;
	private Duration time;
	private int count;
	
	public TestResponse(Duration time, int arraySize, int count) {
		this.time = time;
		this.arraySize = arraySize;
		this.count = count;
	}

	public Duration getTime() {
		return time;
	}
	public int getArraySize() {
		return arraySize;
	}
	public int getCount() {
		return count;
	}
	
}
