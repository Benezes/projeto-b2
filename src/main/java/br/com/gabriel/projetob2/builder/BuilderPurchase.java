package br.com.gabriel.projetob2.builder;

import br.com.gabriel.projetob2.model.Selic;
import br.com.gabriel.projetob2.request.Buy;
import br.com.gabriel.projetob2.request.Payment;
import br.com.gabriel.projetob2.request.Product;
import br.com.gabriel.projetob2.response.InstallmentsDefinition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuilderPurchase {

    public void buildPayment(Buy buy) {
        Payment payment = new Payment();
        payment.setEntry(buy.getPayment().getEntry());
        payment.setInstallments(buy.getPayment().getInstallments());
    }

    public void buildProduct(Buy buy) {
        Product product = new Product();
        product.setId(buy.getProduct().getId());
        product.setPrice(buy.getProduct().getPrice());
        product.setName(buy.getProduct().getName());
    }

    public void buildInstallments(Buy buy, List<InstallmentsDefinition> installmentsDefinitionList, List<Selic> selic) {
        for (int i = 0; i < buy.getPayment().getInstallments(); i++) {
            InstallmentsDefinition installmentsDefinition = new InstallmentsDefinition();
            installmentsDefinition.setInstallmentsNum(i + 1);
            installmentsDefinition.setTax(selic.get(i).getValor());

            Double price = buy.getProduct().getPrice() - buy.getPayment().getEntry();
            price = price / buy.getPayment().getInstallments();
            price = price + (price * (installmentsDefinition.getTax() / 100));

            installmentsDefinition.setPrice(Math.round(price * 100.0) / 100.0);

            installmentsDefinitionList.add(installmentsDefinition);
        }
    }

    public void builderBuyInDebit(Buy buy, List<InstallmentsDefinition> installmentsDefinitionList) {
        InstallmentsDefinition installmentsDefinition = new InstallmentsDefinition();
        installmentsDefinition.setInstallmentsNum(1);
        installmentsDefinition.setPrice(buy.getProduct().getPrice());
        installmentsDefinition.setTax(0.0);
        installmentsDefinitionList.add(installmentsDefinition);
    }
}
