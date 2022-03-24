package com.bakulic.CinemaTicketShop.service;

import com.bakulic.CinemaTicketShop.model.User;
import com.bakulic.CinemaTicketShop.model.dto.requests.CreateOrUpdateUserDTO;
import com.bakulic.CinemaTicketShop.model.dto.requests.LoginUserAccountDTO;
import com.bakulic.CinemaTicketShop.model.dto.requests.RegisterUserAccountDTO;
import com.bakulic.CinemaTicketShop.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bakulic.CinemaTicketShop.exceptions.*;
import com.bakulic.CinemaTicketShop.service.validation.*;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
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


    private PasswordValidator passwordValidator;
    private EmailValidator emailValidator;
    private UsernameValidator usernameValidator;

    @Value("${microservice.security.salt}")
    private String salt;

    public UserService() {
        passwordValidator = new PasswordValidator();
        emailValidator = new EmailValidator();
        usernameValidator = new UsernameValidator();

    }

    /**
     * list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    /**
     * get user by username
     */
    public User getUserByUsername(String username) {
        if (username == null) {
            throw new InvalidUsernameException("Username cannot be null");
        }
        return userRepository.findByUsername(username);
    }

    /**
     * get user by email
     */
    public User getUserByEmail(String email) {
        if (email == null) {
            throw new InvalidEmailException("email cannot be null");
        }
        return userRepository.findByEmail(email);
    }

    /**
     * user update
     */
    public User updateUser(CreateOrUpdateUserDTO updateUserDTO, int id) {

        if (updateUserDTO == null) {
            throw new InvalidDataException("User data cannot be null");
        }

        passwordValidator.checkPassword(updateUserDTO.getPassword());
        emailValidator.checkEmail(updateUserDTO.getEmail());
        usernameValidator.checkUsername(updateUserDTO.getUsername());
        //checkIfUsernameNotUsed(updateUserDTO.getUsername());
        //checkIfEmailNotUsed(updateUserDTO.getEmail());


        User user = getUserRepository().findById(id);
        // update the user
        user.setFullname(updateUserDTO.getFullname());
        user.setEmail(updateUserDTO.getEmail());

        // using the user's salt to secure the new validated password
        user.setPassword(EncryptionService.encrypt(updateUserDTO.getPassword(), salt));
        user.setUsername(updateUserDTO.getUsername());


        User userUpdated = userRepository.save(user);
        log.info(String.format("User %s has been updated.", user.getUserId()));
        return userUpdated;
    }


    /**
     * user register
     */

    public User register(RegisterUserAccountDTO registerUserAccountDTO) {
        if (registerUserAccountDTO == null) {
            throw new InvalidDataException("User account data cannot be null");
        }

        checkIfUsernameNotUsed(registerUserAccountDTO.getUsername());
        passwordValidator.checkPassword(registerUserAccountDTO.getPassword());
        emailValidator.checkEmail(registerUserAccountDTO.getEmail());
        checkIfEmailNotUsed(registerUserAccountDTO.getEmail());

        // create the new user account: not all the user information required
        User newUser = new User();
        newUser.setFullname(registerUserAccountDTO.getFullname());
        newUser.setEmail(registerUserAccountDTO.getEmail());
        newUser.setUsername(registerUserAccountDTO.getUsername());
        newUser.setPassword(EncryptionService.encrypt(registerUserAccountDTO.getPassword(), salt));
        newUser.setRole("user");

        User userCreated = userRepository.save(newUser);

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

        User user = getUserByUsername(username);
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
     * check if the username has not been registered
     */
    public void checkIfUsernameNotUsed(String username) {
        User userByUsername = getUserByUsername(username);
        if (userByUsername != null) {
            String msg = String.format("The username %s it's already in use from another user with ID = %s",
                    userByUsername.getUsername(), userByUsername.getUserId());
            throw new InvalidUsernameException(msg);
        }
    }

    /**
     * check if the email has not been registered
     */
    public void checkIfEmailNotUsed(String email) {
        User userByEmail = getUserByEmail(email);
        if (userByEmail != null) {
            String msg = String.format("The email %s it's already in use from another user with ID = %s",
                    userByEmail.getEmail(), userByEmail.getUserId());
            throw new InvalidEmailException(String.format("This email %s it's already in use.",
                    userByEmail.getEmail()));
        }
    }

    /**
     * delete user
     */
    /*public void deleteUserById(int id) {

        User user = userRepository.deleteById();

        if (user == null) {
            throw new ObjectNotFoundException(String.format("User not found with Id = %s", id));
        }

        userRepository.deleteById(id);
        log.info(String.format("User %s has been deleted.", id));
    }
*/
}
