package Utils;

public class ExecutedTime {

	long time;

	public ExecutedTime() {
		this.time = System.currentTimeMillis();
	}

	public ExecutedTime(long time) {
		this.time = time;
	}

	public void resetTime() {
		this.time = System.currentTimeMillis();
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long watchExecuted() {
		return System.currentTimeMillis() - time;
	}

}
