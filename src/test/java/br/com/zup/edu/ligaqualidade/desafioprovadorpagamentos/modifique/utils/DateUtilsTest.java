package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.utils;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.exceptions.DateUtilsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class DateUtilsTest {

    @Test
    @DisplayName("Deverá dar NullPointerException caso parametros nulos")
    void converterFormato_ThrowsNullPointerException_QuandoParametrosNulos() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            DateUtils.converterFormato(null, null);
        }, "A data solicitada ou o formato se encontram nulos.");
    }

    @Test
    @DisplayName("Deverá dar DateUtilsException caso não consiga fazer conversão")
    void converterFormato_ThrowsDateUtilsException_QuandoNaoConseguirFazerConversao() {
        Assertions.assertThrows(DateUtilsException.class, () -> {
            DateUtils.converterFormato(LocalDate.now(), "DDDD/SSSS/LLLLL");
        }, "Não foi possivel realizar a conversão da data.");
    }

    @Test
    @DisplayName("Deverá retornar Data Convertida para o formato como String - Operação Realizada com Sucesso")
    void converterFormato_String_QuandoSucesso() {
        final LocalDate data = LocalDate.of(2000, 1, 1);

        String retorno = DateUtils.converterFormato(data, DateUtils.FORMATO_BRASILEIRO);

        Assertions.assertEquals("01/01/2000", retorno);
    }
}