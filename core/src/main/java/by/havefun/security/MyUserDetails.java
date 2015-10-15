package by.havefun.security;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import by.havefun.dao.BaseDAO;
import by.havefun.entity.AppUser;


@Transactional
public class MyUserDetails implements UserDetailsService {

    @Autowired
    BaseDAO baseDAO;

    Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser appUser = baseDAO.getEntity(AppUser.class, Restrictions.eq(AppUser.COL_EMAIL,email));
        if (appUser == null) {
            throw new UsernameNotFoundException("AppUser not found.");
        }
        
        boolean enabled = !(appUser.getRole().contains(AppUser.BAN));
        boolean credentialsNonExpired = true;
        boolean accountNonExpired = true;
        boolean accountNonLocked = true;

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(appUser.getRole()));

        return new User(appUser.getEmail(), appUser.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}