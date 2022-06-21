package com.athena.judge.entity;

public class ClassCounter {
	
	int init;

	public int getInit() {
		return init;
	}

	public void setInit(int init) {
		this.init = init;
	}
	 
	public int incrementAndGet()
	{
		init += 1;
		return init;
	}
}
