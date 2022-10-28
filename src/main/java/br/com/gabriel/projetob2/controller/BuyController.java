package br.com.gabriel.projetob2.controller;

import br.com.gabriel.projetob2.request.Buy;
import br.com.gabriel.projetob2.response.InstallmentsDefinition;
import br.com.gabriel.projetob2.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pay")
public class BuyController {

    @Autowired
    private BuyService buyService;

    @PostMapping
    public ResponseEntity<List<InstallmentsDefinition>> buy(@RequestBody Buy buy) {
        return ResponseEntity.ok(buyService.createPurchase(buy));
    }
}
