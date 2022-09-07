package com.spandigital.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.spandigital.exceptions.EmptyException;
import com.spandigital.exceptions.OddTeamsException;
import com.spandigital.exceptions.RankingException;
import com.spandigital.model.Team;

public class HandleInputTest {

	private static final String ARG1 = "arg1";
	private static final String TEAM_B = "Team B";
	private static final String TEAM_A = "Team A";
	
	/* **************************
	 * readText(String args[])
	 * **************************/
	@Test
	public void given_List_Of_Strings_When_Exist_Args_Then_Return_ArraysList() {
		var input = new HandleInput();
		String listOfArgs [] = {ARG1, "arg2", "arg3", "arg4", "arg5", "arg6"};
		assertEquals(input.readText(listOfArgs).getClass(), ArrayList.class);
	}
	
	@Test
	public void given_List_Of_Strings_With_6_Args_When_Exist_Args_Then_The_Size_Of_ArrayList_Is_6() {
		var input = new HandleInput();
		String listOfArgs [] = {ARG1, "arg2", "arg3", "arg4", "arg5", "arg6"};
		assertEquals(input.readText(listOfArgs).size(), 6);
	}
	
	@Test
	public void given_List_Of_Strings_When_Exist_Commas_Then_Non_Of_The_Args_Contains_Commas() {
		var input = new HandleInput();
		List<String> list = new ArrayList<>();
		String listOfArgs [] = {"arg1,"};
		list = input.readText(listOfArgs);
		assertEquals(ARG1, list.get(0));
	}
	
	@Test
	public void given_List_Of_Strings_When_Exist_Multiple_Commas_Then_Non_Of_The_Args_Contains_Commas() {
		var input = new HandleInput();
		List<String> list = new ArrayList<>();
		String listOfArgs [] = {",,a,rg1,"};
		list = input.readText(listOfArgs);
		assertEquals(ARG1, list.get(0));
	}
	
	/* **************************
	 * assignTeams(List<String> list)
	 * **************************/
	@Test
	public void given_List_Of_Strings_When_Null_List_Then_Throws_NullPointerException() {
		var input = new HandleInput();
		List<String> list = null;
		assertThrows(NullPointerException.class, () -> {
			input.assignTeams(list);
		});
	}
	
	@Test
	public void given_List_Of_Strings_When_Not_Null_List_Then_Not_Throws_NullPointerException() {
		var input = new HandleInput();
		List<String> list = new ArrayList<>();
		input.assignTeams(list);
		assertNotNull(list);
	}
	
	@Test
	public void given_List_Of_Strings_When_Not_Null_Then_Return_List_Of_Teams() {
		var input = new HandleInput();
		List<String> list = new ArrayList<>();
		list.add("teamA");
		list.add("teamB");
		list.add("teamC");
		assertEquals(input.assignTeams(list).getClass(), ArrayList.class);
	}
	
	@Test
	public void given_Two_Strings_When_One_Is_Not_Numeric_And_Other_Is_Text_Then_Return_List_Of_Teams() {
		var input = new HandleInput();
		List<String> list = new ArrayList<>();
		list.add("teamA");
		list.add("teamB");
		assertEquals(input.assignTeams(list).getClass(), ArrayList.class);
	}
	
	@Test
	public void given_Two_Strings_When_One_Is_Numeric_And_Other_Is_Text_Then_Return_List_Of_Teams() {
		var input = new HandleInput();
		List<String> list = new ArrayList<>();
		list.add("4");
		list.add("teamB");
		assertEquals(input.assignTeams(list).getClass(), ArrayList.class);
	}
	
	@Test
	public void given_Two_Strings_When_Two_Are_Numeric_Then_Return_List_Of_Teams() {
		var input = new HandleInput();
		List<String> list = new ArrayList<>();
		list.add("4");
		list.add("6");
		assertEquals(input.assignTeams(list).getClass(), ArrayList.class);
	}
	
