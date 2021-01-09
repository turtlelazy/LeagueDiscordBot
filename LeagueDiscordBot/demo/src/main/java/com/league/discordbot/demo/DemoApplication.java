//Code testing class
package com.league.discordbot.demo;

import com.league.discordbot.demo.riot.RitoSummoner;
import com.merakianalytics.orianna.types.common.Region;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//idk wht this does
		SpringApplication.run(DemoApplication.class, args);

		//safe way to access your key and share code; create a Private class
		String key = Private.ritoKey;

		//initializaing use of the Rito API
		RitoSummoner ritoAPIRunner = new RitoSummoner(Region.NORTH_AMERICA,key);
		ritoAPIRunner.init();

		//test for int method; delete later
		/*
		String[] summoners = {"time","fishrocks","xjosephcoolx","endiah","what came first"};
		for(int i = 0; i < summoners.length; i++){
			int[] kda = ritoAPIRunner.lastGameKDA(summoners[i]);
			System.out.println(kda[0]);
			System.out.println(kda[1]);
			System.out.println(kda[2]);
		}
		*/

		System.out.println(ritoAPIRunner.exists("xjosephcoolx"));
		

	}

}
