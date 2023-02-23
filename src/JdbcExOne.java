import java.sql.*;

public class JdbcExOne {
    private static void printData(ResultSet rs, String col1, String col2, String col3, String col4, String col5, String col6, String col7, String col8, String col9) throws SQLException {
        System.out.println("id\t|\t\tinitial\t\t|\t   name\t\t|\tbirthyear\t\t|\tbirthplace\t\t|\tfirstnum\t\t|\tlastnum\t\t|\theight\t\t|\tdebutdate");
        System.out.println("=======================================");
        while (rs.next()) {
            if (!col1.equals(""))
                System.out.print(rs.getString("id"));
            if (!col2.equals(""))
                System.out.print("\t|\t" +
                        rs.getString("initial"));
            if (!col3.equals(""))
                System.out.println("\t|\t" +
                        rs.getString("name"));
            if (!col4.equals(""))
                System.out.println("\t|\t" +
                        rs.getString("birthyear"));
            if (!col5.equals(""))
                System.out.println("\t|\t" +
                        rs.getString("birthplace"));
            if (!col6.equals(""))
                System.out.println("\t|\t" +
                        rs.getString("firstnum"));
            if (!col7.equals(""))
                System.out.println("\t|\t" +
                        rs.getString("lastnum"));
            if (!col8.equals(""))
                System.out.println("\t|\t" +
                        rs.getString("height"));
            if (!col9.equals(""))
                System.out.println("\t|\t" +
                        rs.getString("debutdate"));
            else
                System.out.println();
        }
    }

    public static void main(String[] args){
        System.out.println("JDBC Start");

        Connection conn = null;
        Statement stmt = null;

        try{
            //forName은 jdbc드라이버에 접속할 수 있도록 해줌
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/sampledb";
            String userName = "root";
            String password = "gging00100!";

            conn = DriverManager.getConnection(url, userName, password);

            if (conn != null){
                System.out.println("DB 연결 완료");
                stmt = conn.createStatement();
                ResultSet srs = stmt.executeQuery("select * from student");
                printData(srs, "id", "initial", "name", "birthyear", "birthplace", "firstnum", "lastnum", "height", "debutdate");
            }

        }catch (ClassNotFoundException e){
            System.out.println("JDBC 드라이버 로드 오류");
        }catch (SQLException e){
            System.out.println("DB 연결 오류");
        }finally{
            try{
                stmt.close();
                conn.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}