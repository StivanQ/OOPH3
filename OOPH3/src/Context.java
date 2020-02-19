public class Context {

    private Strategy strategy;


    /**
     * @param team
     */
    public void setStrategyDependingOfTeam (Team team) {
        if (team.getTeamType().contentEquals("basketball")) {
            strategy = new BasketballStrategy();
        } else if (team.getTeamType().contentEquals("football")) {
            if (team.getGender().contentEquals("masculin")) {
                strategy = new MaleFootballStrategy();
            } else {
                strategy = new FemaleFootballStrategy();
            }
        } else {
            if (team.getGender().contentEquals("masculin")) {
                strategy = new MaleHandballStrategy();
            } else {
                strategy = new FemaleHandballStrategy();
            }
        }
    }

    /**
     * @param team
     * @return
     */
    public Double executeStrategy (Team team) {

        return strategy.calculatePoints(team);
    }

}
