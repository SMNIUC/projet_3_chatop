package com.openclassrooms.chatop.configuration;

import com.openclassrooms.chatop.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record MyUserDetails(User user) implements UserDetails {
    /**
     * Constructs a new {@code MyUserDetails} instance for the given {@link User}.
     *
     * @param user the {@link User} entity to be adapted to {@link UserDetails}
     */
    public MyUserDetails {
    }

    /**
     * Returns the authorities granted to the user.
     *
     * <p>
     * The authority is typically a role, such as "ROLE_USER" or "ROLE_ADMIN".
     * </p>
     *
     * @return a collection of granted authorities (roles) for the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
        return List.of(authority);
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * @return the user's username
     */
    @Override
    public String getUsername() {
        return user.getName();
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * <p>
     * This implementation always returns {@code true}, indicating that the account is not expired.
     * </p>
     *
     * @return {@code true} if the account is not expired, {@code false} otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     *
     * <p>
     * This implementation always returns {@code true}, indicating that the account is not locked.
     * </p>
     *
     * @return {@code true} if the account is not locked, {@code false} otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     *
     * <p>
     * This implementation always returns {@code true}, indicating that the credentials are not expired.
     * </p>
     *
     * @return {@code true} if the credentials are not expired, {@code false} otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * <p>
     * This implementation always returns {@code true}, indicating that the user is enabled.
     * </p>
     *
     * @return {@code true} if the user is enabled, {@code false} otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
