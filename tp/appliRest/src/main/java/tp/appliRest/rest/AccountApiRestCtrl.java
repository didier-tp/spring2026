package tp.appliRest.rest;


import tp.appliRest.dto.Account;
import tp.appliRest.dto.DepositRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/my-bank-api/v1")
public class AccountApiRestCtrl  implements AccountsApi {

    @Override
    public ResponseEntity<Account> getAccount(String id) {
        //Execute the business logic through Service/Utils/Repository classes
        return ResponseEntity.ok(new Account().number(id).label("compte_"+id).balance(BigDecimal.valueOf(100.0)));
    }
}
