package com.spandigital.service;

import java.io.IOException;
import java.util.Map;

public interface IOutput {
	
	/**
	 * @param list
	 * @return The total of Teams sorted by more points and alphabetically 6 
	 * @throws IOException
	 */
	public void outputText(Map<String, Integer> list);
	
}
