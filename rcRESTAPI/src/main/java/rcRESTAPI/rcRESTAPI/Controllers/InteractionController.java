package rcRESTAPI.rcRESTAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import rcRESTAPI.rcRESTAPI.Service.InteractionService;

@RestController
@CrossOrigin
public class InteractionController {

	@Autowired
	InteractionService interactionService;


}
