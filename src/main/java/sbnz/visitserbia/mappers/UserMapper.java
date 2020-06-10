package sbnz.visitserbia.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sbnz.visitserbia.DTOs.UserDTO;
import sbnz.visitserbia.model.MaritalStatus;
import sbnz.visitserbia.model.RegisteredUser;

@Component
public class UserMapper implements IMapper<RegisteredUser, UserDTO> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisteredUser toEntity(UserDTO userDTO){
        RegisteredUser u = new RegisteredUser();
        u.setId(null);
        u.setVerified(false);
        u.setEmail(userDTO.getEmail());
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setNumberOfKids(userDTO.getNumberOfKids());
        u.setYears(userDTO.getYears());
        u.setMaritalStatus(MaritalStatus.valueOf(userDTO.getMaritalStatus()));
        return u;
    }

    @Override
    public UserDTO toDTO(RegisteredUser user){
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setId(user.getId());
        dto.setVerified(user.getVerified());
        dto.setNumberOfKids(user.getNumberOfKids());
        dto.setYears(user.getYears());
        dto.setMaritalStatus(user.getMaritalStatus().name());
        return dto;
    }
}
