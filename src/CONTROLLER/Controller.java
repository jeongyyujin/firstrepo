package CONTROLLER;

import java.util.Scanner;

import MODEL.UserDAO;
import MODEL.UserDTO;

public class Controller {

	private UserDAO userDAO;

	public Controller() {
		userDAO = new UserDAO();
	}

	// 회원가입 메서드
	public void addUser(UserDTO dto) {
		userDAO.dbOpen();
		
		int cnt = userDAO.addUser(dto);
		
		if(cnt > 0) {
			System.out.println("회원 등록 성공!");
			
		} else {
			System.out.println("회원 등록 실패!");
		}
		  userDAO.dbClose(); // 데이터베이스 연결 종료
	}

	// 로그인 메서드
	public int login(String id, String password) {
		return userDAO.login(id, password);
	}
	
	   // 닉네임 설정 메서드
    public void setNickname(String id, String nickname) {
        userDAO.dbOpen();
        int cnt = userDAO.setNickname(id, nickname);

        if(cnt > 0) {
            System.out.println("닉네임 설정 성공!");
        } else {
            System.out.println("닉네임 설정 실패!");
        }
    }

    // 포켓몬 선택 메서드
    public void selectPokemon(String id, String type) {
        userDAO.dbOpen();
        int cnt = userDAO.selectPokemon(id, type);

        if(cnt > 0) {
            System.out.println("포켓몬 선택 성공!");
        } else {
            System.out.println("포켓몬 선택 실패!");
        }
    }
}

