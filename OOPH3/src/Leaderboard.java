import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {

    private ArrayList<Team> observers;

    /**
     * @param teams
     */
    public Leaderboard (ArrayList<Team> teams) {
        observers = new ArrayList<>(teams);
    }

    public void notifyAllObservers () {
        for (Team team : observers) {
            team.update(getPlace(team));
        }
    }

    /**
     * @param team
     * @return the placement in the leaderboard
     */
    private Integer getPlace (Team team) {
        for (int i = 0; i < observers.size(); i++) {
            if (team == observers.get(i)) {
                return i + 1;
            }
        }
        return -1;
    }

    public void sortLeaderboard () {
        Collections.sort(observers);
        Collections.reverse(observers);
    }

    /**
     * @return
     */
    public Team[] getTopThreeTeamsFromLeaderboard () {
        Team[] topThree = {observers.get(0), observers.get(1), observers.get(2)};
        return topThree;
    }

    /**
     * @param team
     */
    public void attach (Team team) {
        observers.add(team);
    }


}
