package com.casestudy10;

public class TestSports {
    public static void main(String[] args) {
        Player[] team1Players = {
                new Player("Mucyo", 20),
                new Player("Muhire", 35),
                new Player("Mukiza", 15)
        };

        Player[] team2Players = {
                new Player("Mugisha", 25),
                new Player("Jean", 30),
                new Player("Alice", 20)
        };

        Team team1 = new Team("Team A", team1Players);
        Team team2 = new Team("Team B", team2Players);

        Team[] teams = {team1, team2};

        Team highestTeam = teams[0];
        for (Team t : teams) {
            t.displayTeam();
            if (t.totalScore() > highestTeam.totalScore()) {
                highestTeam = t;
            }
            System.out.println();
        }

        System.out.println("Team with highest total score: " + highestTeam.getTeamName() +
                " | Score: " + highestTeam.totalScore());
    }
}
