package com.spectre.example.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository repository;

    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Optional<Account> findOne(Long id) {
        return repository.findById(id);
    }

    public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.save(account);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @PostConstruct
    public void init() {
        Account account = repository.findByUsername("spectre");
        if(account == null) {
            Account account1 = new Account();
            account1.setUsername("spectre");
            account1.setPassword("1234");
            this.save(account1);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findByUsername(username);
        return new User(account.getUsername(), account.getPassword(), getAuthorities());
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
