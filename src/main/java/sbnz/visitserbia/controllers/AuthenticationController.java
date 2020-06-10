package sbnz.visitserbia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import sbnz.visitserbia.DTOs.LoginDTO;
import sbnz.visitserbia.DTOs.UserDTO;
import sbnz.visitserbia.exceptions.EntityAlreadyExistsException;
import sbnz.visitserbia.exceptions.EntityNotFoundException;
import sbnz.visitserbia.exceptions.EntityNotValidException;
import sbnz.visitserbia.security.TokenUtils;
import sbnz.visitserbia.services.AuthenticationService;
import sbnz.visitserbia.services.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.MalformedURLException;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping(value = "/login")
    @PreAuthorize("!(hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER'))")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO){
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getEmail(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getEmail());
            userDetailsService.checkIsVerified(loginDTO.getEmail());
            return new ResponseEntity<>(tokenUtils.generateToken(details), HttpStatus.OK);
        }
        catch(UsernameNotFoundException | EntityNotValidException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (BadCredentialsException ex) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.BAD_REQUEST);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("!(hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER'))")
    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserDTO user, HttpServletRequest request) {
        try{
            return new ResponseEntity<>(authenticationService.register(user, request), HttpStatus.OK);
        }catch (EntityAlreadyExistsException | MalformedURLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/verify-account")
    public ResponseEntity<Object> verifyUser(@RequestParam("token") String token){
        try{
            return new ResponseEntity<>(authenticationService.verifyUser(token), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
