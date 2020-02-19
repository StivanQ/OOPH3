public class BasketballStrategy implements Strategy {

    /**
     * @param team
     * @return
     */
    public Double calculatePoints (Team team) {
        return calculateMeanOfScores(team);
    }

    /**
     * @param team
     * @return
     */
    private Double calculateMeanOfScores (Team team) {
        Double sum = 0.0;
        for (Player player : team.getPlayerList()) {
            sum += player.getScore();
        }
        return (sum / team.numberOfPlayers);
    }
}
