package sbnz.visitserbia.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sbnz.visitserbia.DTOs.ResponsesDTO;
import sbnz.visitserbia.exceptions.EntityNotFoundException;
import sbnz.visitserbia.exceptions.EntityNotValidException;
import sbnz.visitserbia.model.User;
import sbnz.visitserbia.repositories.UserRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;


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

}
