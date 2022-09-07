package com.spandigital.controller;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HandleOutputTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	@Test
	public void given_Teams_From_A_To_E_When_Unsorted_Then_Verify_Equals_Printed_Teams_Are_Sorted_By_Points() {
		var output = new HandleOutput();
		Map<String, Integer> mapIn = new LinkedHashMap<>();
		mapIn.put("Team C", 4);
		mapIn.put("Team B", 5);
		mapIn.put("Team E", 1);
		mapIn.put("Team A", 0);
		mapIn.put("Team D", 6);

		output.outputText(mapIn);
	    assertEquals(
	    		  "Team D, 6 pts\r\n"
	    		+ "Team B, 5 pts\r\n"
	    		+ "Team C, 4 pts\r\n"
	    		+ "Team E, 1 pts\r\n"
	    		+ "Team A, 0 pts\r\n", outContent.toString());
	}
	
	@Test
	public void given_Teams_From_A_To_E_When_Unsorted_Then_Get_Sorted_Values() {
		var output = new HandleOutput();
		Map<String, Integer> mapOut = new LinkedHashMap<>();
		Map<String, Integer> mapIn = new LinkedHashMap<>();
		mapIn.put("Team C", 4);
		mapIn.put("Team B", 5);
		mapIn.put("Team E", 1);
		mapIn.put("Team A", 0);
		mapIn.put("Team D", 6);

		Map<String, Integer> mapToCompare = new LinkedHashMap<>();
		mapToCompare.put("Team D", 6);
		mapToCompare.put("Team B", 5);
		mapToCompare.put("Team C", 4);
		mapToCompare.put("Team E", 1);
		mapToCompare.put("Team A", 0);

		mapOut = output.getSortedList(mapIn);
		
		List<Integer> listC = new ArrayList<>(mapToCompare.values());
		List<Integer> listO = new ArrayList<>(mapOut.values());
		
		assertEquals(listO.get(0), listC.get(0));
		assertEquals(listO.get(1), listC.get(1));
		assertEquals(listO.get(2), listC.get(2));
		assertEquals(listO.get(3), listC.get(3));
		assertEquals(listO.get(4), listC.get(4));

	}
	
	@Test
	public void given_Teams_From_A_To_E_When_Unsorted_And_Value_Is_Repeated_Then_Get_Sorted_Values_Alphabetically() {
		var output = new HandleOutput();
		Map<String, Integer> mapOut = new LinkedHashMap<>();
		Map<String, Integer> mapIn = new LinkedHashMap<>();
		mapIn.put("Team C", 1);
		mapIn.put("Team B", 1);
		mapIn.put("Team E", 1);
		mapIn.put("Team A", 0);
		mapIn.put("Team D", 6);

		Map<String, Integer> mapToCompare = new LinkedHashMap<>();
		mapToCompare.put("Team D", 6);
		mapToCompare.put("Team B", 1);
		mapToCompare.put("Team C", 1);
		mapToCompare.put("Team E", 1);
		mapToCompare.put("Team A", 0);

		mapOut = output.getSortedList(mapIn);
		
		List<Integer> listC = new ArrayList<>(mapToCompare.values());
		List<Integer> listO = new ArrayList<>(mapOut.values());
		
		assertEquals(listO.get(0), listC.get(0));
		assertEquals(listO.get(1), listC.get(1));
		assertEquals(listO.get(2), listC.get(2));
		assertEquals(listO.get(3), listC.get(3));
		assertEquals(listO.get(4), listC.get(4));

	}

}
