import java.io.*;

public class ConfigFinder {
    public String[] configFinder(){
        String[] configs = {"", ""};
        try{
            File file = new File("res\\db_properties");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
        }catch (FileNotFoundException e){
            System.out.println("CONFIG FILE NOT FOUND");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("UNEXPECTED END OF FILE");
            e.printStackTrace();
        }
        return configs;
    }
}
