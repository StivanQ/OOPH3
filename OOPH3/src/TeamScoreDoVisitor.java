public class TeamScoreDoVisitor {

    /**
     * @param team
     * @return
     */
    public Double visit (Team team) {
        Context context = new Context();
        context.setStrategyDependingOfTeam(team);
        return context.executeStrategy(team);
    }
}
