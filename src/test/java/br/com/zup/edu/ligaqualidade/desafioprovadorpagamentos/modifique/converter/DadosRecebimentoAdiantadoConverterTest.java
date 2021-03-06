package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.converter;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.converter.DadosRecebimentoAdiantadoConverter;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.constants.DesafioConstants.VIRGULA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DadosRecebimentoAdiantadoConverterTest {

    @Test
    @DisplayName("Deverá retornar DadosRecebimentoAdiantado - Operação Realizada com sucesso")
    void from_DadosRecebimentoAdiantado_QuandoSucesso() {
        final String toStringValue = "1,0.01";
        final String[] toStringSplitValue = toStringValue.split(VIRGULA);

        assertEquals(2, toStringSplitValue.length);

        DadosRecebimentoAdiantado retorno = DadosRecebimentoAdiantadoConverter.from(toStringValue);

        final int idTransacao = Integer.parseInt(toStringSplitValue[0]);
        final BigDecimal taxa = BigDecimal.valueOf(Double.parseDouble(toStringSplitValue[1]));

        assertNotNull(retorno);
        assertEquals(idTransacao, retorno.idTransacao);
        assertEquals(taxa, retorno.taxa);
    }

}