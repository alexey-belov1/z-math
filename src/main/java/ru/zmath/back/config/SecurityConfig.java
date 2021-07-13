package ru.zmath.back.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import ru.zmath.back.security.UserDetailsServiceImpl;
import ru.zmath.back.filter.JWTAuthenticationFilter;
import ru.zmath.back.filter.JWTAuthorizationFilter;

import java.util.Arrays;
import java.util.List;

import static ru.zmath.back.filter.JWTAuthenticationFilter.SIGN_UP_URL;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private HandlerExceptionResolver resolver;

    public SecurityConfig(
        UserDetailsServiceImpl userDetailsService,
        BCryptPasswordEncoder bCryptPasswordEncoder,
        @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver)
    {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.resolver = resolver;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
                .and()
            .csrf()
                .disable()
            .exceptionHandling()
                .authenticationEntryPoint(((request, response, e) -> resolver.resolveException(request, response, null, e)))
                .and()
            .authorizeRequests()   //Это строкой мы говорим предоставить разрешения для следующих url
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, "/api/review").permitAll()
                .antMatchers(HttpMethod.GET, "/api/status").permitAll()
                .antMatchers(HttpMethod.GET, "/api/subject").permitAll()
                .antMatchers(HttpMethod.GET, "/api/task").permitAll()
                .antMatchers(HttpMethod.GET, "/api/method").permitAll()
                .antMatchers(HttpMethod.GET, "/api/attached-files/download/{id}").permitAll()
                .anyRequest().authenticated()
                .and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .addFilter(new JWTAuthorizationFilter(authenticationManager(), this.userDetailsService))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            /*.httpBasic()    // Чтобы задать аутентификацию типа Http Basic, строка в коде должна быть такая*/
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration()
            .applyPermitDefaultValues();    //Задает по умолчанию конфигурцию, иначе будет сильно ограничена
        corsConfiguration.setExposedHeaders(Arrays.asList("x-app-alert", "x-app-entity", "x-app-param", "x-total-count"));
        corsConfiguration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
