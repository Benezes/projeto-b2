package br.com.gabriel.projetob2.service;

import br.com.gabriel.projetob2.builder.BuilderPurchase;
import br.com.gabriel.projetob2.infra.ApiInfra;
import br.com.gabriel.projetob2.model.Selic;
import br.com.gabriel.projetob2.request.Buy;
import br.com.gabriel.projetob2.response.InstallmentsDefinition;
import br.com.gabriel.projetob2.service.exception.InstallmentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BuyService {

    @Autowired
    private ApiInfra apiInfra;
    @Autowired
    private BuilderPurchase builderBuy;

    public List<InstallmentsDefinition> createPurchase(Buy buy) {

        validations(buy);

        builderBuy.buildPayment(buy);
        builderBuy.buildProduct(buy);

        List<InstallmentsDefinition> installmentsDefinitionList = new ArrayList<>();

        if (!buy.getProduct().getPrice().equals(buy.getPayment().getEntry())) {
            List<Selic> selic = apiInfra.getApiSelic();
            Collections.reverse(selic);
            builderBuy.buildInstallments(buy, installmentsDefinitionList, selic);
        } else {
            builderBuy.builderBuyInDebit(buy, installmentsDefinitionList);
        }

        return installmentsDefinitionList;
    }


    private static void validations(Buy buy) {
        if (buy.getPayment().getInstallments() <= 0)
            throw new InstallmentsException("Parcelamento deve ser maior que zero");

        if (buy.getPayment().getEntry() == 0)
            throw new InstallmentsException("O preço da entrada deve ser maior que zero");

        if(buy.getProduct().getPrice() < buy.getPayment().getEntry())
            throw new InstallmentsException("O preço da entrada deve ser menor ou igual ao preço do produto");

    }

}
