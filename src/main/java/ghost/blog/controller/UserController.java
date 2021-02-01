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
import ghost.blog.dto.TokenDTO;
import ghost.blog.dto.UserDetailsDTO;
import ghost.blog.entity.User;
import ghost.blog.enumerations.Role;
import ghost.blog.security.TokenUtils;
import ghost.blog.service.impl.UserService;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
        	System.out.println("LOGIN CALLED");
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            String genToken = tokenUtils.generateToken(details);
            System.out.println("token is: " + genToken);
            return new ResponseEntity<TokenDTO>(new TokenDTO(genToken),
            		HttpStatus.OK);
        } catch (Exception ex) {
        	System.out.println("BAD REQUEST");
            return new ResponseEntity<TokenDTO>(new TokenDTO(""), HttpStatus.BAD_REQUEST);
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
		
		userService.save(user);
		
		return new ResponseEntity<>(new UserDetailsDTO(user), HttpStatus.CREATED);
	}
}
