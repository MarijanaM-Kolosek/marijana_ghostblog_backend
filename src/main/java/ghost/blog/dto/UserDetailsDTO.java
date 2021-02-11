package ghost.blog.dto;

import ghost.blog.entity.User;
import ghost.blog.enumerations.Role;

public class UserDetailsDTO {

	private Role role;
	private String token;
	private String username;
	private String password;
	private String fullname;
	private String country;
	private String image;
	
	public UserDetailsDTO() {
		super();
	}

	public UserDetailsDTO(Role role, String username, String password, String fullname, String country, String image) {
		super();
		this.role = role;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.country = country;
		this.image = image;
	}
	
	public UserDetailsDTO(String token, Role role, String username, String password, String fullname, String country, String image) {
		super();
		this.token = token;
		this.role = role;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.country = country;
		this.image = image;
	}
	
	public UserDetailsDTO(User u) {
		this(u.getRole(), u.getUsername(), u.getPassword(), u.getFullname(), u.getCountry(),u.getImage());
	}
	
	public UserDetailsDTO(User u, String token) {
		this(token, u.getRole(), u.getUsername(), u.getPassword(), u.getFullname(), u.getCountry(), u.getImage());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
