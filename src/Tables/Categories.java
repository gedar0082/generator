package Tables;

public class Categories extends Table {
    final String category = "res\\categories";
    private final String[] categories = fileReader(category);

    public String[] getAllCategories(){ return categories; }

    private String[] parser(String line){ return line.split("[ ]+"); }

    public String getName(String line){
        return parser(line)[0];
    }

    public int getId(String line){
        return Integer.parseInt(parser(line)[1]);
    }

    public int getUpperCategory(int id){
        for(String s: getAllCategories()){
            if (Integer.parseInt(parser(s)[1]) == id) return Integer.parseInt(parser(s)[2]);
        }
        return 0;
    }

}
