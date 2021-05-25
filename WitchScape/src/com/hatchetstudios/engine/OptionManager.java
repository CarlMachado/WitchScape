package com.hatchetstudios.engine;

import java.util.ArrayList;
import java.util.List;

public class OptionManager {
	
	protected int MAX_OPTION;
	
	protected List<Option> options = new ArrayList<>();
	protected Option curOption;
	
	public OptionManager(int MAX_OPTION) {
		this.MAX_OPTION = MAX_OPTION;
	}
	
	public void addOption(Option option) {
		if(options.size() < MAX_OPTION) {
			options.add(option);
		}
		firstOption();
	}
	
	public Option getOption(int index) {
		return options.get(index);
	}
	
	public int optionAmount() {
		return options.size();
	}
	
	public void firstOption() {
		curOption = options.get(0);
	}
	
	public void nextOption() {
		if(curOption.getIndex() < options.size() - 1)
			curOption = options.get(curOption.getIndex() + 1);
	}
	
	public void previousOption() {
		if(curOption.getIndex() > 0)
			curOption = options.get(curOption.getIndex() - 1);
		
	}

	public Option getCurOption() {
		return curOption;
	}

	public void setCurOption(Option curOption) {
		this.curOption = curOption;
	}
	
}
