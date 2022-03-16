package com.sansei.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author ：dongxp
 * @date ：Created in 2022/1/11 14:58
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    private static final String SECRETPREFIX = "{noop}";

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private TokenEnhancer tokenEnhancer;

    @Override
    public void configure(ClientDetailsServiceConfigurer clents) throws Exception {
        clents.inMemory()
                .withClient("default")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("all")
                .secret(passwordEncoder.encode("default_1q2w3e4r!"));
        //.accessTokenValiditySeconds(10000)
        //.refreshTokenValiditySeconds(1500);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()")
                .passwordEncoder(passwordEncoder);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer, jwtAccessTokenConverter));
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter)
                .userDetailsService(userDetailsService);

    }

}
