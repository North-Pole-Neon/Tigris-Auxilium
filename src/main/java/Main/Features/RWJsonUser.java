package Main.Features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Map;

public class RWJsonUser {

    // Might need to be static
    public static String firstName = null;
    public static String lastName = null;
    public static String sGrade = null;
    public int iGrade = Integer.parseInt(sGrade);
    public static String sIsd = null;
    public int iIsd = Integer.parseInt(sIsd);
    public static String PCName = null;
    public static String PCNAME= null;
    public static String setupCom = "false";
    public boolean bSetupCom = false;

    public static String osName;
    public static Boolean fileUserExists;
    public static Boolean folderUserExists;
    public static String finalPath;
    public static String UserFilePath;


    @SuppressWarnings("unchecked")
    public static void WriteToJson() {

        //First user
        JSONObject userDetails = new JSONObject();
        userDetails.put("firstName", firstName);
        userDetails.put("lastName", lastName);
        userDetails.put("grade", sGrade);
        userDetails.put("isd", sIsd);
        userDetails.put("PCName", PCName);
        userDetails.put("setupCom", setupCom);

        JSONObject userObject = new JSONObject();
        userObject.put("user", userDetails);



        //Add user to list
        JSONArray userList = new JSONArray();
        userList.add(userObject);

        //Write JSON file
        try (FileWriter file = new FileWriter(UserFilePath)) {

            file.write(userList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON Created"); //PRINT JSON Created
    }

    @SuppressWarnings("unchecked")
    public static void ReadToJson() { //Static might be optional -------------------------------------------------------------------


        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(UserFilePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray userList = (JSONArray) obj;
            System.out.println(userList);

            //Iterate over user array
            userList.forEach( emp -> parseUserObject( (JSONObject) emp ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static void parseUserObject(JSONObject user)
    {
        //Get user object within list
        JSONObject userObject = (JSONObject) user.get("user");

        //Get user first name
        firstName = (String) userObject.get("firstName");
        //System.out.println(firstName);


        //Get user last name
        lastName = (String) userObject.get("lastName");
        //System.out.println(lastName);


        //Get user grade name
        sGrade = (String) userObject.get("grade");
        //System.out.println(sGrade);


        //Get user isd name
        sIsd = (String) userObject.get("isd");
        //System.out.println(sIsd);


        //Get user PcName name
        PCName = (String) userObject.get("PCName");
        //System.out.println(PCName);

        //Get user setup status
        setupCom = (String) userObject.get("setupCom");
        //System.out.println(setupCom);

        System.out.println("FN: "+ firstName + " LN: "+lastName + " Grade:  "+sGrade + " ISD: "+sIsd + " PC: "+PCName + " SU?: "+setupCom);

    }


    public static void getComputerName(Boolean test) //-----------------------------GET COMPUTER NAME--------------------------
    {
        String PCnameStr;


        Map<String, String> env = System.getenv();
        if (env.containsKey("COMPUTERNAME"))
            PCnameStr = env.get("COMPUTERNAME");
        else if (env.containsKey("HOSTNAME"))
            PCnameStr = env.get("HOSTNAME");
        else
            PCnameStr = "Unknown Computer";

        if(test == true) {
            PCNAME = PCnameStr;
        }else {
            PCName = PCnameStr;
        }
    }

    public static String getOSVersion() { //----------------------------------GET OS VERSION--------------------------------
        String os_name = null;
        os_name = System.getProperty("os.name");
        System.out.println("OS name is: " + os_name); //PRINT OS name is
        osName = os_name;
        return os_name;
    }



    public static void rootPathMaker() {//-------------------------------------PATH MAKER-------------------------------------
        String homePath;
        if (osName.equals("Windows 10") || osName.equals("Windows 8") || osName.equals("Windows 7")) {
            homePath = "C:\\Test\\TA\\Data";
        } else {

            homePath = System.getProperty("user.home") + "/TA/Data/";
        }

        System.out.println("Home dir path: " + homePath); //PRINT Home dir
        File fileDir = new File(homePath);
        if (!fileDir.exists()) fileDir.mkdirs(); //Might be an issue
        finalPath = homePath;
    }

    public static void jsonPathMaker(String name) { //----------------------------JSON PATH MAKER---------------------------------
        String tempPath = null;
        if (osName.equals("Windows 10") || osName.equals("Windows 8") || osName.equals("Windows 7")) {
            tempPath = finalPath + "\\";
        } else {
            tempPath = finalPath + "/";
        }

        switch(name) {
            case "User":
                UserFilePath = tempPath + name+".json";
                break;
            default:
                System.out.println("jsonPathMaker can't detect file name"); //PRINT JPM cant detect file name
        }
        System.out.println(name +" JSON file path is: " +UserFilePath); //PRINT finalPath
    }

    public static void userFileExists(String path) {// --------------------USER FILE EXISTS-------------------------

        boolean temp = new File(path).isFile();
        System.out.println("User file exists: " + temp); //PRINT User file exists
        fileUserExists = temp;
    }



    public static void clearUserData() {
        firstName = null;
        lastName = null;
        sGrade = null;
        sIsd = null;
        PCName = null;
        setupCom = null;
        WriteToJson();
    }
}
