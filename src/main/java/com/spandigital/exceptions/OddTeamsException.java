package com.spandigital.exceptions;


/**
 * @author Orlando.Valencia
 * @description Quantity of the teams are not pair
 */
public class OddTeamsException extends RankingException{
	
	private static final long serialVersionUID = 1L;

	public OddTeamsException(String message) {
		super(message);
	}
}
