package Tables;

public class Worker extends Table {
    final String first_name = "res\\first_names";
    final String last_name = "res\\last_names";
    final String job = "res\\jobs";
    String[] first_names;
    String[] last_names;
    String[] jobs;


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
        switch (job){
            case("seller"): return rnd(100, 999);
            case("manager"): return rnd(1000, 4999);
            case("boss"): return rnd(5000, 9999);
            default: return 777;
        }
    }
}
