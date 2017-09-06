package springboot17_walkthrough.springboot17.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot17_walkthrough.springboot17.models.Role;
import springboot17_walkthrough.springboot17.models.User;
import springboot17_walkthrough.springboot17.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional// either do everything or nothing
@Service//creates a component that works with Repo
public class SSUserDetailsService implements UserDetailsService{

    // taking a repo object from class to class without initializing it
    private UserRepository userRepository;
    public  SSUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    //determine if user has access
    // inside this method we can setup an if condition to check if user is disabled or enabled access
    // if (enable=true)..something like this
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException{
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                //don't say user not available in real life
                System.out.println("user not found with the provided username " + user.toString());

                return null;
            }

            System.out.println("user from username" + user.toString());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    getAuthorities(user));
        }
        catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }

    }
    //this is what looks for the type of the roles and grants specific role
    //this is a method that exists in spring to go ahead and check roles assigned to specific user
    private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority>authorities=new HashSet<GrantedAuthority>();
        for(Role role: user.getRoles())
        {
            GrantedAuthority grantedAuthority=new SimpleGrantedAuthority((role.getRole()));
            authorities.add(grantedAuthority);
        }
        System.out.println("user authorities are "+authorities.toString());
        return authorities;

    }


}
