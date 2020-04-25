import java.sql.*;


public class Main {

    final String user = "postgres";
    final String password = "9204";
    final String url = "jdbc:postgresql://localhost:5432/taudio";

    public static void main(String[] args){
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Cant find driver");
            e.printStackTrace();
        }
        Main main = new Main();
        try{
            Statement statement = main.connect().createStatement();
            //statement.execute("INSERT INTO client(id, first_name, last_name, telephone) values (1, 'Ivan', 'Trushin', 88005553535);");
            ResultSet res = statement.executeQuery("select * from client;");
        }catch (SQLException e){ e.printStackTrace(); }


    }

    public Connection connect(){
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




}
