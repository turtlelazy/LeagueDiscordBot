//Bot is run here
package com.league.discordbot.demo;

import java.io.FileNotFoundException;

import javax.security.auth.login.LoginException;

import com.league.discordbot.demo.discord.Commands;

import net.dv8tion.jda.api.JDABuilder;

public class DiscordBot {
    public static void main(String[] args) throws LoginException,FileNotFoundException{
        Private.updateKeys();
        JDABuilder builder = JDABuilder.createDefault(Private.discKey);
        Commands commands = new Commands();
        builder.addEventListeners(commands);
        builder.build();
    }
}
