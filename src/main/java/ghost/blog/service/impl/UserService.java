package ghost.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ghost.blog.entity.User;
import ghost.blog.repository.UserRepository;
import ghost.blog.service.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface, UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
		return authorities;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
				getGrantedAuthorities(user));
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
}
