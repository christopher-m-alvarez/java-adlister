import com.mysql.cj.jdbc.Driver;
import jdbc.Config;

import java.sql.*;
import java.util.List;

public class MySQLAdsDao implements Ads{

    public static void main(String[] args) throws SQLException {
        Config config = new Config();

        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
        );


        Statement statement = connection.createStatement();
        ResultSet rows = statement.executeQuery("select u.name, u.email from ads as u");


        while (rows.next()){
            // rows points to the current row
            //output name and email of current row
            int currentUserId = rows.getInt("id");
            String currentUserName = rows.getString("Ad");
            String currentUserEmail = rows.getString("email");
            System.out.println(currentUserId + " " + currentUserName + " " + currentUserEmail);
        }


        int numRows = statement.executeUpdate("insert into ads(name, email, role_id) values ('Tom Jones', 'tom@tom.com'),4)", Statement.RETURN_GENERATED_KEYS);
        if(numRows < 1) {
            System.out.println("did not make an ad");
            rows.close();
            statement.close();
            connection.close();
            System.exit(1);
        }
        System.out.println(numRows + "Ad inserted");

    }


    @Override
    public List<Ad> all() {
        return null;
    }
}
