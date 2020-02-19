import java.util.ArrayList;

public class Competition {

    private String type;
    private String gender;
    private ArrayList<Team> teamList;
    private ArrayList<Team> competingTeamList;
    private Leaderboard leaderboard;
    private Context pointsCalculator;

    /**
     * @param type
     * @param gender
     */
    public Competition (String type, String gender) {
        this.type = type;
        this.gender = gender;
        teamList = new ArrayList<>();
        competingTeamList = new ArrayList<>();
    }

    /**
     * @param team
     */
    public void addTeam (Team team) {
        teamList.add(team);
    }

    /**
     * @return
     */
    public ArrayList<Team> getTeamList () {

        return teamList;
    }

    /**
     * @return
     */
    public String getType () {

        return type;
    }

    /**
     * @return
     */
    public String getGender () {
        return gender;
    }

    /**
     * @return
     */
    public ArrayList<Team> getCompetingTeamList () {
        return competingTeamList;
    }

    /**
     * @param type
     * @param gender
     */
    public void setCompetitionWithParameters (String type, String gender) {
        this.type = type;
        this.gender = gender;
    }

    public void matchAllTeams () {
        leaderboard = new Leaderboard(competingTeamList);
        for (int i = 0; i < competingTeamList.size() - 1; i++) {
            for (int j = i + 1; j < competingTeamList.size(); j++) {
                matchTwoTeams(competingTeamList.get(i), competingTeamList.get(j));
            }
        }
    }

    private void matchTwoTeams (Team firstTeam, Team secondTeam) {
        TeamScoreDoVisitor visitor = new TeamScoreDoVisitor();
        if (firstTeam.accept(visitor) > secondTeam.accept(visitor)) {
            teamWonMatch(firstTeam);
        } else if (firstTeam.accept(visitor) < secondTeam.accept(visitor)) {
            teamWonMatch(secondTeam);
        } else {
            teamTieMatch(firstTeam);
            teamTieMatch(secondTeam);
        }
        leaderboard.sortLeaderboard();
        leaderboard.notifyAllObservers();
    }

    private void teamWonMatch (Team team) {
        team.setPoints(team.getPoints() + 3);
    }

    private void teamTieMatch (Team team) {
        team.setPoints(team.getPoints() + 1);
    }

    /**
     * @return the top 3 teams
     */
    public Team[] getTopThreeTeams () {
        return leaderboard.getTopThreeTeamsFromLeaderboard();
    }

    /**
     * @param name
     * @return team by name
     */
    public Team searchTeamByName (String name) {
        for (Team team : teamList) {
            if (team.getTeamName().contentEquals(name)) {
                return team;
            }
        }
        return null;
    }
}
