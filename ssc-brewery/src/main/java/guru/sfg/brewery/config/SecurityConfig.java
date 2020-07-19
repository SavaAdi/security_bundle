package guru.sfg.brewery.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ROOT_URL = "/";
    public static final String WEB_JARS = "/webjars/**";
    public static final String LOGIN = "/login";
    public static final String RESOURCES = "/resources/**";
    public static final String BEER_SEARCH = "/beers/find";
    public static final String BEER_SEARCH_NAME = "/beers*";


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(authorize -> {
            authorize
                    .antMatchers(ROOT_URL, WEB_JARS, LOGIN, RESOURCES).permitAll()
                    .antMatchers(BEER_SEARCH,BEER_SEARCH_NAME).permitAll();
        })
                .authorizeRequests()
                .anyRequest()
                .authenticated().and()
                .formLogin().and()
                .httpBasic();
    }

}
