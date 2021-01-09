package com.league.discordbot.demo.riot;

import java.util.ArrayList;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Participant;
import com.merakianalytics.orianna.types.core.match.ParticipantStats;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class RitoSummoner {
    //initialization variables
    Region region = Region.NORTH_AMERICA;
    String key = "YOUR-KEY-HERE";

    //tracking summoners to reduce riot api calls
    ArrayList<String> userHistory = new ArrayList<String>();
    ArrayList<Summoner> userHistoryCoutner_Part = new ArrayList<Summoner>();

    //Constructor
    public RitoSummoner(Region region, String key){
        this.region = region;
        this.key = key;
    }

    //Initialization
    public void init(){
        Orianna.setRiotAPIKey(key);
        Orianna.setDefaultRegion(region);
    }

    //NOTE: A lot of the get methods are for reducing Riot API calls as much as possible

    //gets the summoner; followed example code here
    public Summoner getSummoner(String SummonerName){
        if(newSummoner(SummonerName)){
            userHistory.add(SummonerName);
            userHistoryCoutner_Part.add(Orianna.summonerNamed(SummonerName).get());
        }
        Summoner currentSummoner = userHistoryCoutner_Part
                .get(userHistory.indexOf(SummonerName));
        return currentSummoner;
    }

    //gets the match history of the summoner
    public MatchHistory getMatchHistory(String SummonerName){
        Summoner currentSummoner = getSummoner(SummonerName);
        return currentSummoner.matchHistory().get();
    }

    //gets the stats of a summoner during the LAST game played
    //for matchHistory indexes, 0 is last game played, and 1 is the game played before that
    public ParticipantStats getLastMatchStats(String SummonerName){
        Summoner currentSummoner = getSummoner(SummonerName);
        MatchHistory matchHistory = getMatchHistory(SummonerName);

        ParticipantStats SummonerStats = matchHistory.get(0).getParticipants().find(currentSummoner).getStats();
        
        return SummonerStats;
    }

    public boolean exists(String SummonerName){
        Summoner currentSummoner = getSummoner(SummonerName);
        return currentSummoner.exists();
    }

    // checks to see if summoner inted their LAST game
    // not useful after writing last game KDA
    public boolean inted(String SummonerName){
        ParticipantStats SummonerStats = getLastMatchStats(SummonerName);

        return SummonerStats.getAssists() + SummonerStats.getKills() <= SummonerStats.getDeaths();
    }

    //gets the KDA of the player in their last game as a list
    public int[] lastGameKDA(String SummonerName) {
        ParticipantStats SummonerStats = getLastMatchStats(SummonerName);
        return new int[] {SummonerStats.getKills(),SummonerStats.getDeaths(),SummonerStats.getAssists()};
    }

    //checks to see if the summoner is new to the database

    public boolean newSummoner(String SummonerName){
        for (int i = 0; i < userHistory.size(); i++) {
            if (userHistory.get(i).equals(SummonerName)) {
                return false;
            }
        }
        return true;
    }

}
