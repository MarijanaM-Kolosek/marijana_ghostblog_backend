package ghost.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ghost.blog.enumerations.Role;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "username", unique = true, nullable = false)
	protected String username;
	
	@Column(name = "password", unique = false, nullable = false)
	protected String password;
	
	@Column(name = "fullname", unique = false, nullable = true)
	protected String fullname;
	
	@Column(name = "country", unique = false, nullable = true)
	protected String country;
	
	@Column(name = "image", unique = false, nullable = true)
	protected String image;
	
	@Enumerated(EnumType.STRING)
	protected Role role;
	
	public User() {
		super();
	}

	public User(Long id, String username, String password, String fullname, String country, String image, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.country = country;
		this.image = image;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
