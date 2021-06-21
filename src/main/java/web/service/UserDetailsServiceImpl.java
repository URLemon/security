package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserDao dao;

    public UserDetailsServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = dao.getUserByLogin(s);
        if(user == null){
            throw new UsernameNotFoundException("nop");
        }
        return user;
    }
}
