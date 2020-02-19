public class MaleFootballStrategy implements Strategy {

    /**
     * @param team
     * @return
     */
    public Double calculatePoints (Team team) {
        return calculateMaxScore(team) + calculateSumOfScores(team);
    }

    /**
     * @param team
     * @return
     */
    private Double calculateMaxScore (Team team) {
        Double max = 0.0;
        for (Player player : team.getPlayerList()) {
            if (player.getScore() > max) {
                max = player.getScore().doubleValue();
            }
        }
        return max;
    }

    /**
     * @param team
     * @return
     */
    private Double calculateSumOfScores (Team team) {
        Double sum = 0.0;
        for (Player player : team.getPlayerList()) {
            sum += player.getScore().doubleValue();
        }
        return sum;
    }
}
