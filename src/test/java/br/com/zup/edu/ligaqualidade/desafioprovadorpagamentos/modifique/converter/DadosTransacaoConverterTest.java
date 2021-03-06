package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.converter;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.constants.DesafioConstants.VIRGULA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DadosTransacaoConverterTest {

    @Test
    @DisplayName("Deverá retornar DadosTransacao - Operação Realizada com sucesso")
    void from_DadosTransacao_QuandoSucesso() {
        final String toStringValue = "100,CREDITO,764387534,Nome do cartao,06/03/2023,457,1";
        final String[] toStringSplitValue = toStringValue.split(VIRGULA);

        assertEquals(7, toStringSplitValue.length);

        DadosTransacao retorno = DadosTransacaoConverter.from(toStringValue);

        final BigDecimal valor = BigDecimal.valueOf(Double.parseDouble(toStringSplitValue[0]));
        final MetodoPagamento metodoPagamento = MetodoPagamento.valueOf(toStringSplitValue[1]);
        final String numero = toStringSplitValue[2];
        final String nome = toStringSplitValue[3];
        final LocalDate validade = LocalDate.parse(toStringSplitValue[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        final int cvv = Integer.parseInt(toStringSplitValue[5]);
        final int id = Integer.parseInt(toStringSplitValue[6]);

        assertNotNull(retorno);
        assertEquals(valor, retorno.valor);
        assertEquals(metodoPagamento, retorno.metodo);
        assertEquals(numero, retorno.numero);
        assertEquals(nome, retorno.nome);
        assertEquals(validade, retorno.validade);
        assertEquals(cvv, retorno.cvv);
        assertEquals(id, retorno.id);
    }
}