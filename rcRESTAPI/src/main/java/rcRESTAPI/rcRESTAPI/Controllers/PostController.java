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

import rcRESTAPI.rcRESTAPI.DTOs.PostDTO;
import rcRESTAPI.rcRESTAPI.Entity.Post;
import rcRESTAPI.rcRESTAPI.Entity.User;
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
	public ResponseEntity<PostDTO> create(@RequestBody PostDTO dto, @RequestHeader("token") String token,
			@RequestHeader("username") String username) {
		if (userService.validateToken(username, token)) {
			dto.setCreator(username);
			PostDTO response = postService.create(dto);
			if (response.getPostId() != null) {
				userService.addPost(response,username);
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

	}

	@GetMapping("/posts")
	public ResponseEntity<ArrayList<PostDTO>> findAll() {
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

	@RequestMapping(value = "/posts", method = { RequestMethod.DELETE })
	public ResponseEntity<Object> deleteById(@RequestHeader("token") String token,
			@RequestHeader("username") String username,@RequestHeader("postid") String postid) {
		if (userService.validateToken(username, token)) {
			postService.deleteById(Long.parseLong(postid),username);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@GetMapping("/posts/getuserposts")
	public ResponseEntity<ArrayList<Post>> getPosts(@RequestHeader("token") String token, @RequestHeader("username") String username) {
		if (userService.validateToken(username, token)) {
			Optional<User> user = userService.getByUsername(username);
			ArrayList<Post> list = new ArrayList<Post>(user.get().getPosts());
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
