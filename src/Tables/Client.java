package Tables;

import java.io.*;
import java.util.ArrayList;

public class Client extends Table {
    final String first_name = "res\\first_names";
    final String last_name = "res\\last_names";
    String[] first_names;
    String[] last_names;

    public String getFirstName(){

        if (first_names == null) first_names = fileReader(first_name);
        if (first_names == null) return "";
        return first_names[rnd(0, first_names.length-1)];
    }

    public String getLastName(){
        if (last_names == null) last_names = fileReader(last_name); // first iteration. if it is null i will call reader. else will be next iteration
        if (last_names == null) return ""; // if after reading last_names still nullable, drop void string
        return last_names[rnd(0, last_names.length-1)];
    }

    public String getTelephoneNumber(){
        String result = "8";
        String[] prefix = { "910", "915", "916", "917", "919",
                "985", "986", "903", "905", "906",
                "909", "962", "963", "964", "965",
                "966", "967", "968", "969", "980",
                "981", "983", "986", "925", "926",
                "929", "936", "999", "901", "958",
                "977", "999", "995", "996", "999", };
        int randomPrefix = rnd(0, prefix.length-1);
        int randomNumber = rnd(1000000, 9999999);
        result = result + prefix[randomPrefix] + randomNumber;
        return result;
    }


}
