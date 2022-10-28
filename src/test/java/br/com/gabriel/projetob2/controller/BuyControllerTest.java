package br.com.gabriel.projetob2.controller;

import br.com.gabriel.projetob2.request.Buy;
import br.com.gabriel.projetob2.request.Payment;
import br.com.gabriel.projetob2.request.Product;
import br.com.gabriel.projetob2.response.InstallmentsDefinition;
import br.com.gabriel.projetob2.service.BuyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BuyControllerTest {

    List<InstallmentsDefinition> value = new ArrayList<>();
    Buy buy = createBuy();

    @Mock
    private BuyService buyService;

    @InjectMocks
    private BuyController buyController;

    @BeforeEach
    void setUp() {

        InstallmentsDefinition installments = new InstallmentsDefinition();
        installments.setInstallmentsNum(1);
        installments.setPrice(10000.0);
        installments.setTax(0.0);

        value.add(installments);
        when(buyService.createPurchase(any(Buy.class))).thenReturn(value);
    }

    @Test
    void shouldInsertPaymentWithSuccess() {

        ResponseEntity<List<InstallmentsDefinition>> result = buyController.buy(buy);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    private Buy createBuy() {
        Buy buy = new Buy();
        Product product = new Product();
        product.setId(1L);
        product.setPrice(10000.0);
        product.setName("BMW");

        Payment payment = new Payment();
        payment.setInstallments(1);
        payment.setEntry(10000.0);

        buy.setProduct(product);
        buy.setPayment(payment);

        return buy;
    }
}