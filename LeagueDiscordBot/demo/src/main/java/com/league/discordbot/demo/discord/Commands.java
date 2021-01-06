package com.league.discordbot.demo.discord;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
    String prefix = "!";
    BotSystem botSystem = new BotSystem();
    public IntCheckLoop intCheckLoop = new IntCheckLoop();
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if((prefix + "setchannel").equalsIgnoreCase(args[0])){
            botSystem.changeDefaultChannel(event.getChannel());
            BotSystem.defaultChannel.sendMessage("channel set!").queue();
        }

        if ((prefix + "add").equalsIgnoreCase(args[0])) {
            intCheckLoop.add(args[1]);
            BotSystem.defaultChannel.sendMessage("Summoner added!").queue();
        }

        if ((prefix + "remove").equalsIgnoreCase(args[0])) {
            intCheckLoop.remove(args[1]);
            BotSystem.defaultChannel.sendMessage("Summoner removed!").queue();
        }

    }
    
}
