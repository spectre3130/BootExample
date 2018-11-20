package com.spectre.example.config;

import com.spectre.example.user.AccountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //TODO 토큰스토어 사용관련...
    private final TokenStore tokenStore;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final AccountService accountService;

    public AuthorizationServerConfig(TokenStore tokenStore, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, AccountService accountService) {
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("spectre-client")
                .secret(passwordEncoder.encode("spectre-password"))
                //TODO authorizedGrantTypes 에 대해 알아보기
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(1*60*60)
                .refreshTokenValiditySeconds(6*60*60);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(accountService);
    }
}