	@Test
	public void given_Two_Strings_When_One_Is_Text_And_Other_Is_Numeric_Then_Return_List_Of_Teams() {
		var input = new HandleInput();
		List<String> list = new ArrayList<>();
		list.add("teamA");
		list.add("3");
		assertEquals(input.assignTeams(list).getClass(), ArrayList.class);
	}

	/* **************************
	 * isNumeric(String)
	 * **************************/
	@Test
	public void given_A_String_When_Is_Text_Return_False() {
		var input = new HandleInput();
		String text = "Text";
		assertFalse(input.isNumeric(text));
	}
	
	@Test
	public void given_A_String_When_Is_Numeric_Return_True() {
		var input = new HandleInput();
		String text = "45";
		assertTrue(input.isNumeric(text));
	}
	
	@Test
	public void given_A_String_When_Is_Null_Return_False() {
		var input = new HandleInput();
		String text = null;
		assertFalse(input.isNumeric(text));
	}

	/* **************************
	 * assignName(String currentString, String teamName)
	 * **************************/
	@Test
	public void given_Two_Texts_A_And_B_When_Assign_Name_Return_B_A_Concatenated() {
		var input = new HandleInput();
		String textA = "A";
		String textB = "B";
		assertEquals("B A", input.assignName(textA, textB));
	}
	
	@Test
	public void given_TextA_And_Empty_String_When_Assign_Name_Return_A() {
		var input = new HandleInput();
		String textA = "A";
		String emptyText = "";
		assertEquals("A", input.assignName(textA, emptyText));
	}
	
	/* **************************
	 * confrontationMatch(Team teamA, Team teamB)
	 * **************************/
	@Test
	public void given_TeamA_And_TeamB_When_Team_A_Win_Assign_3_Points_To_TeamA() {
		var input = new HandleInput();
		Team teamA = new Team();
		Team teamB = new Team();
		List<Team> teams = new ArrayList<>();
		
		teamA.setName(TEAM_A);
		teamA.setPoints(0);
		teamA.setResult(3);
		
		teamB.setName(TEAM_B);
		teamB.setPoints(0);
		teamB.setResult(1);
		
		teams = input.confrontationMatch(teamA, teamB);
		
		assertEquals(3, teams.get(0).getPoints());
		assertEquals(0, teams.get(1).getPoints());
	}
	
	@Test
	public void given_TeamA_And_TeamB_When_Team_A_Draws_Vs_TeamB_Assign_1_Points_To_Each_Team() {
		var input = new HandleInput();
		Team teamA = new Team();
		Team teamB = new Team();
		List<Team> teams = new ArrayList<>();
		
		teamA.setName(TEAM_A);
		teamA.setPoints(0);
		teamA.setResult(2);
		
		teamB.setName(TEAM_B);
		teamB.setPoints(0);
		teamB.setResult(2);
		
		teams = input.confrontationMatch(teamA, teamB);
		
		assertEquals(1, teams.get(0).getPoints());
		assertEquals(1, teams.get(1).getPoints());
	}
	
	@Test
	public void given_TeamA_And_TeamB_When_Team_A_Lose_Assign_3_Points_To_TeamB() {
		var input = new HandleInput();
		Team teamA = new Team();
		Team teamB = new Team();
		List<Team> teams = new ArrayList<>();
		
		teamA.setName(TEAM_A);
		teamA.setPoints(0);
		teamA.setResult(0);
		
		teamB.setName(TEAM_B);
		teamB.setPoints(0);
		teamB.setResult(4);
		
		teams = input.confrontationMatch(teamA, teamB);
		
		assertEquals(0, teams.get(0).getPoints());
		assertEquals(3, teams.get(1).getPoints());
	}
	
