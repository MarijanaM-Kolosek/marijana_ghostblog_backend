package ghost.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ghost.blog.dto.LoginDTO;
import ghost.blog.dto.UserDetailsDTO;
import ghost.blog.entity.User;
import ghost.blog.enumerations.Role;
import ghost.blog.repository.UserRepository;
import ghost.blog.security.TokenUtils;
import ghost.blog.service.impl.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<UserDetailsDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            String genToken = tokenUtils.generateToken(details);
            User u = userRepository.findByUsername(loginDTO.getUsername());
        	return new ResponseEntity<UserDetailsDTO>(new UserDetailsDTO(u, genToken),
            		HttpStatus.OK);
        } catch (Exception ex) {
        	System.out.println("BAD REQUEST");
        	return new ResponseEntity<>(new UserDetailsDTO(), HttpStatus.BAD_REQUEST);
        }
	}
	
	@RequestMapping(value = "/api/register", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDetailsDTO> register(@RequestBody UserDetailsDTO userDTO){
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setCountry(userDTO.getCountry());
		user.setFullname(userDTO.getFullname());
		user.setRole(Role.Contributor); //toDo roles and permissions
		user.setImage("//www.gravatar.com/avatar/2bfa103a13c88b5ffd26da6f982f11df?s=250&d=mm&r=x");
		
		userService.save(user);
		
		return new ResponseEntity<>(new UserDetailsDTO(user), HttpStatus.CREATED);
	}
	
}
