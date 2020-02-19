import java.util.ArrayList;

public abstract class Team implements Comparable<Team> {
    protected String teamType;
    protected String teamName;
    protected String gender;
    protected Integer numberOfPlayers;
    protected ArrayList<Player> playerList;
    protected Integer points;
    protected Integer place;

    /**
     * @param teamType
     * @param teamName
     * @param gender
     * @param numberOfPlayers
     */
    public Team (String teamType, String teamName, String gender, Integer numberOfPlayers) {
        this.teamType = teamType;
        this.teamName = teamName;
        this.gender = gender;
        this.numberOfPlayers = numberOfPlayers;
        this.points = 0;
        playerList = new ArrayList<>(numberOfPlayers);
    }

    /**
     * @return
     */
    public Integer getPoints () {
        return points;
    }

    /**
     * @param points
     */
    public void setPoints (Integer points) {
        this.points = points;
    }

    /**
     * @return
     */
    public Integer getPlace () {
        return place;
    }

    /**
     * @param newPlace
     */
    public void update (Integer newPlace) {
        place = newPlace;
    }

    /**
     * @return
     */
    public String getTeamName () {
        return teamName;
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
    public String getTeamType () {
        return teamType;
    }

    /**
     * @return
     */
    public Integer getNumberOfPlayers () {
        return numberOfPlayers;
    }

    /**
     * @return
     */
    public ArrayList<Player> getPlayerList () {
        return playerList;
    }

    /**
     * @param player
     */
    public void addPlayer (Player player) {
        playerList.add(player);
    }

    /**
     * @return
     */
    @Override
    public String toString () {
        String string =
                "{" + "teamName: " + teamName + ", gender: " + gender + ", " + "numberOfPlayers: " +
                numberOfPlayers + ", players: [";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        appendPlayersToStringBuilder(stringBuilder);
        stringBuilder.append("]}");

        return stringBuilder.toString();
    }

    private void appendPlayersToStringBuilder (StringBuilder stringBuilder) {
        for (int i = 0; i < numberOfPlayers - 1; i++) {
            stringBuilder.append(playerList.get(i));
            stringBuilder.append(", ");
        }
        stringBuilder.append(playerList.get(numberOfPlayers - 1));
    }

    /**
     * @param visitor
     * @return
     */
    public Double accept (TeamScoreDoVisitor visitor) {
        return visitor.visit(this);
    }

    /**
     * @param o
     * @return
     */
    @Override
    public int compareTo (Team o) {
        if (this.points < o.points) {
            return -1;
        } else if (this.points > o.points) {
            return 1;
        }
        return 0;
    }
}
