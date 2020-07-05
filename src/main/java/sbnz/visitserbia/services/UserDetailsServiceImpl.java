package sbnz.visitserbia.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sbnz.visitserbia.DTOs.ResponsesDTO;
import sbnz.visitserbia.DTOs.UserDTO;
import sbnz.visitserbia.exceptions.EntityAlreadyExistsException;
import sbnz.visitserbia.exceptions.EntityNotFoundException;
import sbnz.visitserbia.exceptions.EntityNotValidException;
import sbnz.visitserbia.model.MaritalStatus;
import sbnz.visitserbia.model.RegisteredUser;
import sbnz.visitserbia.model.User;
import sbnz.visitserbia.repositories.UserRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
    } else {
    	return user;
    }
  }

  public UserDetails checkIsVerified(String email) throws EntityNotValidException, EntityNotFoundException {
    User user = userRepository.findByEmail(email);
    if(user == null){
      throw new EntityNotFoundException("Account not found");
    }
    else if(user.getVerified()){
      return user;
    }else{
      throw new EntityNotValidException("Your account is not verified!");
    }
  }

  public String checkPassword(String passwordToCheck) throws EntityNotValidException {
    User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if(passwordEncoder.matches(passwordToCheck, u.getPassword())){
      return "true";
    }
    else{
      throw new EntityNotValidException("Your old password is incorrect!");
    }


  }

  public String editUser(UserDTO user) throws EntityNotFoundException {
    if(userRepository.findByEmail(user.getEmail())== null){
      throw new EntityNotFoundException("User not found!");
    }
    RegisteredUser registeredUser = userRepository.findRegUserByEmail(user.getEmail());
    if(user.getPassword() != null){
      registeredUser.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    registeredUser.setNumberOfKids(user.getNumberOfKids());
    registeredUser.setYears(user.getYears());
    registeredUser.setMaritalStatus(MaritalStatus.valueOf(user.getMaritalStatus()));
    registeredUser.setFirstName(user.getFirstName());
    registeredUser.setLastName(user.getLastName());
    userRepository.save(registeredUser);
    return "Profile successfully updated!";
  }
}
