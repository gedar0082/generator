import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;
import Tables.*;

public class Main {

    final String user = "postgres";
    final String password = "9204";
    final String url = "jdbc:postgresql://localhost:5432/taudio";
    Table table = new Table();
    ArrayList<ArrayList<Integer>> orderHasGood = new ArrayList<>();
    ArrayList<ArrayList<Integer>> goodHasCategory = new ArrayList<>();
    ArrayList<ArrayList<Integer>> charHasGood = new ArrayList<>();
    ArrayList<ArrayList<Integer>> charHasCategory = new ArrayList<>();


    public static void main(String[] args) throws SQLException {
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Cant find driver");
            e.printStackTrace();
        }
        /*коннект*/
        Main main = new Main();
        Statement statement = main.connect().createStatement();
        /*сброс запросов*/
        statement.execute("delete from char_has_good");
        statement.execute("delete from char_has_category");
        statement.execute("delete from good_has_category");
        statement.execute("delete from order_has_good");
        statement.execute("delete from public.order;");
        statement.execute("alter sequence order_id_seq restart;");
        statement.execute("delete from worker;");
        statement.execute("alter sequence worker_id_seq restart;");
        statement.execute("delete from client;");
        statement.execute("alter sequence client_id_seq restart;");
        statement.execute("delete from status;");
        statement.execute("delete from good;");
        statement.execute("alter sequence good_id_seq restart;");
        statement.execute("delete from category;");
        statement.execute("delete from char;");
        statement.execute("alter sequence char_id_seq restart;");

