package com.example.project.service;

import com.example.project.model.RegisterInfo;
import com.example.project.repository.RegisterRepository;
import com.example.project.utilities.GrantedAuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class RegisterService implements UserDetailsService {
    @Autowired
    RegisterRepository registerRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegisterInfo register = null;
        try {
            register = registerRepository.findByUsername(username);
            if (register == null) {
                throw new UsernameNotFoundException("user" + username + "is not found");
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        UserDetails userDetails = new User(register.getUsername(),
                register.getPassword(), GrantedAuthorityUtil.getSetRoles());
        return userDetails;
    }
}
