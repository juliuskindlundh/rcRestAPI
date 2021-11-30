package rcRESTAPI.rcRESTAPI.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncodeAndMatch {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public String encode(String password) {
		return bCryptPasswordEncoder.encode(password);
	}
	
	public boolean matches(String passwordIN,String passwordDB) {
		return bCryptPasswordEncoder.matches(passwordIN,passwordDB);
	}
}
