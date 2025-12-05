package com.example.bankapp.controller;

import com.example.bankapp.dto.account.*;
import com.example.bankapp.dto.transaction.TransactionDto;
import com.example.bankapp.model.Account;
import com.example.bankapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/me")
    public ResponseEntity<AccountDto> getMyDetails() {
        return ResponseEntity.ok(accountService.getMyDetails());
    }


}
