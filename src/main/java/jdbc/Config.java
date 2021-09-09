package jdbc;

public class Config {
        public String getUrl() {
            return "jdbc:mysql://localhost/codeup?serverTimezone=UTC&useSSL=false";
        }
        public String getUser() {
            return "root";
        }
        public String getPassword() {
            return "password";
        }
}
