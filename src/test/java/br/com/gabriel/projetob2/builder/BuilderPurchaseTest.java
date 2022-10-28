package br.com.gabriel.projetob2.builder;

import br.com.gabriel.projetob2.model.Selic;
import br.com.gabriel.projetob2.request.Buy;
import br.com.gabriel.projetob2.request.Payment;
import br.com.gabriel.projetob2.request.Product;
import br.com.gabriel.projetob2.response.InstallmentsDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class BuilderPurchaseTest {

    List<Selic> listSelic = createListSelic();
    Buy buy = createBuy();
    List<InstallmentsDefinition> installmentsDefinitionList = createInstallments();

    @InjectMocks
    private BuilderPurchase builderPurchase;

    @BeforeEach
    void setUp() {
    }

    @Test
    void buildPayment() {
        builderPurchase.buildPayment(buy);
    }

    @Test
    void buildProduct() {
        builderPurchase.buildProduct(buy);
    }

    @Test
    void buildInstallments() {
        builderPurchase.buildInstallments(buy, installmentsDefinitionList, listSelic);
    }

    @Test
    void builderBuyInDebit() {
        builderPurchase.builderBuyInDebit(buy, installmentsDefinitionList);
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