package com.casestudy10;

public class Team {
    private String teamName;
    private Player[] players;

    public Team(String teamName, Player[] players) {
        this.teamName = teamName;
        this.players = players;
    }

    public int totalScore() {
        int total = 0;
        for (Player p : players) {
            total += p.getScore();
        }
        return total;
    }

    public Player highestScoringPlayer() {
        Player top = players[0];
        for (Player p : players) {
            if (p.getScore() > top.getScore()) {
                top = p;
            }
        }
        return top;
    }

    public void displayTeam() {
        System.out.println("Team: " + teamName + " | Total Score: " + totalScore());
        System.out.println("Top Player: " + highestScoringPlayer().getName() +
                " | Score: " + highestScoringPlayer().getScore());
    }

    public String getTeamName() {
        return teamName;
    }
}
