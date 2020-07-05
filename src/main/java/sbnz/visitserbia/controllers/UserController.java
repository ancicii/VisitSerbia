package sbnz.visitserbia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sbnz.visitserbia.DTOs.LoginDTO;
import sbnz.visitserbia.DTOs.UserDTO;
import sbnz.visitserbia.exceptions.EntityNotFoundException;
import sbnz.visitserbia.exceptions.EntityNotValidException;
import sbnz.visitserbia.model.RegisteredUser;
import sbnz.visitserbia.model.User;
import sbnz.visitserbia.repositories.UserRepository;
import sbnz.visitserbia.services.QuestionnaireService;
import sbnz.visitserbia.services.UserDetailsServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;


    @GetMapping()
    public ResponseEntity<Object> getUser(){
	    try{
	    	User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	RegisteredUser registeredUser = this.userRepository.findRegUserByEmail(u.getEmail());
			return new ResponseEntity<>(registeredUser, HttpStatus.OK);
		}catch(ClassCastException e){
	    	return new ResponseEntity<>("No User Logged In!", HttpStatus.BAD_REQUEST);
		}

    }

	@PostMapping(value = "/checkPassword")
	public ResponseEntity<String> checkPassword(@RequestBody String passwordToCheck){
		try {
			return new ResponseEntity<>(userDetailsService.checkPassword(passwordToCheck), HttpStatus.OK);
		} catch (EntityNotValidException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/editUser")
	public ResponseEntity<String> editUser(@Valid @RequestBody UserDTO user){
		try {
			return new ResponseEntity<>(userDetailsService.editUser(user), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
