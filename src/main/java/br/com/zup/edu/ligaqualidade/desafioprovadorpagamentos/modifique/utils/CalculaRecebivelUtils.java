package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.utils;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

import java.math.BigDecimal;

public final class CalculaRecebivelUtils {

    private static final double PORCENTAGEM_DEBITO = 0.03;
    private static final double PORCENTAGEM_CREDITO = 0.05;

    private CalculaRecebivelUtils(){}

    public static BigDecimal calcular(final DadosTransacao dadosTransacao) {
        return MetodoPagamento.DEBITO.equals(dadosTransacao.metodo)
                ? BigDecimal.valueOf(dadosTransacao.valor.doubleValue() - (dadosTransacao.valor.doubleValue() * PORCENTAGEM_DEBITO))
                : BigDecimal.valueOf(dadosTransacao.valor.doubleValue() - (dadosTransacao.valor.doubleValue() * PORCENTAGEM_CREDITO));
    }
}