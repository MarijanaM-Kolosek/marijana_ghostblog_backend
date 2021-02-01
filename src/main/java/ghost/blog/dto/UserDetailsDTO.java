package ghost.blog.dto;

import ghost.blog.entity.User;
import ghost.blog.enumerations.Role;

public class UserDetailsDTO {

	private Role role;
	private String username;
	private String password;
	private String fullname;
	private String country;
	
	public UserDetailsDTO() {
		super();
	}

	public UserDetailsDTO(Role role, String username, String password, String fullname, String country) {
		super();
		this.role = role;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.country = country;
	}
	
	public UserDetailsDTO(User u) {
		this(u.getRole(), u.getUsername(), u.getPassword(), u.getFullname(), u.getCountry());
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
	
}
