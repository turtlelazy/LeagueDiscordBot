package com.league.discordbot.demo.discord;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class BotSystem {
    public static MessageChannel defaultChannel;

    public boolean perms(MessageReceivedEvent event){
        boolean correctChannel = defaultChannel.equals(event.getChannel());

        return correctChannel;
    }

    public void changeDefaultChannel(MessageChannel channel){
        defaultChannel = channel;
    }
}
