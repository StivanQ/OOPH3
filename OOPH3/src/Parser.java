import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public final class Parser {

    private static Parser parserInstance;
    private static FileWriter fileWriter;
    private static StringBuilder stringBuilder;
    private static String[] args;
    private static String taskType;
    private static File inputFileTeamsSignUp;
    private static File inputFileTeamsCompetition;
    private static Scanner teamsSignUpScanner;
    private static Scanner teamsCompetitionScanner;
    private Competition competition;

    /**
     * never used
     */
    private Parser () {
    }

    /**
     * @param args
     */
    private Parser (String[] args) {
        this.args = args;
        instantiateTaskType(args[0]);
        instantiateInputFileTeamsSignUp(args[1]);
        instantiateFileWriter(args[3]);
        instantiateTeamsSignUpScanner(inputFileTeamsSignUp);
        instantiateStingBuilder();
    }

    private static void instantiateTaskType (String taskTypeName) {
        taskType = taskTypeName;
    }

    private static void instantiateInputFileTeamsSignUp (String inputFileName) {
        inputFileTeamsSignUp = new File(inputFileName);
    }

    private static void instantiateFileWriter (String fileName) {
        try {
            fileWriter = new FileWriter(fileName);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static void instantiateTeamsSignUpScanner (File inputFileTeamsSignUp) {
        try {
            teamsSignUpScanner = new Scanner(inputFileTeamsSignUp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void instantiateStingBuilder () {
        stringBuilder = new StringBuilder();
    }

    private static void instantiateTeamsCompetitionScanner (File inputFileTeamsCompetition) {
        try {
            teamsCompetitionScanner = new Scanner(inputFileTeamsCompetition);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     * @return parser instance
     */
    public static Parser getInstance (String[] args) {
        if (parserInstance == null) {
            parserInstance = new Parser(args);
        }
        return parserInstance;
    }

    private static void instantiateInputFileTeamsCompetition (String inputFileName) {
        inputFileTeamsCompetition = new File(inputFileName);
    }

    /**
     * MAgic
     */
    public void doMagic () {
        parseTaskType();
    }

    private void parseTaskType () {
        if (taskType.contentEquals("inscriere")) {
            solveFirstTaskAkaListingTheTeams();
        } else if (taskType.contentEquals("competitie")) {
            solveSecondTaskAkaTheActualCompetition();
        }
    }

    private void solveFirstTaskAkaListingTheTeams () {
        instantiateCompetitionForListing();
        signUpForListing();
        listTeamsInCompetition();
    }

    private void solveSecondTaskAkaTheActualCompetition () {
        instantiateInputFileTeamsCompetition(args[2]);
        signUpForCompetition();
        solveLeaderboard();
        writeAllResults();
    }

    private void instantiateCompetitionForListing () {
        competition = new Competition(null, null);
    }

    private void signUpForListing () {
        while (teamsSignUpScanner.hasNext()) {
            Team teamRead = readTeamLine();
            competition.addTeam(teamRead);
        }
    }

    private void listTeamsInCompetition () {
        competition.getTeamList().forEach(team -> {
            addTeamToStringBuilder(team);
            writeAndFlushToOutputTheStringBuilder();
            clearStringBuilder();
        });
    }

    private void signUpForCompetition () {
        instantiateCompetitionForListing();
        signUpForListing();
        setCompetitionForCompeting();
        moveEligibleTeamsToCompetingList();
    }

    /**
     * @param team
     */
    private void addTeamToCompetitionIfEligible (Team team) {
        if (isEligible(team)) {
            competition.getCompetingTeamList().add(team);
        }
    }

    /**
     * @param team
     * @return
     */
    private boolean isEligible (Team team) {
        return team.getTeamType().contentEquals(competition.getType()) &&
               team.getGender().contentEquals(competition.getGender());
    }

    private void moveEligibleTeamsToCompetingList () {
        while (teamsCompetitionScanner.hasNext()) {
            String teamName = teamsCompetitionScanner.nextLine();
            Team toBeInsertedTeam = competition.searchTeamByName(teamName);
            addTeamToCompetitionIfEligible(toBeInsertedTeam);
        }
    }

    private void setCompetitionForCompeting () {
        instantiateTeamsCompetitionScanner(inputFileTeamsCompetition);
        String competitionInfo = teamsCompetitionScanner.nextLine();
        String[] competitionInfoSplit = competitionInfo.split(", ");
        competition.setCompetitionWithParameters(competitionInfoSplit[0], competitionInfoSplit[1]);
    }

    /**
     * @return
     */
    private Team readTeamLine () {
        String teamLine = teamsSignUpScanner.nextLine();
        String[] teamInfo = teamLine.split(", ");
        Team team = TeamFactory.getTeamInstance(teamInfo);
        readPlayers(team);
        return team;
    }

    /**
     * @param team
     */
    private void addTeamToStringBuilder (Team team) {
        stringBuilder.append(team.toString()).append('\n');
    }

    /**
     * @param team
     */
    private void readPlayers (Team team) {
        for (int i = 0; i < team.getNumberOfPlayers(); i++) {
            String playerLine = readPlayersLine();
            String[] playerInfo = playerLine.split(", ");
            team.addPlayer(new Player(playerInfo));
        }
    }

    private String readPlayersLine () {
        return teamsSignUpScanner.nextLine();
    }


    private void solveLeaderboard () {
        competition.matchAllTeams();
    }

    private void writeAllResults () {
        writeTopThreeResults();
        writeEachTeamResult();
    }

    private void writeTopThreeResults () {
        clearStringBuilder();
        topThreeTeamStringBuilder();
        writeAndFlushToOutputTheStringBuilder();
    }

    /**
     * @param team
     */
    private void eachTeamStringBuilder (Team team) {
        stringBuilder.append(getPlacementString(team));
    }

    /**
     * @param team
     * @return
     */
    private String getPlacementStringForDebuging (Team team) {
        TeamScoreDoVisitor visitor = new TeamScoreDoVisitor();
        return "Echipa " + team.getTeamName() + " a ocupat locul " + team.getPlace() + " cu " +
               "punctele " + team.getPoints() + " si scorul " + team.accept(visitor) + '\n';
    }

    /**
     * @param team
     * @return
     */
    private String getPlacementString (Team team) {
        return "Echipa " + team.getTeamName() + " a ocupat locul " + team.getPlace() + '\n';
    }

    private void writeEachTeamResult () {
        clearStringBuilder();
        competition.getCompetingTeamList().forEach(team -> {
            eachTeamStringBuilder(team);
            writeAndFlushToOutputTheStringBuilder();
            clearStringBuilder();
        });
    }

    private void clearStringBuilder () {
        stringBuilder.delete(0, stringBuilder.length());
    }

    private void topThreeTeamStringBuilder () {
        Team[] teams = competition.getTopThreeTeams();
        for (int i = 0; i < 3; i++) {
            stringBuilder.append(teams[i].getTeamName() + '\n');
        }
    }

    private void writeAndFlushToOutputTheStringBuilder () {
        try {
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
