package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.converter;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.constants.DesafioConstants.VIRGULA;

public final class DadosRecebimentoAdiantadoConverter {

    private DadosRecebimentoAdiantadoConverter() {
    }

    public static DadosRecebimentoAdiantado from(final String toString) {
        final String[] split = toString.split(VIRGULA);

        final int idTransacao = Integer.parseInt(split[0]);
        final BigDecimal taxa = BigDecimal.valueOf(Double.parseDouble(split[1]));

        return new DadosRecebimentoAdiantado(
                idTransacao,
                taxa
        );
    }
}