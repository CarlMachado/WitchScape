package com.hatchetstudios.engine.animation;

public class AnimationCounter {
	
	private int frames, index;
	public final int MAX_FRAMES, MAX_INDEX;
	private boolean moved, movement;
	
	public AnimationCounter(int MAX_FRAMES, int MAX_INDEX, boolean movement) {
		this.frames = 0;
		this.MAX_FRAMES = MAX_FRAMES;
		this.index = 0;
		this.MAX_INDEX = MAX_INDEX;
		this.movement = movement;
		moved = !movement;
	}
	
	public AnimationCounter(int MAX_FRAMES) {
		this.frames = 0;
		this.MAX_FRAMES = MAX_FRAMES;
		MAX_INDEX = 0;
	}

	public int getIndex() {return index;}
	
	public void animation() {
		if(moved) {
			frames++;
			if(frames > MAX_FRAMES) {
				frames = 0;
				index++;
				if(index == MAX_INDEX) index = 0;
			}
		}
		if(movement) moved = false;
	}
	
	public boolean frameCounter() {
		frames++;
		if(frames > MAX_FRAMES) {
			frames = 0;
			return false;
		}
		return true;
	}
	
	public void setMoved(boolean moved) {this.moved = moved;}
	public boolean getMoved() {return moved;}
	
}
