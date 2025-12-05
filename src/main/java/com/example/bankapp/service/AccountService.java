package com.example.bankapp.service;

import com.example.bankapp.dto.account.*;
import com.example.bankapp.dto.transaction.TransactionDto;
import com.example.bankapp.model.Account;
import com.example.bankapp.model.Role;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.model.TransactionType;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService   {

    private  final AccountRepository accountRepository;
    private  final TransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountDto getMyDetails() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("username"+"  "+username);
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return AccountDto.builder()
                .username(account.getUsername())
                .balance(account.getBalance())
                .role(account.getRole())
                .build();
    }

    public Account getLoggedInAccount() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Logged in account not found"));
    }

    public Account findAccountByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found with username: " + username));
    }

}

