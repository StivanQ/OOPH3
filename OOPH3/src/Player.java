public class Player {

    private String name;
    private Integer score;

    /**
     * @param playerInfo
     */
    public Player (String[] playerInfo) {
        this.name = playerInfo[0];
        this.score = Integer.parseInt(playerInfo[1]);
    }

    /**
     * @return
     */
    public String getName () {
        return name;
    }

    /**
     * @param name
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public Integer getScore () {
        return score;
    }

    /**
     * @param score
     */
    public void setScore (Integer score) {
        this.score = score;
    }

    /**
     * @return
     */
    @Override
    public String toString () {
        return "{name: " + name + ", score: " + score + '}';
    }
}
