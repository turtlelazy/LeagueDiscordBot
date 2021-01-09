package com.league.discordbot.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Private {
    public static String ritoKey;
    public static String discKey = "Nzc2OTQwNzYxMTgyMjQwODE4.X68MeA.AlDgkQYWCOjPkGw93anQVQTbWX0";

    public static void updateKeys() throws FileNotFoundException{
        System.out.println(new File(".").getAbsolutePath());
        File keyFiles = new File("keys.txt");
        Scanner input = new Scanner(keyFiles);

        ritoKey = input.next();
        
    }
}
