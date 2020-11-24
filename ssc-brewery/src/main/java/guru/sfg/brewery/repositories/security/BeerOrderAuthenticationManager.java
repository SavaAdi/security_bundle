package guru.sfg.brewery.repositories.security;

import guru.sfg.brewery.domain.security.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Component
public class BeerOrderAuthenticationManager {

    public boolean customerIdMatches(Authentication authentication, UUID customerId) {
        User authenticatedUser = (User) authentication.getPrincipal();

        log.debug(() -> "Auth User Customer Id: " + authenticatedUser.getCustomer().getId() + " Customer Id: " + customerId);

        return authenticatedUser.getCustomer().getId().equals(customerId);
    }
}
