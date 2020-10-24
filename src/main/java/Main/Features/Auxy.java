package Main.Features;

import static Main.Features.MacTerm.AppleScript;

public class Auxy {
    public String askAuxy(String statement) {
        System.out.println("Processing statement...");
        String ans = "";

        switch (statement) {
            case "Hello":
                ans = "Hello their";

                System.out.println("Hello Case");
                break;

            case "Bye":
                ans = "Well, goodbye then";
                System.out.println("Bye Case");
                break;

            case "sleep":
                ans = "night night";
                AppleScript("sleep");
                System.out.println("sleep");
                break;

            case "shutdown":
                ans = "its, getting dark";
                AppleScript("shutdown");
                System.out.println("shutdown");
                break;
        }


        return ans;
    }
}

/*
Use ifs with list checking for common words
Use mixture of ifs, switches, and functions for responses
 */
