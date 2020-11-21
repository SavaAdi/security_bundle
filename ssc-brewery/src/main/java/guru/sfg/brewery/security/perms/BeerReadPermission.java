package guru.sfg.brewery.security.perms;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) //Telling the java compiler that this annotation should be retained at runtime
@PreAuthorize("hasAuthority('beer.read')")
public @interface BeerReadPermission {
}
