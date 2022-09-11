package review;
import java.time.*;

/**
*
* @author Sergio Pereo
* 
*         Class used to store results of an efficiency test.
* 
*/

public class TestResponse {

	private int arraySize;
	private Duration time;
	private int count;
	
	public TestResponse(Duration time, int arraySize, int count) {
		this.time = time;
		this.arraySize = arraySize;
		this.count = count;
	}
	
	public void sum(TestResponse other) {
		this.time = this.time.plus(other.getTime());
		this.count += other.getCount();
	}
	
	@Override
	public String toString() {
		return arraySize + "," + time.toMillis() + "," + count;
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
	public void setArraySize(int arraySize) {
		this.arraySize = arraySize;
	}

	public void setTime(Duration time) {
		this.time = time;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
