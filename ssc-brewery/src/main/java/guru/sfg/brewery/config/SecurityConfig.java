package guru.sfg.brewery.config;


import guru.sfg.brewery.security.AdiPasswordEncoderFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //Enable @PreAuthorized annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ROOT_URL = "/";
    public static final String WEB_JARS = "/webjars/**";
    public static final String LOGIN = "/login";
    public static final String RESOURCES = "/resources/**";
    public static final String BEER_SEARCH = "/beers/find";
    public static final String BEER_SEARCH_NAME = "/beers*";
    public static final String BEER_API = "/api/v1/beer/**";
    public static final String BREWERY = "/brewery/breweries/**";
    public static final String BREWERY_API = "/brewery/api/v1/breweries/**";

    @Bean
    PasswordEncoder passwordEncoder() {
        return AdiPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(authorize -> {
            authorize
                    .antMatchers("/h2-console/**").permitAll() // Remove in production
                    .antMatchers(ROOT_URL, WEB_JARS, LOGIN, RESOURCES).permitAll();
        })
                .authorizeRequests()
                .anyRequest()
                .authenticated().and()
                .formLogin().and()
                .httpBasic()
                .and().csrf().disable();

        //h2 console config
        http.headers().frameOptions().sameOrigin();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("spring")
//                .password("{bcrypt14}$2a$14$b11rLt8CGq.UIttUDlANs.RmsjTY5MV33sbKtDRjy4cROjlPeLwhe") //you need to specify the password encoder (noop stores it as free text)
//                .roles("ADMIN")
//                .and()
//                .withUser("user")
//                .password("{sha256}977378b4618baf6e1c5093d082f82d0188a6396be86aeff8fbfebfb815697adb7ddadcdf9fd8131b")
//                .roles("USER");
//    }


    //    @Override
//    @Bean //You need to mark it as a bean in order for it to be brought in the spring context
//    protected UserDetailsService userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("spring")
//                .password("guru")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}