	/* **************************
	 * assignPoints(List<Team> teamList)
	 * **************************/
	@Test
	public void given_TeamA_When_Has_3_Matches_With_Each_Result_Return_4_Points() {
		var input = new HandleInput();
		
		Team teamA1 = new Team();
		Team teamB = new Team();
		Team teamA2 = new Team();
		Team teamA3 = new Team();
		List<Team> teams = new ArrayList<>();
		
		teamA1.setName(TEAM_A);
		teamA1.setPoints(0);
		teamA1.setResult(0);
		
		teamB.setName(TEAM_B);
		teamB.setPoints(0);
		teamB.setResult(4);		
		
		teamA2.setName(TEAM_A);
		teamA2.setPoints(1);
		teamA2.setResult(0);
		
		teamA3.setName(TEAM_A);
		teamA3.setPoints(3);
		teamA3.setResult(0);
		
		teams.add(teamA1);
		teams.add(teamB);
		teams.add(teamA2);
		teams.add(teamA3);
		
		teams.forEach(lOTeams -> {
			input.rankingTeams.put(lOTeams.getName(), 0);
		});
		
		input.assignPoints(teams);
		//Get the value from Team Name that is the Key
		int points = input.rankingTeams.get(TEAM_A);
		assertEquals(4, points);
	}
	
	/* **************************
	 * assignMatch(List<Team> teamList)
	 * **************************/
	@Test
	public void given_TeamA_TeamB_TeamC_TeamD_When_Confrontations_Return_rankingTeams() throws RankingException {
		var input = new HandleInput();
		Map<String, Integer> pointingTableMap = new TreeMap<>();
		Team teamA = new Team();
		Team teamB = new Team();
		Team teamC = new Team();
		Team teamD = new Team();
		List<Team> teams = new ArrayList<>();
		
		teamA.setName(TEAM_A);
		teamA.setPoints(0);
		teamA.setResult(3);
		
		teamB.setName(TEAM_B);
		teamB.setPoints(0);
		teamB.setResult(4);		
		
		teamC.setName("Team C");
		teamC.setPoints(0);
		teamC.setResult(1);
		
		teamD.setName("Team D");
		teamD.setPoints(0);
		teamD.setResult(1);
		
		teams.add(teamA);
		teams.add(teamB);
		teams.add(teamC);
		teams.add(teamD);
		
		teams.forEach(lOTeams -> {
			input.rankingTeams.put(lOTeams.getName(), 0);
		});
		
		pointingTableMap = input.assignMatch(teams);
		//Get the value from Team Name that is the Key
		int pointsA = pointingTableMap.get(TEAM_A);
		assertEquals(0, pointsA);
		
		int pointsB = pointingTableMap.get(TEAM_B);
		assertEquals(3, pointsB);
		
		int pointsC = pointingTableMap.get("Team C");
		assertEquals(1, pointsC);
		
		int pointsD = pointingTableMap.get("Team D");
		assertEquals(1, pointsD);
	}
	
	@Test
	public void given_Odd_Number_Of_Teams_When_Confrontations_Then_Throws_OddTeamsException() throws RankingException {
		var input = new HandleInput();
		Team teamA = new Team();
		Team teamB = new Team();
		Team teamC = new Team();
		List<Team> teams = new ArrayList<>();
		
		teamA.setName(TEAM_A);
		teamA.setPoints(0);
		teamA.setResult(3);
		
		teamB.setName(TEAM_B);
		teamB.setPoints(0);
		teamB.setResult(4);		
		
		teamC.setName("Team C");
		teamC.setPoints(0);
		teamC.setResult(1);
		
		teams.add(teamA);
		teams.add(teamB);
		teams.add(teamC);
		
		assertThrows(OddTeamsException.class, () -> {
			input.validatePairNumberOfTeams(teams);
		});
	}
	
	@Test
	public void given_Null_Teams_When_Confrontations_Then_Throws_NullPointerException() throws RankingException {
		var input = new HandleInput();
		
		List<Team> teams = null;
		
		assertThrows(NullPointerException.class, () -> {
			input.validateNull(teams);
		});
	}
	
	@Test
	public void given_Empty_Teams_When_Confrontations_Then_Throws_EmptyException() throws RankingException {
		var input = new HandleInput();
		
		List<Team> teams = new ArrayList<>();
		
		assertThrows(EmptyException.class, () -> {
			input.validateEmpty(teams);
		});
	}
	
}
