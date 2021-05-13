package com.talesayajins.services;

import com.talesayajins.entities.User;
import com.talesayajins.repositories.UserRepository;
import com.talesayajins.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repo.findByLogin(login);
        if(user == null){
            throw new UsernameNotFoundException(login);
        }
        return new UserSS(user.getId(), user.getLogin(), user.getSenha());
    }
}
