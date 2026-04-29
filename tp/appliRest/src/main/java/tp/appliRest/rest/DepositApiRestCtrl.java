package tp.appliRest.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.appliRest.dto.Account;
import tp.appliRest.dto.DepositRequest;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/my-bank-api/v1")
public class DepositApiRestCtrl implements DepositsApi {
    @Override
    public ResponseEntity<Void> depositToAccount(DepositRequest depositRequest) {
        //Execute the business logic through Service/Utils/Repository classes
        return ResponseEntity.ok().build();
    }

}
