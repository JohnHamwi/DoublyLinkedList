/**
 * This class serves as the entrypoint for the entire application.
 *
 * @author Zach Kissel
 * <p>
 * The code was completed by
 * @author Student Name
 */

import java.io.*;
import java.util.Scanner;

import list.DoubleLinkedList;

public class DodgeballDraft {
    /**
     * Return true if the candidate with the given name does
     * not have a match.
     *
     * @param candidates the array of candidates to check for mathces.
     * @param nm is the name of the candidate to check.
     * @return true if the candidate with the given name does *not*
    have
     * a match.
     */
    public static boolean isFree(Candidate[] candidates, String nm) {
        for(Candidate candidate: candidates){
            if (candidate.getName().equalsIgnoreCase(nm))
                return candidate.getCurrentMatch() == null;
        }
        return false;
    }

    /**
     * Sets the match of the specified candidate to match. The function
     ensures
     * that and the player previous matching is removed (i.e., update
     the old
     * team so that its candidate is "").
     *
     * @param teams the array of team candidates.
     * @param players the array of player candidates
     * @param playerName the name of the player to update.
     * @param teamName the name of the team to update.
     */
    public static void match(Candidate[] teams, Candidate[] players,
                             String playerName, String teamName) {
        int tIndex =0,pIndex=0;
        for(;tIndex<teams.length;tIndex++){
            if(teams[tIndex].getName().equals(teamName) == true)
                break;
        }

        for(;pIndex<players.length;pIndex++){
            if(players[pIndex].getName().equals(playerName) == true)
                break;
        }

        if(isFree(players,playerName) == false){
            String currentMatched = players[pIndex].getCurrentMatch();
            int currMIndex = 0;
            for(;currMIndex<teams.length;currMIndex++){
                if(teams[currMIndex].getName().equals(currentMatched))
                    break;
            }

            teams[currMIndex].setCurrentMatch(null);
        }
        players[pIndex].setCurrentMatch(teamName);
        teams[tIndex].setCurrentMatch(playerName);
    }

    /**
     * Return true if the candidate prefers the current choice over
     * its existing match.
     *
     * @param candidates the array of candidates.
     * @param nm the name of the candidate to check the preference of.
     * @param offer the name of the new offer.
     * @return true if {@code nm} prefers {@code offer} over its
    current
     * assignment; otherwise, false.
     */
    public static boolean preferCandidate(Candidate[] candidates,
                                          String nm,
                                          String offer) {

        for(int i=0;i<candidates.length;i++){
            if(candidates[i].getName().equals(nm)){
                String currMatched = candidates[i].getCurrentMatch();
                if(candidates[i].getPriorityOf(offer) < candidates[i].getPriorityOf(currMatched))
                    return true;
            }
        }
        return false;
    }

    /**
     * Runs the stable match on the lists of people to find the
     * best pairing.
     *
     * @param players the array of player candidates.
     * @param teams the array of team candidates.
     */
    public static void findStableMatch(Candidate[] players, Candidate[] teams) {

        //Parallel array to store the priority numbers of each team that it has
        //proposed for contract before.
        int proposedBefore[] = new int[teams.length];
        int maxMatches = teams.length;
        int currentMatched = 0;
        while(currentMatched < maxMatches){
            int freeTeamIndex=0, wishPlayerIndex=0;

            //Get index of free team.
            for(;freeTeamIndex< teams.length;freeTeamIndex++){
                boolean isTFree = isFree(teams,teams[freeTeamIndex].getName());
                if(isTFree == true)
                    break;
            }

            //Get name of free team
            String freeTname = teams[freeTeamIndex].getName();

            //While this team doesn't find match continue.
            while(isFree(teams,teams[freeTeamIndex].getName()) == true){

                //Find name of highest priority players that freeTeam has not proposed to yet
                String wishPlayer = teams[freeTeamIndex].getChoice(proposedBefore[freeTeamIndex] + 1);
                proposedBefore[freeTeamIndex] = proposedBefore[freeTeamIndex] + 1;

                //Check if player is free
                boolean isPFree = isFree(players,wishPlayer);
                boolean freeTPriority = false;

                //If player is not free, get priority of free team as compared to the
                //priority of team that the player is currently matched with.
                if(isPFree == false )
                {
                   freeTPriority = preferCandidate(players,wishPlayer,freeTname);
                }

                //If player is free or priority of current free team is more than the
                //priority of currentMatched team with player, then create match.
                if(isPFree == true || freeTPriority == true){
                    match(teams,players,wishPlayer,freeTname);
                    if(isPFree==true)
                        currentMatched++;
                }
            }


        }



    }

    /**
     * Displays the candidates and their matchings.
     *
     * @param candidates and array of candidates to display.
     */
    public static void printMatches(Candidate[] candidates) {
            for (int i=0;i<candidates.length;i++){
                String res = candidates[i].toString();
                System.out.println(res);
            }
    }

    /**
     * Saves the match results to a file.
     *
     * @param candidates that we should write to the file.
     * @param fname the name of the file to write to.
     */
    public static void saveMatches(Candidate[] candidates, String fname) throws IOException {
        BufferedWriter fileWrite = new BufferedWriter( new FileWriter (new File(fname)) );
        fileWrite.newLine();
        fileWrite.write(" --------- Matches ----------");
        fileWrite.newLine();
        for(int i=0;i<candidates.length;i++){
            fileWrite.write(" [" + (i+1) + "] " +candidates[i].toString());
            fileWrite.newLine();
        }

        fileWrite.close();

    }

    /**
     * Reads the candidate information from the file.
     *
     * @parm fname the file to read the candidates from.
     * @return an array of Candidates read from the file.
     */
    public static Candidate[] loadCandidates(String fname) {
        DoubleLinkedList<Candidate> candidateList = new
                DoubleLinkedList<>();
        String currLine;
        boolean readRankings;
        int choiceCount;
        try {
            Scanner in = new Scanner(new File(fname));
            // Read every line.
            while (in.hasNextLine()) {
                Candidate tmp = new Candidate();
                String nm = in.nextLine();
                tmp.setName(nm);
                readRankings = true;
                choiceCount = 1;
                while (in.hasNextLine() && readRankings) {
                    currLine = in.nextLine();
                    if (currLine.equals(".")) {
                        readRankings = false;
                        candidateList.insert(candidateList.getLength() + 1,
                                tmp);
                    } else
                        tmp.addChoice(choiceCount++, currLine);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Could not load candidate list.");
            ex.printStackTrace();
            System.exit(1);
        }
        // Create an array.
        Candidate[] carray = new Candidate[candidateList.getLength()];
        Object[] array = candidateList.toArray();
        for (int i = 0; i < candidateList.getLength(); i++)
            carray[i] = (Candidate) array[i];
        return carray;
    }

    /**
     * The entry point.
     *
     * @param args the command line arguemnts.
     */
    public static void main(String[] args) {
        Candidate[] players;
        Candidate[] teams;

        players = loadCandidates("players2.txt");
        teams = loadCandidates("teams2.txt");
        findStableMatch(players,teams);
        printMatches(players);
        try {
            saveMatches(players,"output2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }
}