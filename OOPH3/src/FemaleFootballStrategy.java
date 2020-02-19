public class FemaleFootballStrategy implements Strategy {

    /**
     * @param team
     * @return
     */
    public Double calculatePoints (Team team) {
        return calculateMinScore(team) + calculateSumOfScores(team);
    }

    /**
     * @param team
     * @return
     */
    private Double calculateMinScore (Team team) {
        Double min = team.getPlayerList().get(0).getScore().doubleValue();
        for (Player player : team.getPlayerList()) {
            if (player.getScore() < min) {
                min = player.getScore().doubleValue();
            }
        }
        return min;
    }

    /**
     * @param team
     * @return
     */
    private Double calculateSumOfScores (Team team) {
        Double sum = 0.0;
        for (Player player : team.getPlayerList()) {
            sum += player.getScore();
        }
        return sum;
    }
}
