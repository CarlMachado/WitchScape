package com.bakerystudios.engine;

public class TimeCounter {

	private long start;
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
	}
	
	public boolean satisfied() {
		if(System.currentTimeMillis() - start >= condition) {
			start = -1;
			return true;
		}
		return false;
	}
	
}
