package br.com.gabriel.projetob2.service;

import br.com.gabriel.projetob2.builder.BuilderPurchase;
import br.com.gabriel.projetob2.infra.ApiInfra;
import br.com.gabriel.projetob2.model.Selic;
import br.com.gabriel.projetob2.request.Buy;
import br.com.gabriel.projetob2.request.Payment;
import br.com.gabriel.projetob2.request.Product;
import br.com.gabriel.projetob2.response.InstallmentsDefinition;
import br.com.gabriel.projetob2.service.exception.InstallmentsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BuyServiceTest {

    List<Selic> listSelic = createListSelic();
    Buy buy = createBuy();
    List<InstallmentsDefinition> installmentsDefinitionList = createInstallments();

    @Mock
    private ApiInfra apiInfra;

    @Mock
    private BuilderPurchase builderBuy;

    @InjectMocks
    private BuyService buyService;

    @BeforeEach
    void setUp() {

        when(apiInfra.getApiSelic()).thenReturn(listSelic);


    }

    @Test
    void shouldCreatePurchaseSuccess() {

        List<InstallmentsDefinition> result = buyService.createPurchase(buy);
        assertNotNull(result);
    }

    @Test
    void shouldCreatePurchaseWithInstallmentsWithSuccess() {
        buy.getPayment().setEntry(1000.0);
        buy.getPayment().setInstallments(1);
        List<InstallmentsDefinition> result = buyService.createPurchase(buy);
        assertNotNull(result);
    }

    @Test
    void shouldCreatePurchaseWithInstallmentsError() {
        buy.getPayment().setEntry(1000.0);
        buy.getPayment().setInstallments(0);
        assertThrows(InstallmentsException.class, () -> {
            buyService.createPurchase(buy);
        });
    }

    @Test
    void shouldCreatePurchaseWithEntryError() {
        buy.getPayment().setEntry(0.0);
        buy.getPayment().setInstallments(1);
        assertThrows(InstallmentsException.class, () -> {
            buyService.createPurchase(buy);
        });
    }

    @Test
    void shouldCreatePurchaseWithEntryAndPriceError() {
        buy.getProduct().setPrice(1000.0);
        buy.getPayment().setEntry(10000.0);
        buy.getPayment().setInstallments(2);
        assertThrows(InstallmentsException.class, () -> {
            buyService.createPurchase(buy);
        });
    }

    private List<Selic> createListSelic() {
        List<Selic> selics = new ArrayList<>();

        Selic selic = new Selic();
        selic.setData("27/10/2022");
        selic.setValor(0.0);

        selics.add(selic);
        return selics;
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

    private List<InstallmentsDefinition> createInstallments() {
        List<InstallmentsDefinition> installmentsDefinitionsList = new ArrayList<>();

        InstallmentsDefinition installmentsDefinitions = new InstallmentsDefinition();
        installmentsDefinitions.setTax(0.0);
        installmentsDefinitions.setInstallmentsNum(1);
        installmentsDefinitions.setPrice(10000.0);

        installmentsDefinitionsList.add(installmentsDefinitions);

        return installmentsDefinitionsList;
    }
}