package book.searchupdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDBSearchAndUpdate {

	public static void main(String[] args) {
		Connection conn;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 로드
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb", "root","victory8"); // JDBC 연결
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement(); // SQL문 처리용 Statement 객체 생성
			stmt.executeUpdate("update book set title='Pride & Prejudice', author='제인 오스틴' where title='Pride and Prejudice'"); //데이터 수정
			printTable(stmt);
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		}
	}

	// 레코드의 각 열의 값 화면에 출력
	private static void printTable(Statement stmt) {
		ResultSet srs;
		System.out.printf("%4s|%-30s|%-30s|%-10s\n", "id","title", "publisher", "author");
		try {
			srs = stmt.executeQuery(
					"select * from book");
			while (srs.next()) {
				System.out.printf("%4s|%-30s|%-30s|%-10s\n", new String(srs.getString("id")), srs.getString("title"), srs.getString("publisher"), srs.getString("author"));
				//System.out.printf("%4s|%-30s|%-30s|%-10s\n", srs.getString("id"), srs.getString("title"), srs.getString("publisher"), srs.getString("author"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		}
	}
}

