package com.spandigital.RankingTeams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.spandigital.exceptions.EmptyException;
import com.spandigital.exceptions.RankingException;

public class RankingTeamsApplicationTests {

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
	public void given_List_Of_Args_When_Null_Then_Throw_EmptyException(){
		String listOfArgs [] = {};
		assertThrows(EmptyException.class, ()->{
			RankingTeamsApplication.main(listOfArgs);
		});
	}
	
	@Test
	public void given_Teams_From_A_To_E_When_Unsorted_Then_Verify_Equals_Printed_Teams_Are_Sorted_By_Points() throws RankingException {
		String listOfArgs [] = {
		"Team", " C", "4",
		"Team", " B", "5",
		"Team", " E", "1",
		"Team", " A", "0",
		"Team", " D", "6",
		"Team", " F", "3"
		};
		RankingTeamsApplication.main(listOfArgs);
	    assertEquals(
	    		  "Team B, 3 pts\r\n"
	    		+ "Team D, 3 pts\r\n"
	    		+ "Team E, 3 pts\r\n"
	    		+ "Team A, 0 pts\r\n"
	    		+ "Team C, 0 pts\r\n"
	    		+ "Team F, 0 pts\r\n", outContent.toString());
	}
	
	@Test
	public void given_Two_Journeys_When_Confrontations_Then_Verify_Print_Correct_Points() throws RankingException {
		String listOfArgs [] = {
		"Team", " C", "2",
		"Team", " B", "2",
		"Team", " E", "1",
		"Team", " A", "0",
		"Team", " D", "0",
		"Team", " F", "3",
		
		"Team", " A", "2",
		"Team", " D", "1",
		"Team", " B", "2",
		"Team", " E", "2",
		"Team", " C", "1",
		"Team", " F", "1"
		};
		RankingTeamsApplication.main(listOfArgs);
	    assertEquals(
	    		  "Team E, 4 pts\r\n"
	    		+ "Team F, 4 pts\r\n"
	    		+ "Team A, 3 pts\r\n"
	    		+ "Team B, 2 pts\r\n"
	    		+ "Team C, 2 pts\r\n"
	    		+ "Team D, 0 pts\r\n", outContent.toString());
	}
}
