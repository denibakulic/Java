package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.exceptions.*;
import com.bakulic.onlineherbarium.model.Role;
import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.dto.CreateOrUpdateUserDTO;
import com.bakulic.onlineherbarium.model.dto.LoginUserAccountDTO;
import com.bakulic.onlineherbarium.model.dto.RegisterUserAccountDTO;
import com.bakulic.onlineherbarium.repository.RoleRepository;
import com.bakulic.onlineherbarium.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UserService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Value("${microservice.security.salt}")
    private String salt;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    /**
     * list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * user register
     */

    public User register(RegisterUserAccountDTO registerUserAccountDTO) {
        if (registerUserAccountDTO == null) {
            throw new InvalidDataException("User account data cannot be null");
        }

        /*checkIfUsernameNotUsed(registerUserAccountDTO.getUsername());
        usernameValidator.checkUsername(registerUserAccountDTO.getUsername());
        checkIfEmailNotUsed(registerUserAccountDTO.getEmail());
        emailValidator.checkEmail(registerUserAccountDTO.getEmail());
        passwordValidator.checkPassword(registerUserAccountDTO.getPassword());
*/


        // create the new user account: not all the user information required
        var newUser = new User();
        newUser.setFullname(registerUserAccountDTO.getFullname());
        newUser.setEmail(registerUserAccountDTO.getEmail());
        newUser.setUsername(registerUserAccountDTO.getUsername());
        newUser.setPassword(EncryptionService.encrypt(registerUserAccountDTO.getPassword(), salt));
        Role role =roleRepository.findByName("user");
        newUser.setRole(role);

        var userCreated = userRepository.save(newUser);

        log.info(String.format("User %s has been created.", userCreated.getUserId()));
        return userCreated;
    }

    /**
     * user login
     */
    public User login(LoginUserAccountDTO loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (username.isEmpty() || password.isEmpty()) {
            throw new InvalidLoginException("Username or Password cannot be null or empty");
        }

        User user = userRepository.findByUsername(loginUser.getUsername());
        if (user == null) {
            // invalid username
            throw new InvalidLoginException("Invalid username or password");
        }

        log.info(String.format("Login request from %s", username));

        // check the password
        if (EncryptionService.isPasswordValid(password, user.getPassword(), salt)) {
            log.info(String.format("Valid login for %s", username));
        } else {
            throw new InvalidLoginException("Invalid username or password");
        }
        return user;
    }

    /**
     * user update
     */
    public User updateUser(CreateOrUpdateUserDTO updateUserDTO, int id) {

        if (updateUserDTO == null) {
            throw new InvalidDataException("User data cannot be null");
        }/*
        checkIfEmailNotUsed(updateUserDTO.getEmail());
        emailValidator.checkEmail(updateUserDTO.getEmail());
        passwordValidator.checkPassword(updateUserDTO.getPassword());
*/

        User user = getUserRepository().findById(id);
        user.setFullname(updateUserDTO.getFullname());
        user.setEmail(updateUserDTO.getEmail());

        user.setPassword(EncryptionService.encrypt(updateUserDTO.getPassword(), salt));
        user.setUsername(updateUserDTO.getUsername());


        var userUpdated = userRepository.save(user);
        log.info(String.format("User %s has been updated.", user.getUserId()));
        return userUpdated;
    }

}
