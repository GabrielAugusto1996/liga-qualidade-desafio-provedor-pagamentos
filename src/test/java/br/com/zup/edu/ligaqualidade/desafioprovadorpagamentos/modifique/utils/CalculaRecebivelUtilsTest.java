package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.utils;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class CalculaRecebivelUtilsTest {

    @Test
    @DisplayName("Deverá calcular o valor recebivel - Função Debito")
    void calcular_BigDecimal_QuandoForDebito() {
        final DadosTransacao dadosTransacao = new DadosTransacao(valueOf(50.0), MetodoPagamento.DEBITO, null, null, null, 0, 0);

        final BigDecimal retorno = CalculaRecebivelUtils.calcular(dadosTransacao);

        assertEquals(valueOf(48.5), retorno);
    }

    @Test
    @DisplayName("Deverá calcular o valor recebivel - Função Crédito")
    void calcular_BigDecimal_QuandoForCredito() {
        final DadosTransacao dadosTransacao = new DadosTransacao(valueOf(50.0), MetodoPagamento.CREDITO, null, null, null, 0, 0);

        final BigDecimal retorno = CalculaRecebivelUtils.calcular(dadosTransacao);

        assertEquals(valueOf(47.5), retorno);
    }
}