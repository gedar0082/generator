import java.io.*;
import java.util.ArrayList;

public class ConfigParser {
    private void reader(String filename){

        try{
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while(line != null){
                if (line.contains("*")) continue; // ignore comments
                String[] substrings;
                substrings = line.split(" = ");
                //TODO next parsing
                line = reader.readLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("CONFIG FILE NOT FOUND");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("UNEXPECTED END OF FILE");
            e.printStackTrace();
        }

    }
}
