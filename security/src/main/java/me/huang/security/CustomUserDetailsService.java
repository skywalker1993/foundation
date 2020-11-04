package me.huang.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Function<String, String> passwordEncoder = (plainPassword) ->
                PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(plainPassword);

        Map<String, UserDetails> userMap = new HashMap<>();
        userMap.put("admin",
                User.builder()
                        .username("admin").password("admin")
                        .authorities("ROLE_ADMIN", "admin")
                        .accountExpired(false).accountLocked(false)
                        .credentialsExpired(false).disabled(false)
                        .passwordEncoder(passwordEncoder).build());

        return userMap.getOrDefault(s, null);
    }
}
