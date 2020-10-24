package Main.Features;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MacTerm {
    static String tSleep = "pmset sleepnow";
    public static String sSleep= "tell application \"System Events\"\n" +
            "\tsleep\n" +
            "end tell\n"; //Might need to remove the \t
    public static String sShutdown = " tell application \"Finder\"\n" +
            " \tshut down\n" +
            " end tell\n";


    public String fileFinder(String fileName) {
        Object OS = RWJsonUser.osName;
        String fullPath;
        if (OS.equals("Windows 10") || OS.equals("Windows 8") || OS.equals("Windows 7")) {
            fullPath = "C:\\Test\\TA\\Data\\"+fileName;
        } else {
            String paths = System.getProperty("user.home");
            fullPath = paths + "/TA/Data/"+fileName;
        }
        return fullPath;
    }



    public static void macShell(String com) throws InterruptedException {
        String [] command = {"sh", com}; //Can turn to command "/Users/226331/Desktop/fun.sh"
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File(System.getProperty("user.home")));

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader  (new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);

            }
            int exitCode = process.waitFor();

            System.out.println("\nExited with error code : " + exitCode);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void AppleScript(String command) //template
    {
        Runtime runtime = Runtime.getRuntime();

        // an applescript command that is multiple lines long.
        // just create a java string, and remember the newline characters.
        String applescriptCommand = null;
        if(command == "sleep") {
            applescriptCommand = sSleep;
        } else {
            applescriptCommand = sShutdown;
        }

        String[] args = { "osascript", "-e", applescriptCommand };

        try
        {
            @SuppressWarnings("unused")
            Process process = runtime.exec(args);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void macTermLin(String command) //template
    {
        Runtime runtime = Runtime.getRuntime();
        String[] args = { command };

        try
        {
            @SuppressWarnings("unused")
            Process process = runtime.exec(args);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
