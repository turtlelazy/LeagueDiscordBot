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
                    int[] kda = ritoSummoner.lastGameKDA(summonerNames.get(i));
                    if(kda[0]+kda[2]<=kda[1]){
                        BotSystem.defaultChannel.sendMessage(summonerNames.get(i) + " inted. They went " 
                        + kda[0] + "/" + kda[1] + "/" + kda[2]+ " in their last game.").queue();
                    }
                    else{
                        BotSystem.defaultChannel.sendMessage(summonerNames.get(i) + " didn't int their last game. Good job " 
                        + summonerNames.get(i)).queue();
                    }
                    
                }
            }
        };

        final ScheduledFuture<?> intCheckHandle = scheduler.scheduleAtFixedRate(intCheck, 0, 10, MINUTES);
    }

    public void remove(String summonerString){
        summonerNames.remove(summonerString);
    }

    public void add(String summonerString){
        summonerNames.add(summonerString);
    }
}
