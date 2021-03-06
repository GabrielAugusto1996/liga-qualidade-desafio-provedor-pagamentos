package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.converter;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.constants.DesafioConstants.VIRGULA;

public final class DadosTransacaoConverter {

    private DadosTransacaoConverter() {
    }

    public static DadosTransacao from(final String toString) {
        final String[] split = toString.split(VIRGULA);

        final BigDecimal valor = BigDecimal.valueOf(Double.parseDouble(split[0]));
        final MetodoPagamento metodoPagamento = MetodoPagamento.valueOf(split[1]);
        final String numero = split[2];
        final String nome = split[3];
        final LocalDate validade = LocalDate.parse(split[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        final int cvv = Integer.parseInt(split[5]);
        final int id = Integer.parseInt(split[6]);

        return new DadosTransacao(
                valor,
                metodoPagamento,
                numero,
                nome,
                validade,
                cvv,
                id
        );
    }
}