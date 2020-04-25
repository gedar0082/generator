import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Main {

    private final String url = "jdbc:postgresql://localhost:5432/taudio";
    private String user = "postgres";
    private String passsword = "9204";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Main main = new Main();
        main.connect();
    }

    public Connection connect(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, passsword);
            System.out.println("SUCCESSFULLY CONNECTED");
        } catch (SQLException e){
            System.out.println("FAILED CONNECTION");
            System.out.println(e.getMessage());
        }
        return connection;
    }


}
