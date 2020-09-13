package cn.acyou.scorpio.security.config.handler;

import cn.acyou.scorpio.security.model.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author youfang
 * @version [1.0.0, 2020/6/29]
 **/
@Service
public class UserService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername  根据用户名查找用户" + username);
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new Resources("demo:list"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_AUDITOR"));

        //List<GrantedAuthority> authorities =
        //        AuthorityUtils.commaSeparatedStringToAuthorityList("demo:list,ROLE_ADMIN,ROLE_AUDITOR");//设置权限和角色
        UserDetails buildUser = User.builder()
                .username(username)
                .password("123")
                .disabled(false)
                .accountExpired(false)
                .accountLocked(false)
                .authorities(authorities).build();
        return buildUser;
    }
}
