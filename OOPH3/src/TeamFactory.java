public class TeamFactory {

    /**
     * @param teamInfo
     * @return
     */
    public static Team getTeamInstance (String[] teamInfo) {

        if (teamInfo[0].contentEquals("football")) {
            return new FootballTeam(teamInfo[0], teamInfo[1], teamInfo[2],
                                    Integer.parseInt(teamInfo[3]));
        } else if (teamInfo[0].contentEquals("basketball")) {
            return new BasketballTeam(teamInfo[0], teamInfo[1], teamInfo[2],
                                      Integer.parseInt(teamInfo[3]));
        } else if (teamInfo[0].contentEquals("handball")) {
            return new HandballTeam(teamInfo[0], teamInfo[1], teamInfo[2],
                                    Integer.parseInt(teamInfo[3]));
        }
        return null;
    }
}
