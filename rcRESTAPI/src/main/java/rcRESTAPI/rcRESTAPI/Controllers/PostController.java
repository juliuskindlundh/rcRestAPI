package rcRESTAPI.rcRESTAPI.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rcRESTAPI.rcRESTAPI.Converter;
import rcRESTAPI.rcRESTAPI.DTOs.PostDTO;
import rcRESTAPI.rcRESTAPI.Entity.Post;
import rcRESTAPI.rcRESTAPI.Service.PostService;
import rcRESTAPI.rcRESTAPI.Service.UserService;

@RestController
@CrossOrigin
public class PostController {

	@Autowired
	PostService postService;
	@Autowired
	UserService userService;

	@PostMapping("/posts")
	public ResponseEntity<PostDTO> create(@RequestBody Post dto, @RequestHeader("token") String token, @RequestHeader("username") String username) {
		if (userService.validateToken(username, token)) {
			PostDTO response = Converter.postToDTO(postService.create(dto));
			if (response.getPostId() != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

	}

	@GetMapping("/posts")
	public ResponseEntity<ArrayList<Post>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getAll());
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<Post> findById(@PathVariable(name = "id") Long id) {
		Optional<Post> optional = postService.getById(id);
		if (optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(optional.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@RequestMapping(value = "/posts", produces = "application/json", method = { RequestMethod.PUT })
	public ResponseEntity<Post> update(@RequestBody Post dto, @RequestHeader("token") String token, @RequestHeader("username") String username) {
		if(userService.validateToken(username, token)) {
			postService.update(dto);
		return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}

	@RequestMapping(value = "/posts/{id}", method = { RequestMethod.DELETE })
	public ResponseEntity<Object> deleteById(@PathVariable(name = "id") Long id, @RequestHeader("token") String token, @RequestHeader("username") String username) {
		if(userService.validateToken(username, token)) {
			postService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
