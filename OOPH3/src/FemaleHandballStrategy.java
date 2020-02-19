public class FemaleHandballStrategy implements Strategy {

    /**
     * @param team
     * @return
     */
    public Double calculatePoints (Team team) {
        return calculateProductOfScores(team);
    }

    /**
     * @param team
     * @return
     */
    private Double calculateProductOfScores (Team team) {
        Double product = 1.0;
        for (Player player : team.getPlayerList()) {
            product *= player.getScore();
        }
        return product;
    }
}
