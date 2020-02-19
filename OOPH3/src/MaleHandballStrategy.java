public class MaleHandballStrategy implements Strategy {

    /**
     * @param team
     * @return
     */
    public Double calculatePoints (Team team) {
        return calculateSumOfScores(team);
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
