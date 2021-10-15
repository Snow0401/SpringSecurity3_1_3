package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(s);
        UserDetails userDetails = new org.springframework.security.core.userdetails
                .User(user.getEmail(), user.getPassword(), user.getAuthorities());
        return userDetails;
    }

}
