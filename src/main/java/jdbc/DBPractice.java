package jdbc;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class DBPractice {
    public static void main(String[] args) throws SQLException {
        Config config = new Config();

        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
        );

        // fetch users from users table
        Statement statement = connection.createStatement();
        ResultSet rows = statement.executeQuery("select u.name, u.email from users as u");


        // iterate over the rows
        while (rows.next()){
            // rows points to the current row
            //output name and email of current row
            int currentUserId = rows.getInt("id");
            String currentUserName = rows.getString("name");
            String currentUserEmail = rows.getString("email");
            System.out.println(currentUserId + " " + currentUserName + " " + currentUserEmail);
        }


        //insert a new user: tom jones

        int numRows = statement.executeUpdate("insert into users(name, email, role_id) values ('Tom Jones', 'tom@tom.com'),4)", Statement.RETURN_GENERATED_KEYS);
        if(numRows < 1) {
            System.out.println("did not insert tom");
            rows.close();
            statement.close();
            connection.close();
            System.exit(1);
        }
        System.out.println(numRows + "rows inserted");

        ResultSet newKeys = statement.getGeneratedKeys();
        int tomsId = 0;
        if (newKeys.next()) {
            tomsId = newKeys.getInt(1);
        }
        System.out.println(tomsId + " is the id for the new tom jones record");

        //clean your stuff up
        rows.close();
        statement.close();
        connection.close();
    }
}