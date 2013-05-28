package com.ffbit.bcrypt.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Access(AccessType.PROPERTY)
public class User extends AbstractPersistable<Integer> implements UserDetails {
    private static final long serialVersionUID = 8608334294620998454L;

    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private Set<UserRole> authorities;

    private User() {
    }

    public User(String username, String password) {
        this();

        this.username = username;
        this.password = password;

        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;

        this.authorities = new HashSet<UserRole>();
    }

    @NotNull
    @Length(min = 4, max = 40)
    @Column(name = "username")
    @Override
    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authorities")
    @Enumerated(EnumType.STRING)
    @Override
    public Set<UserRole> getAuthorities() {
        return authorities;
    }

    private void setAuthorities(Set<UserRole> authorities) {
        this.authorities = authorities;
    }

    public boolean grantAuthority(UserRole authority) {
        return authorities.add(authority);
    }

    public boolean revokeAuthority(UserRole authority) {
        return authorities.remove(authority);
    }

    @NotNull
    @Length(min = 6, max = 60)
    @Column(name = "password")
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Column(name = "account_non_expired")
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @NotNull
    @Column(name = "account_non_locked")
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @NotNull
    @Column(name = "credentials_non_expired")
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @NotNull
    @Column(name = "enabled")
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
