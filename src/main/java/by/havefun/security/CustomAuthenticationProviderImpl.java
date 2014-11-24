package by.havefun.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProviderImpl  extends AbstractUserDetailsAuthenticationProvider {


	@Autowired
    UserDetailsService userDetailsService;

    //~ Methods ========================================================================================================
	
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }

        String presentedPassword = authentication.getCredentials().toString();
        presentedPassword = KeccakUtil.getHash(presentedPassword); 
        if (!presentedPassword.equals(userDetails.getPassword())) {
        	
            logger.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }
    }

    

    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        UserDetails loadedUser;

        try {
            loadedUser = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException notFound) {
            if(authentication.getCredentials() != null) {
                String presentedPassword = authentication.getCredentials().toString();
                //passwordEncoder.isPasswordValid(userNotFoundEncodedPassword, presentedPassword, null);
            }
            throw notFound;
        } catch (Exception repositoryProblem) {
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
            throw new AuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }
}
