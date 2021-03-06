package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.utils;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.exceptions.DateUtilsException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

public final class DateUtils {

    public static final String FORMATO_BRASILEIRO = "dd/MM/yyyy";

    private DateUtils() {}

    public static String converterFormato(final LocalDate data, final String formato) {
        if (isNull(data) || isNull(formato)) {
            throw new NullPointerException("A data solicitada ou o formato se encontram nulos.");
        }

        try {
            return data.format(DateTimeFormatter.ofPattern(formato));
        } catch (Exception exception) {
            throw new DateUtilsException("Não foi possivel realizar a conversão da data.", exception);
        }
    }
}