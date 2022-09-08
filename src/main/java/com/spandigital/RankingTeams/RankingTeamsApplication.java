package com.spandigital.RankingTeams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.spandigital.controller.HandleInput;
import com.spandigital.controller.HandleOutput;
import com.spandigital.exceptions.EmptyException;
import com.spandigital.exceptions.RankingException;
import com.spandigital.model.Team;

public class RankingTeamsApplication {

	public static void main(String[] args) throws RankingException {
		if (args.length == 0) {
			throw new EmptyException("No input to read");
		}

		HandleInput in = new HandleInput();
		HandleOutput out = new HandleOutput();
		
		List<String> list = new ArrayList<>();
		List<Team> teams = new ArrayList<>();
		Map<String, Integer> pointingTableMap = new TreeMap<>();

		list = in.readText(args);
		teams = in.assignTeams(list);
		pointingTableMap = in.assignMatch(teams);

		out.outputText(pointingTableMap);
	}

}
