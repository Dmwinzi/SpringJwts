package com.example.demo.Service;

import com.example.demo.Entity.Entity;
import com.example.demo.Repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@org.springframework.stereotype.Service
public class Service  implements UserDetailsService {

    @Autowired
    Userrepository userrepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Entity user = userrepository.getuserByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("Could not find user");
        }
        return new UserDetail(user);

    }
}
