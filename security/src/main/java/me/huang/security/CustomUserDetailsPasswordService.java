package me.huang.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsPasswordService implements UserDetailsPasswordService {
    @Override
    public UserDetails updatePassword(UserDetails userDetails, String s) {
        return User.withUserDetails(userDetails).password(null).build();
    }
}
