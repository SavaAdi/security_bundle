package guru.sfg.brewery.security.perms;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//    the beerOrderAuthenticationManager bean from the context
@PreAuthorize("hasAuthority('order.read') OR " +
        "hasAuthority('customer.order.read') " +
        "AND @beerOrderAuthenticationManager.customerIdMatches(authentication, #customerId)")
@Retention(RetentionPolicy.RUNTIME)
public @interface BeerOrderReadPermission {
}
