package com.spandigital.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spandigital.exceptions.EmptyException;
import com.spandigital.exceptions.OddTeamsException;
import com.spandigital.exceptions.RankingException;
import com.spandigital.model.Team;
import com.spandigital.service.IInput;

public class HandleInput implements IInput{

	private final Logger logger = LoggerFactory.getLogger(HandleInput.class);
	public Map<String, Integer> rankingTeams = new TreeMap<>();
	
	@Override
	public List <String> readText(String[] args){
		List<String> list = new ArrayList<>();
		
		for(int i = 0; i < args.length; i++) {
			list.add( ( args[i] ).trim().replace(",", "").replace(" ", ""));
		}
		return list;
	}

	@Override
	public List<Team> assignTeams(List<String> list) {
		Team team = new Team();
		List<Team> teams = new ArrayList<>();
		
		if(null == list) {
			throw new NullPointerException("list is null");
		}
		
		logger.info(" *** --- Numeric and Text Validation --- ***");
		for(int currentIterator = 0; currentIterator < list.size(); currentIterator++) {
			String currentString = list.get(currentIterator);
			if( !isNumeric(currentString) && validateText(currentString)) {
				String teamName = team.getName();
				String name = assignName(currentString, teamName);
				logger.info("Assign Name : " + name);
				team.setName( name );
			}if(isNumeric(currentString)){
				logger.info("Assign Result " + currentString);
				team.setResult( Integer.parseInt(currentString) );
				teams.add(team);
				team = new Team();
			}
		}
		
		return teams;
	}

	/**
	 * @param currentString
	 * @param teamName
	 * @description This method is used to concatenate Text if the values are two text 
	 */
	public String assignName(String currentString, String teamName) {
		return (null != teamName && !teamName.equals("")) ? teamName + " " + currentString : currentString;
	}

	@Override
	public boolean validateText(String text) {
		Matcher characterMatcher = Pattern.compile("[a-zA-Z]*").matcher(text);
		logger.info("Text " + text + " is Text? : " + characterMatcher.matches());
		return characterMatcher.matches();
	}

	/**
	 * @param strNum
	 * @return true if validation of strNum is Numeric
	 */
	public boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	    	// Omit the exception only to return false if validation fails.
	        return false;
	    }
	    return true;
	}
	
	@Override
	public Map<String, Integer> assignMatch(List<Team> list) throws RankingException {
		Team team = new Team();
		List<Team> teamList = new ArrayList<>();
		Set<Team> listOfTeams = new HashSet<>(list);
		
		validateNull(list);
		
		validateEmpty(list);
		
		validatePairNumberOfTeams(list);
		
		listOfTeams.forEach(lOTeams -> {
			rankingTeams.put(lOTeams.getName(), 0);
		});
		
		logger.info(" *** --- Confrontations --- ***");
		for (int index = 0; index < list.size(); index++) {
			if (index % 2 != 0) {
				Team team2 = list.get(index);

				logger.info("Match is : " + team.getName() + " " + team.getResult() + " - " + team2.getName() + " "
						+ team2.getResult());
				
				teamList = confrontationMatch(team, team2);
				assignPoints(teamList);

			} else {
				team = list.get(index);
			}
		}
		
		return rankingTeams;
	}

	/**
	 * @param list
	 * @throws OddTeamsException
	 */
	public void validatePairNumberOfTeams(List<Team> list) throws OddTeamsException {
		if(list.size() % 2 != 0) {
			throw new OddTeamsException("Quantity of the teams are not pair,\n"
					+ "Please add pair number of the teams to create the martches!");
		}
	}

	/**
	 * @param list
	 * @throws EmptyException
	 */
	public void validateEmpty(List<Team> list) throws EmptyException {
		if(list.size() == 0) {
			throw new EmptyException("List is Empty");
		}
	}

	/**
	 * @param list
	 */
	public void validateNull(List<Team> list) {
		if(null == list) {
			throw new NullPointerException("List is Null");
		}
	}

	/**
	 * @param teamList
	 */
	public void assignPoints(List<Team> teamList) {
		teamList.stream().forEach(currentTeam -> {
			rankingTeams.forEach((key, value) -> {
				if (key.equals(currentTeam.getName()) ) {
					rankingTeams.put(key, value + currentTeam.getPoints());
				}
			});
		});
	}

	/**
	 * @param team
	 * @param pointingTable
	 * @param team2
	 * @return
	 */
	@Override
	public List<Team> confrontationMatch(Team teamA, Team teamB) {
		List<Team> teams = new ArrayList<>();
		
		if( teamA.getResult() > teamB.getResult()) {
			teamA.setPoints( 3 );
			teamB.setPoints( 0 );
		}if(teamA.getResult() == teamB.getResult() ){
			teamA.setPoints( 1 );
			teamB.setPoints( 1 );
		}if(teamA.getResult() < teamB.getResult() ){
			teamA.setPoints( 0 );
			teamB.setPoints( 3 );
		}
		
		teams.add(teamA);
		teams.add(teamB);
		
		return teams;
	}

}
