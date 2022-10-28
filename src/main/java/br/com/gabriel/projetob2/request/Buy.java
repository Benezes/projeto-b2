package br.com.gabriel.projetob2.request;

import lombok.Data;

@Data
public class Buy {

    private Product product;
    private Payment payment;
}
