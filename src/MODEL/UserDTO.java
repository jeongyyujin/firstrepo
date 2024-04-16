package MODEL;

public class UserDTO {

	// 생성자, 게터, 세터
	private String id;
	private String password;
	private String nickname;
	private String pokemon;
	
	public UserDTO(String id) {
		this.id = id;
	}
	
	public UserDTO(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public UserDTO(String id, String password, String nickname) {
		this.id = id;
		this.password = password;
		this.nickname = nickname;
	}

	public UserDTO(String id, String password, String nickname, String pokemon) {
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.pokemon = pokemon;
	}
	// getter/setter
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPokemon() {
		return pokemon;
	}
	
	public void setPokemon(String pokemon) {
		this.pokemon = pokemon;
	}

}
