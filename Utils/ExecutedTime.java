package Utils;

import java.util.concurrent.TimeUnit;

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

	public String watchExecuted() {
		long total = System.currentTimeMillis() - time;
		long h = TimeUnit.MILLISECONDS.toHours(total);
		long m = TimeUnit.MILLISECONDS.toMinutes(total) % 60;
		long s = TimeUnit.MILLISECONDS.toSeconds(total) % 60;
		long ms = total % 1000;
		return "Executed time: " + h + ":" + m + ":" + s + ":" + ms;
	}
	
	public String toString() {
		return this.watchExecuted();
	}

}
