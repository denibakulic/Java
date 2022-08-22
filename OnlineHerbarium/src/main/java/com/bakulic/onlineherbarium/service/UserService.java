package com.bakulic.onlineherbarium.service;

import com.bakulic.onlineherbarium.model.User;
import com.bakulic.onlineherbarium.model.dto.RegisterUserAccountDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register (RegisterUserAccountDTO registerUserAccountDTO);
}
