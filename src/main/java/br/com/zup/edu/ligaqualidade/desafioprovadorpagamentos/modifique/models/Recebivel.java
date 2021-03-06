package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.models;

import java.math.BigDecimal;

import static br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.constants.DesafioConstants.VIRGULA;

public class Recebivel {
    private final String status;
    private final String dataTransacao;
    private final BigDecimal valorOriginal;
    private final BigDecimal valorReceber;

    public Recebivel(
            final String status,
            final String dataTransacao,
            final BigDecimal valorOriginal,
            final BigDecimal valorReceber
    ) {
        this.status = status;
        this.dataTransacao = dataTransacao;
        this.valorOriginal = valorOriginal;
        this.valorReceber = valorReceber;
    }

    public String getStatus() {
        return status;
    }

    public String getDataTransacao() {
        return dataTransacao;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public BigDecimal getValorReceber() {
        return valorReceber;
    }

    @Override
    public String toString() {

        return new StringBuilder()
                .append("{")
                .append(this.status)
                .append(VIRGULA)
                .append(this.valorOriginal)
                .append(VIRGULA)
                .append(this.valorReceber)
                .append(VIRGULA)
                .append(this.dataTransacao)
                .append("}")
                .toString();
    }
}