        /*одноразовые запросы*/
        statement.execute(main.statusSetter());
        statement.execute(main.categorySetter());
        statement.execute(main.charSetter());
        for (int i = 0; i < 100 ; i ++){
            statement.execute(main.workerSetter());
            statement.execute(main.clientSetter());
            statement.execute(main.goodSetter());
            statement.execute(main.orderSetter(statement));
            statement.execute(main.orderHasGood(statement));
            statement.execute(main.goodHasCategory(statement));
            statement.execute(main.charHasGood(statement));
            statement.execute(main.charHasCategory(statement));
        }

    }

    Connection connect(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("SUCCESSFULLY CONNECTED");
        } catch (SQLException e){
            System.out.println("FAILED CONNECTION");
            System.out.println(e.getMessage());
        }
        return connection;
    }

    String clientSetter(){
        Client client = new Client();
        return "INSERT INTO client(first_name, last_name, telephone) values (" +
                "'" + client.getFirstName() + "'" + ", " +
                "'" + client.getLastName() + "'" + ", " +
                client.getTelephoneNumber() + ");";
    }

    String workerSetter(){
        Worker worker = new Worker();
        return "INSERT INTO worker(first_name, last_name, job, salary) values (" +
                "'" + worker.getFirstName() + "'" + ", " +
                "'" + worker.getLastName() + "'" + ", " +
                "'" + worker.getJob() + "'" + "," +
                worker.getSalary("seller") + ");";
    }

    String statusSetter(){
        Status status = new Status();
        String[] statuses = status.getAll();
        return "INSERT INTO status(id, name) values(" + 1 + ", " + "'" + statuses[0] + "'" + "); " +
        "INSERT INTO status(id, name) values(" + 2 + ", " + "'" + statuses[1] + "'" + "); " +
        "INSERT INTO status(id, name) values(" + 3 + ", " + "'" + statuses[2] + "'" + ");";
    }

    String categorySetter(){
        Categories cat = new Categories();
        String[] cats = cat.getAllCategories();
        StringBuilder res = new StringBuilder();
        for (String c : cats) {
            if (cat.getUpperCategory(cat.getId(c)) == 0) {
                String s = "INSERT INTO category(id, name) values (" +
                        cat.getId(c) + ", " +
                        "'" + cat.getName(c) + "'" + "); ";
                res.append(s);
            } else {
                String s = "INSERT INTO category(id, name, upper_cat_id) values (" +
                        cat.getId(c) + ", " +
                        "'" + cat.getName(c) + "'" + ", " +
                        cat.getUpperCategory(cat.getId(c)) + "); ";
                res.append(s);
            }
        }
        return res.toString();
    }

    String goodSetter(){
        Good good = new Good();
        return "INSERT INTO good(name, price) values (" + "'" + good.getName() + "'" + "," + good.getPrice() + ");";
    }

    String charSetter(){
        Characteristic ch = new Characteristic();
        String[] chs = ch.getAllChars();
        StringBuilder res = new StringBuilder();
        for (String s: chs){
            String st = "INSERT INTO char(name, value) values (" + "'" + ch.getChar(s) + "'" + ", " + ch.getRandomValue() + ");";
            res.append(st);
        }
        return res.toString();
    }

    String orderSetter(Statement statement) throws SQLException {
        ResultSet rs = statement.executeQuery("select id from worker;");
        Integer[] arr = rsToIntArr(rs);
        int workerId = table.rnd(arr[0], arr[arr.length - 1]);

        rs = statement.executeQuery("select id from client;");
        arr = rsToIntArr(rs);
        int clientId = table.rnd(arr[0], arr[arr.length - 1]);
        
        rs = statement.executeQuery("select id from status;");
        arr = rsToIntArr(rs);
        int statusId = table.rnd(arr[0], arr[arr.length - 1]);
        
        LocalDate dateStart = LocalDate.of(table.rnd(2000, 2019), table.rnd(1,12), table.rnd(1,20));
        LocalDate dateFinish = LocalDate.of(dateStart.getYear(), dateStart.getMonthValue(), table.rnd(21, 27));

        int sum = table.rnd(100, 2000);
        if (statusId == 3){
            return "INSERT INTO public.order(worker_id, client_id, sum_price, date_start, status_id) values ("+
                    workerId + "," +
                    clientId + "," +
                    sum + "," + "'" +
                    Date.valueOf(dateStart) + "'" + "," +
                    statusId + ");";
        }
        else{
            return "INSERT INTO public.order(worker_id, client_id, sum_price, date_start, date_finish, status_id) values ("+
                    workerId + "," +
                    clientId + "," +
                    sum + "," + "'" +
                    Date.valueOf(dateStart) + "'" + "," + "'" +
                    Date.valueOf(dateFinish) + "'" + "," +
                    statusId + ");";
        }
    }

    public String orderHasGood(Statement st) throws SQLException {
        Integer[] arrOrder;
        ResultSet rsOrder = st.executeQuery("select id from public.order;");
        arrOrder = rsToIntArr(rsOrder);


        Integer[] arrGood;
        ResultSet rsGood = st.executeQuery("select id from good;");
        arrGood = rsToIntArr(rsGood);


        int orderId = table.rnd(arrOrder[0], arrOrder[arrOrder.length - 1]);
        int goodId = table.rnd(arrGood[0], arrGood[arrGood.length - 1]);

        boolean b = isInArr(orderHasGood, orderId, goodId);
        while(b){
            orderId = table.rnd(arrOrder[0], arrOrder[arrOrder.length - 1]);
            goodId = table.rnd(arrGood[0], arrGood[arrGood.length - 1]);
            b = isInArr(orderHasGood, orderId, goodId);
        }
        ArrayList<Integer> ad = new ArrayList<>();
        ad.add(orderId);
        ad.add(goodId);
        orderHasGood.add(ad);
        
        return "INSERT INTO order_has_good(good_id, number, order_id) values (" + goodId + "," + table.rnd(1, 5) + "," + orderId + ");";
    }

    public String goodHasCategory(Statement st) throws SQLException{
        Integer[] arrCategory;
        ResultSet rsCategory = st.executeQuery("select id from category;");
        arrCategory = rsToIntArr(rsCategory);

        Integer[] arrGood;
        ResultSet rsGood = st.executeQuery("select id from good;");
        arrGood = rsToIntArr(rsGood);

        int categoryId = arrCategory[table.rnd(0, arrCategory.length-1)];
        int goodId = table.rnd(arrGood[0], arrGood[arrGood.length - 1]);

        boolean b = isInArr(goodHasCategory, categoryId, goodId);
        while(b){
            categoryId = arrCategory[table.rnd(0, arrCategory.length-1)];
            goodId = table.rnd(arrGood[0], arrGood[arrGood.length - 1]);
            b = isInArr(goodHasCategory, categoryId, goodId);
        }
        ArrayList<Integer> ad = new ArrayList<>();
        ad.add(categoryId);
        ad.add(goodId);
        goodHasCategory.add(ad);

        return "INSERT INTO good_has_category(good_id, category_id) values (" + goodId + "," + categoryId + ");";
    }

    public String charHasGood(Statement st) throws SQLException {
        Integer[] arrChar;
        ResultSet rsChar = st.executeQuery("select id from char;");
        arrChar = rsToIntArr(rsChar);

        Integer[] arrGood;
        ResultSet rsGood = st.executeQuery("select id from good;");
        arrGood = rsToIntArr(rsGood);

        int charId = table.rnd(arrChar[0], arrChar[arrChar.length - 1]);
        int goodId = table.rnd(arrGood[0], arrGood[arrGood.length - 1]);

        boolean b = isInArr(charHasGood, charId, goodId);
        while(b){
            charId = table.rnd(arrChar[0], arrChar[arrChar.length - 1]);
            goodId = table.rnd(arrGood[0], arrGood[arrGood.length - 1]);
            b = isInArr(charHasGood, charId, goodId);
        }
        ArrayList<Integer> ad = new ArrayList<>();
        ad.add(charId);
        ad.add(goodId);
        charHasGood.add(ad);

        return "INSERT INTO char_has_good(good_id, char_id) values (" + goodId + "," + charId + ");";
    }

    public String charHasCategory(Statement st) throws SQLException{
        Integer[] arrChar;
        ResultSet rsChar = st.executeQuery("select id from char;");
        arrChar = rsToIntArr(rsChar);

        Integer[] arrCategory;
        ResultSet rsCategory = st.executeQuery("select id from category;");
        arrCategory = rsToIntArr(rsCategory);

        int charId = table.rnd(arrChar[0], arrChar[arrChar.length - 1]);
        int categoryId = arrCategory[table.rnd(0, arrCategory.length-1)];

        boolean b = isInArr(charHasCategory, charId, categoryId);
        while(b){
            charId = table.rnd(arrChar[0], arrChar[arrChar.length - 1]);
            categoryId = arrCategory[table.rnd(0, arrCategory.length-1)];
            b = isInArr(charHasCategory, charId, categoryId);
        }
        ArrayList<Integer> ad = new ArrayList<>();
        ad.add(charId);
        ad.add(categoryId);
        charHasCategory.add(ad);

        return "INSERT INTO char_has_category(category_id, char_id) values (" + categoryId + "," + charId + ");";
    }

    private boolean isInArr(ArrayList<ArrayList<Integer>> arr, int a, int b){
        for (int i = 0; i < arr.size(); i++){
            if (arr.get(i).get(0) == a && arr.get(i).get(1) == b) return true;
        }
        return false;
    }

    private Integer[] rsToIntArr(ResultSet rs) throws SQLException {
        Vector<Integer> ores = new Vector<>();
        while(rs.next()){ores.add(rs.getInt(1));}
        return ores.toArray(new Integer[0]);
    }
}
