package com.spandigital.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.spandigital.exceptions.OddTeamsException;
import com.spandigital.exceptions.RankingException;
import com.spandigital.model.Team;

public interface IInput {
	
	/**
	 * @param args
	 * @return
	 * @description This method reads the text from command line and return it in a list of Strings separated by spaces
	 * Removes commas
	 * @throws IOException
	 */
	public List <String> readText(String [] args);
	
	
	/**
	 * @param text
	 * @description validates if this String is text
	 * @return
	 */
	public boolean validateText(String text);
	
	/**
	 * @param list
	 * @description This method assigns the team from the list to the Team Object with its respective Score6
	 * @return
	 */
	public List <Team> assignTeams(List<String> list);
	
	/**
	 * @param list
	 * @description After having all the list of Teams and its respective scores, confronts Teams one after another.
	 * @return the list of the points of each team depending on the confrontations
	 * @throws OddTeamsException 
	 * @throws RankingException 
	 */
	public Map<String, Integer> assignMatch(List<Team> list) throws RankingException;
	
	/**
	 * @param team
	 * @param team2
	 * @description Confrontation of two teams
	 * A draw (tie) is worth 1 point 
	 * A win is worth 3 points 
	 * A loss is worth 0 points
	 * @return
	 */
	public List<Team> confrontationMatch(Team team, Team team2);
	
}
