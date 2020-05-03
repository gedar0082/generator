package Tables;

public class Characteristic extends Table{
    final String characteristic = "res\\characteristics";
    String[] characteristics = fileReader(characteristic);

    public String[] getAllChars(){return characteristics;}

    private String[] parser(String line){ return line.split("[ ]+"); }

    public String getChar(String line){
        return parser(line)[1];
    }

    public int getID(String line){
        return Integer.parseInt(parser(line)[0]);
    }

    public double getRandomValue(){
        return rndDouble(1, 5);
    }



}
