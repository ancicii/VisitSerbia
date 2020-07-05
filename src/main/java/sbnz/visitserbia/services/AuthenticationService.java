package sbnz.visitserbia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sbnz.visitserbia.DTOs.UserDTO;
import sbnz.visitserbia.exceptions.EntityAlreadyExistsException;
import sbnz.visitserbia.exceptions.EntityNotFoundException;
import sbnz.visitserbia.mappers.UserMapper;
import sbnz.visitserbia.model.*;
import sbnz.visitserbia.repositories.UserRepository;
import sbnz.visitserbia.repositories.VerificationTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public UserDTO register(UserDTO user, HttpServletRequest request) throws EntityAlreadyExistsException, MalformedURLException {
        String url = new URL(request.getRequestURL().toString()).getAuthority();

        if(userRepository.findByEmail(user.getEmail())!= null){
            throw new EntityAlreadyExistsException("Email already taken!");
        }else{
            RegisteredUser regUser = new RegisteredUser();
            regUser.setId(null);
            regUser.setEmail(user.getEmail());
            regUser.setPassword(passwordEncoder.encode(user.getPassword()));
            regUser.setFirstName(user.getFirstName());
            regUser.setLastName(user.getLastName());
            regUser.setMaritalStatus(MaritalStatus.valueOf(user.getMaritalStatus()));
            regUser.setYears(user.getYears());
            regUser.setNumberOfKids(user.getNumberOfKids());
            regUser.setVerified(false);
            List<Authority> authorities = new ArrayList<Authority>();
            Authority a = new Authority();
            a.setType(UserType.ROLE_USER);
            authorities.add(a);
            regUser.setAuthorities(authorities);

            RegisteredUser u = userRepository.save(regUser);
            VerificationToken verificationToken = new VerificationToken(regUser);
            emailService.sendVerificationEmail(regUser,url,verificationToken);
            verificationTokenRepository.save(verificationToken);
            return userMapper.toDTO(u);
        }
    }

    public boolean verifyUser(String token) throws EntityNotFoundException {

//        File file = new File("D:\\Faks\\VIII semestar - SBNZ\\Slike za SBNZ\\Vranje\\Dukat.jpg");
//        byte[] picInBytes = new byte[(int) file.length()];
//        FileInputStream fileInputStream = new FileInputStream(file);
//        fileInputStream.read(picInBytes);
//        fileInputStream.close();
//        Attraction a = attractionRepository.getOne(1L);
//        a.setImage(picInBytes);
        VerificationToken vt = verificationTokenRepository.findByToken(token);
        if(vt==null || vt.isExpired()){
            throw new EntityNotFoundException("The link is invalid or broken!");
        }
        User u = userRepository.findByEmail(vt.getUser().getEmail());
        u.setVerified(true);
        userRepository.save(u);
        return true;
    }
}
