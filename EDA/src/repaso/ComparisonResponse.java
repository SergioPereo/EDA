package repaso;

public class ComparisonResponse {
	
	private int offsetTimeSum = 0;
	private int offsetStepsSum = 0;
	
	public ComparisonResponse(int offsetTimeSum, int offsetStepsSum) {
		this.offsetTimeSum = offsetTimeSum;
		this.offsetStepsSum = offsetStepsSum;
	}

	public int getOffsetTimeSum() {
		return offsetTimeSum;
	}

	public int getOffsetStepsSum() {
		return offsetStepsSum;
	}

}
