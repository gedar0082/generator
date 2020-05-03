package Tables;

public class Worker extends Table {
    final String first_name = "res\\first_names";
    final String last_name = "res\\last_names";
    final String job = "res\\jobs";
    String[] first_names;
    String[] last_names;
    String[] jobs;
    int index = 0;

    public int getIndex(){
        index++;
        return index - 1;
    }

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

    public String getJob(){
        if (jobs == null) jobs = fileReader(job);
        if(jobs == null) return "";
        return jobs[rnd(0, jobs.length -1)];
    }

    public int getSalary(String job){
        int salary = 777;
        switch (job){
            case("seller"): salary = rnd(100, 999);
            case("manager"): salary = rnd(1000, 9999);
            case("boss"): salary = rnd(1000, 9999);
        }
        return salary;
    }
}
