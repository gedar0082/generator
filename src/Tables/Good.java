package Tables;

public class Good extends Table {
    final String goods = "res\\goods";
    String[] companies;


    public String getQuantity(){
        return String.valueOf(rnd(1,4));
    }

    public String getPrice(){
        return rnd(10, 1000) + "." + rnd(1, 9);
    }

    public String getName(){
        return getCompany() + " " + getNumber();
    }

    private String getNumber(){
        String name = "";
        name = name + (char)rnd(65,87) + rnd(10,20) + (char)rnd(65,87) + rnd(10,20);
        return name;
    }

    private String getCompany(){
        if (companies == null) companies = fileReader(goods);
        if (companies == null) return "";
        return companies[rnd(0, companies.length-1)];
    }

}
