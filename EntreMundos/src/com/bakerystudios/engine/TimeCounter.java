package com.bakerystudios.engine;

public class TimeCounter {

	private long start;
	private long elapsed = 0;
	private long condition;
	
	public TimeCounter(long condition) {
		this.condition = condition;
		start = -1;
	}
	
	public void count() {
		if(start == -1) {
			start = System.currentTimeMillis();
			return;
		}
		elapsed += System.currentTimeMillis() - start;
		//System.out.println(elapsed);
	}
	
	public boolean satisfied() {
		if(elapsed >= condition) {
			start = -1;
			elapsed = 0;
			return true;
		}
		return false;
	}
	
}
