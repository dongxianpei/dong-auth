package com.sansei.auth.config;

import com.sansei.boot.security.core.userdetails.UserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/11 16:46
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
public class BeanConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        //accessTokenConverter.setSigningKey("test_key");//配置JWT使用的秘钥
        return accessTokenConverter;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        final Map<String, Object> additionalInfo = new HashMap<>(3);
        return (oAuth2AccessToken, oAuth2Authentication) -> {
            UserDetails authUserDetails = (UserDetails) oAuth2Authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put("nickname", authUserDetails.getNickname());
            additionalInfo.put("username", authUserDetails.getUsername());

            additionalInfo.put("namingSpaceId", authUserDetails.getNamingSpaceId());
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
            return oAuth2AccessToken;
        };
    }

}
