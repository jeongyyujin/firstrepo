package VIEW;

import java.util.ArrayList;
import java.util.Scanner;

import CONTROLLER.Controller;
import MODEL.UserDTO;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Controller userController = new Controller();

		while (true) {
			System.out.println("==== 포켓몬 진화게임 ====");
			System.out.println("아이디가 있나요? [1] Y [2] N ");
			int num = scanner.nextInt();

			if (num == 1) { // 로그인
				System.out.println("==== 로그인 ====");
				System.out.print("아이디를 입력하세요 >> ");
				String id = scanner.next();
				System.out.print("비밀번호를 입력하세요 >> ");
				String password = scanner.next();

				if (userController.login(id, password) == 1) {
					System.out.println("로그인 성공");
					break;
				} else if (userController.login(id, password) == 0) {
					System.out.println("비밀번호가 틀렸습니다");
				} else {
					System.out.println("아이디가 없습니다. 회원가입 해주세요");
				}
			} else if (num == 2) { // 회원가입
				System.out.println("==== 회원가입 ====");
				System.out.print("아이디를 입력하세요 >> ");
				String id = scanner.next();
				System.out.print("비밀번호를 입력하세요 >> ");
				String pw = scanner.next();

				System.out.println("회원가입이 완료되었습니다.");
				UserDTO dto = new UserDTO(id, pw);

				Controller con = new Controller();
				con.addUser(dto);

//			} else if (num == 3) { // 종료
//				System.out.println("게임을 종료합니다.");
//				break;
//			}

			}

		}
	}
}
