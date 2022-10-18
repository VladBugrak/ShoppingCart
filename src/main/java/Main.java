import com.connection.DBConnection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            String strConnection = DBConnection.getConnection().toString();
            System.out.println(strConnection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

