package rcRESTAPI.rcRESTAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import rcRESTAPI.rcRESTAPI.console;
import rcRESTAPI.rcRESTAPI.DTOs.UserDTO;
import rcRESTAPI.rcRESTAPI.Entity.User;
import rcRESTAPI.rcRESTAPI.Service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/users")
	public ResponseEntity create(@RequestBody UserDTO dto) {
		console.log(dto.getUserId()+"idididi");
		UserDTO userDTO = userService.create(dto);
		if(userDTO.getUserId()!=null) {		
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
	
	@GetMapping("/users")
	public ResponseEntity<String> login(@RequestHeader("username") String username,@RequestHeader("password") String password) {
		String token = userService.login(username, password);
		if(token.length() != 0) {
			return ResponseEntity.status(HttpStatus.OK).body(token);
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@PutMapping("/users")
	public ResponseEntity logout(@RequestHeader("token") String token, @RequestHeader("username") String username) {
		System.out.println(token+"||"+username);
		userService.logout(token, username);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
