package cn.leon.account.controller;

import cn.leon.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public void debit(@RequestParam String userId, @RequestParam BigDecimal orderMoney) {
        accountService.debit(userId, orderMoney);
    }


    @GetMapping("hello")
    public void hello(){
        System.out.println("hello");
    }

}
