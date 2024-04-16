package MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	private ArrayList<UserDTO> users;

	public UserDAO() {
		users = new ArrayList<>();
	}

	// 회원 추가 메서드
	public int addUser(UserDTO user) {

		int cnt = 0;
		dbOpen();

		try {
			String sql = "INSERT INTO USER_INFO VALUES (?, ?)";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, user.getId());
			psmt.setString(2, user.getPassword());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
			e.printStackTrace();
		} finally {
			dbClose(); // 데이터베이스 연결 종료
		}

		return cnt;
	}

//    아이디로 회원 조회 메서드
	public UserDTO getUserById(String id) {
		for (UserDTO user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null; // 해당 아이디의 회원이 없을 경우
	}

	// 로그인 메서드
	public int login(String id, String password) {
		dbOpen();

		try {
			String sql = "SELECT PASSWORD FROM USER_INFO WHERE ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(password))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호 불일치
			}
			return -1; // 아이디 없음
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
			e.printStackTrace();
		} finally {
			dbClose(); // 데이터베이스 연결 종료
		}

		return -2; // 데이터 베이스 오류

	}

	// 닉네임 설정 메서드
	public int setNickname(String id, String nickname) {
		int cnt = 0;
		try {
			String sql = "UPDATE DAMA SET NICKNAME = ? WHERE ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nickname);
			psmt.setString(2, id);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	// 포켓몬 선택 메서드
	public int selectPokemon(String id, String type) {
		int cnt = 0;
		try {
			String sql = "UPDATE DAMA SET SELECTED_TYPE = ? WHERE ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, type);
			psmt.setString(2, id);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
			e.printStackTrace();
		}
		return cnt;
	}

	// 데이터베이스와의 동적로딩/권한확인 - 메서드로 불러올거임!
	public void dbOpen() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String id = "campus_24SW_BIG_p1_5";
			String pw = "smhrd5";
			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";

			conn = DriverManager.getConnection(url, id, pw); // 동적로딩과 연결 권한을 확인하는 것임!
		} catch (ClassNotFoundException e) {
			System.out.println("동적 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("권한 확인 실패");
			e.printStackTrace();
		}

	}

	// 데이터베이스 자원 반납
	public void dbClose() {
		try { // 열리는 순서 반대로 닫아줘야함
			if (rs != null) {
				rs.close();
			}
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}