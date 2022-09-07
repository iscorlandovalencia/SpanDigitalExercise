package com.spandigital.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spandigital.service.IOutput;

public class HandleOutput implements IOutput {
	
	private final Logger logger = LoggerFactory.getLogger(HandleOutput.class);
	
	public void outputText(Map<String, Integer> map) {
		logger.info("*** --- Teams sorted by points --- ***");
		map = getSortedList(map);
 
		map.forEach((key, value) -> System.out.println(key + ", " + value + " pts"));
	}

	/**
	 * @param map
	 * @return
	 */
	public LinkedHashMap<String, Integer> getSortedList(Map<String, Integer> map) {
		return map.entrySet()
				.stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(Collectors.toMap(
						Map.Entry::getKey, 
						Map.Entry::getValue, 
						(oldValue, newValue) -> oldValue, 
						LinkedHashMap::new));
	}
 
}
