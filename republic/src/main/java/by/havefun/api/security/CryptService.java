package by.havefun.api.security;

import org.springframework.stereotype.Service;

/**
 * Created by user on 15.10.15.
 */
@Service
public class CryptService {
    public String decrypt(String tokenString) {
        return tokenString;
    }
}
