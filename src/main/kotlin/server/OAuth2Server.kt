package server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore


@SpringBootApplication
@EnableAuthorizationServer
class OAuth2Server {


}

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }




}

@Configuration
class OAuth2ServerConfig : AuthorizationServerConfigurer {


    override fun configure(authorizationServerSecurityConfigurer: AuthorizationServerSecurityConfigurer) {

    }

    override fun configure(clientDetailsServiceConfigurer: ClientDetailsServiceConfigurer) {
        clientDetailsServiceConfigurer
                .inMemory()
                .withClient("1233")
                //raw secret is abcc
                .secret("\$2a\$10\$6AkSrzu8lYix1anvSM0/turYR7eW1ZBc6NbNvgS3SQFfzDF8u9lu6")
                .authorizedGrantTypes("client_credentials")
                .scopes("read")
                .resourceIds("authorization-server")

    }

    override fun configure(authorizationServerEndpointsConfigurer: AuthorizationServerEndpointsConfigurer) {

        authorizationServerEndpointsConfigurer
                .tokenStore(jwtTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter())
    }

    @Bean
    fun jwtTokenStore() = JwtTokenStore(jwtAccessTokenConverter())

    @Bean
    fun jwtAccessTokenConverter() = JwtAccessTokenConverter().also {
        it.setSigningKey("qwerty")
    }
}


fun main(args: Array<String>) {
    runApplication<OAuth2Server>(*args)
}