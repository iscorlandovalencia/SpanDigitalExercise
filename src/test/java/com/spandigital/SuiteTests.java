package com.spandigital;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.spandigital.controller.HandleInputTest;
import com.spandigital.controller.HandleOutputTest;
import com.spandigital.rankingteams.RankingTeamsApplicationTests;

@RunWith(Suite.class)
@SuiteClasses({ HandleInputTest.class, HandleOutputTest.class, RankingTeamsApplicationTests.class })
public class SuiteTests {

}
