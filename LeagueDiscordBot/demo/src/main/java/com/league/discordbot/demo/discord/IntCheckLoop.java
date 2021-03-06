//this runs code at regular intervals and interacts with Riot's API
package com.league.discordbot.demo.discord;

import static java.util.concurrent.TimeUnit.MINUTES;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import com.league.discordbot.demo.Private;
import com.league.discordbot.demo.riot.RitoSummoner;
import com.merakianalytics.orianna.types.common.Region;

public class IntCheckLoop {
    private RitoSummoner ritoSummoner = new RitoSummoner(Region.NORTH_AMERICA, Private.ritoKey);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ArrayList<String> summonerNames = new ArrayList<String>();
    public IntCheckLoop(){
        ritoSummoner.init();
        loop();
    }

    public void loop() {
        final Runnable intCheck = new Runnable() {
            public void run() {
                for(int i = 0; i < summonerNames.size();i++){
                    try {
                        int[] kda = ritoSummoner.lastGameKDA(summonerNames.get(i));
                        if(kda[0]+kda[2]<=kda[1]){
                            BotSystem.defaultChannel.sendMessage(summonerNames.get(i) + " inted. They went " 
                            + kda[0] + "/" + kda[1] + "/" + kda[2]+ " in their last game.").queue();
                        }
                        else{
                            BotSystem.defaultChannel.sendMessage(summonerNames.get(i) + " didn't int their last game. Good job " 
                            + summonerNames.get(i) + "They went " + kda[0] + "/" + kda[1] + "/" + kda[2]
                            + " in their last game.").queue();
                        }
                    }
                    catch(Exception e){
                        BotSystem.defaultChannel.sendMessage("There was an error in calling"
                        + " the API. Maybe the API key is expired. Try updating it.").queue();
                    }
                    
                }
            }
        };

        final ScheduledFuture<?> intCheckHandle = scheduler.scheduleAtFixedRate(intCheck, 0, 10, MINUTES);
    }

    public boolean remove(String summonerString){
        return summonerNames.remove(summonerString);
    }

    public boolean add(String summonerString){
        if  (!summonerNames.contains(summonerString) && 
            ritoSummoner.exists(summonerString))
        {
            summonerNames.add(summonerString);
            return true;
        }
        return false;
    }

    public String SummonerList(){
        return summonerNames.toString();
    }
}
