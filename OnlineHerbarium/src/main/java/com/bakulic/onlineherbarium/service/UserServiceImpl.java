package com.bakulic.onlineherbarium.service;


import com.bakulic.onlineherbarium.exceptions.*;
import com.bakulic.onlineherbarium.model.Role;
import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.dto.RegisterUserAccountDTO;
import com.bakulic.onlineherbarium.repository.RoleRepository;
import com.bakulic.onlineherbarium.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Service
public class UserServiceImpl implements UserService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


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

        Role role = roleRepository.findById(2);

        User newUser = new User(
                registerUserAccountDTO.getFullname(),
                registerUserAccountDTO.getEmail(),
                bCryptPasswordEncoder.encode(registerUserAccountDTO.getPassword()),
                Arrays.asList(role),
                registerUserAccountDTO.getRole());


        log.info(String.format("User %s has been created.", newUser.getUserId()));
        return userRepository.save(newUser);
    }

        public User getUser () {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserEmail = authentication.getName();
                User currentUser = userRepository.findByEmail(currentUserEmail);
                return currentUser;
            }
            return null;
        }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("Invalid username or password!");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),  mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    /**
     * check if the email has not been registered
     */
    public void checkIfEmailNotUsed(String email) {
        List<User> users = getAllUsers();
        for(User u : users){
            if(Objects.equals(u.getEmail(), email)){
                String msg = String.format("The email %s is already in use from another user!", email);
                throw new InvalidUsernameException(msg);
            }
        }
    }
}

