import java.sql.*;

public class JdbcExOne {
    public static void main(String args[]) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/JDBC_CON";
        String userName = "root";
        String password = "lavenderize";

        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users");

        while (rs.next()) {
            String name = rs.getString("name");
            System.out.println(name);

        }

        rs.close();
        st.close();
        con.close();
    }
}