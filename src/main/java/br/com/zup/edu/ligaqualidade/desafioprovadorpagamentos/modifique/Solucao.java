package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.converter.DadosTransacaoConverter;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.models.Recebivel;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.utils.CalculaRecebivelUtils;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.utils.DateUtils;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.utils.DateUtils.FORMATO_BRASILEIRO;
import static java.util.stream.Collectors.toList;

public class Solucao {

    private static final Map<MetodoPagamento, BiFunction<Solucao, DadosTransacao, Recebivel>> mapFunction = new HashMap<>();

    public Solucao() {
        mapFunction.put(MetodoPagamento.DEBITO, Solucao::obterRecebivelDebito);
        mapFunction.put(MetodoPagamento.CREDITO, Solucao::obterRecebivelCredito);
    }

    /**
     * @param infoTransacoes    dados das transações. A String está formatada da seguinte maneira:
     *                          <b>"valor,metodoPagamento,numeroCartao,nomeCartao,validade,cvv,idTransacao"</b>
     *                          <ol>
     *                          <li> Valor é um decimal</li>
     *                          <li> O método de pagamento é 'DEBITO' ou 'CREDITO' </li>
     *                          <li> Validade é uma data no formato dd/MM/yyyy. </li>
     *                          </ol>
     * @param infoAdiantamentos informacao da transacao que pode ser recebida adiantada. A String está formatada da seguinte maneira:
     *                          <b>"idTransacao,taxa"</b>
     *                          <ol>
     *                          <li> Taxa é um decimal </li>
     *                          </ol>
     * @return Uma lista de array de string com as informações na seguinte ordem:
     * [status,valorOriginal,valorASerRecebidoDeFato,dataEsperadoRecebimento].
     * <ol>
     *  <li>O status pode ser 'pago' ou 'aguardando_pagamento'</li>
     *  <li>O valor original e o a ser recebido de fato devem vir no formato decimal. Ex: 50.45</li>
     *  <li>dataEsperadoRecebimento deve ser formatada como dd/MM/yyyy. Confira a classe {@link DateTimeFormatter}</li>
     * </ol>
     * <p>
     * É esperado que o retorno respeite a ordem de recebimento
     */
    public static List<String[]> executa(List<String> infoTransacoes, List<String> infoAdiantamentos) {
        final List<DadosTransacao> dadosTransacaos = infoTransacoes.stream()
                .map(DadosTransacaoConverter::from)
                .collect(toList());

        final List<Recebivel> recebivels = new ArrayList<>();
        dadosTransacaos
                .forEach(dadosTransacao -> recebivels.add(mapFunction.get(dadosTransacao.metodo).apply(new Solucao(), dadosTransacao)));

        return List.of(new String[][]{
                recebivels.stream().map(Recebivel::toString).toArray(String[]::new)
        });
    }

    private Recebivel obterRecebivelDebito(final DadosTransacao dadosTransacao) {
        final String status = "pago";
        final String dataTransacao = DateUtils.converterFormato(LocalDate.now(), FORMATO_BRASILEIRO);
        final BigDecimal valorOriginal = dadosTransacao.valor;
        final BigDecimal valorReceber = CalculaRecebivelUtils.calcular(dadosTransacao);

        return new Recebivel(status, dataTransacao, valorOriginal, valorReceber);
    }

    private Recebivel obterRecebivelCredito(final DadosTransacao dadosTransacao) {
        final String status = "aguardando_liberacao_fundos";
        final String dataTransacao = DateUtils.converterFormato(LocalDate.now().plusDays(30L), FORMATO_BRASILEIRO);
        final BigDecimal valorOriginal = dadosTransacao.valor;
        final BigDecimal valorReceber = CalculaRecebivelUtils.calcular(dadosTransacao);

        return new Recebivel(status, dataTransacao, valorOriginal, valorReceber);
    }
}
