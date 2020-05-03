package Tables;

import java.io.*;
import java.util.ArrayList;

public class Table {
    public int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public double rndDouble(double min, double max){
        return  min + Math.random()*(max-min);

    }

    String[] fileReader(String filename){
        ArrayList<String> strs = new ArrayList<>();
        try{
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while(line != null){
                strs.add(line);
                line = reader.readLine();
            }
            return strs.toArray(String[]::new);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Reader mistake");
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("Incorrect array writing");
            e.printStackTrace();
        }
        return null;
    }
}
