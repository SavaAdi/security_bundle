package guru.sfg.brewery.bootstrap;

import guru.sfg.brewery.config.SecurityConfig;
import guru.sfg.brewery.domain.security.Authority;
import guru.sfg.brewery.domain.security.User;
import guru.sfg.brewery.repositories.security.AuthorityRepository;
import guru.sfg.brewery.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(authorityRepository.count() == 0){
            loadSecurityData();
        }
    }

    private void loadSecurityData() {
        Authority admin = authorityRepository.save(Authority.builder().role("ADMIN").build());
        Authority user = authorityRepository.save(Authority.builder().role("USER").build());
        Authority customer = authorityRepository.save(Authority.builder().role("CUSTOMER").build());

        userRepository.save(User.builder()
                .username("spring")
                .password(passwordEncoder.encode("guru"))
                .authority(admin).build());

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .authority(user).build());

        userRepository.save(User.builder()
                .username("scott")
                .password(passwordEncoder.encode("tiger"))
                .authority(customer).build());
    }


}
