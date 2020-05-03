package Tables;

public class Status extends Table{
    final String status = "res\\status";
    String[] statuses = fileReader(status);

    public String[] getAll(){
        return statuses;
    }

    public String getStatus(){

        return statuses[rnd(0, statuses.length-1)];
    }


}
