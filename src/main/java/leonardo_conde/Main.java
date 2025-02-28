package leonardo_conde;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import leonardo_conde.models.FaturamentoDia;
import leonardo_conde.models.FaturamentoEstado;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static FaturamentoEstado[] faturamentoEstados = {
        new FaturamentoEstado("SP", 67836.43),
        new FaturamentoEstado("RJ ", 36678.66),
        new FaturamentoEstado("MG", 29229.88),
        new FaturamentoEstado("ES", 27165.48),
        new FaturamentoEstado("Outros", 19849.53)
    };

    public static void main(String[] args) {
        ex01();
        ex02(35);
        ex03("dados.json");
        ex04(faturamentoEstados);
        ex05("Paralelepipedo");
    }

    /**
     * 1) Observe o trecho de código abaixo: int INDICE = 13, SOMA = 0, K = 0;
     * Enquanto K < INDICE faça { K = K + 1; SOMA = SOMA + K; }
     * Imprimir(SOMA);
     * Ao final do processamento, qual será o valor da variável SOMA?
     */
    public static void ex01() {
        int INDICE = 13, SOMA = 0, K = 0;
        while(K < INDICE) {
            K = K + 1;
            SOMA = SOMA + K;
        }
        System.out.println(SOMA);
        System.out.printf("Resposta EX 01: O valor da final de SOMA é de: %d\n", SOMA);
    }

    /*
     * 2) Dado a sequência de Fibonacci, onde se inicia por 0 e 1 e o próximo valor sempre será a soma dos 2 valores
     * anteriores (exemplo: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34...), escreva um programa na linguagem que desejar onde,
     * informado um número, ele calcule a sequência de Fibonacci e retorne uma mensagem avisando se o número informado
     * pertence ou não a sequência.
     * IMPORTANTE: Esse número pode ser informado através de qualquer entrada de sua preferência ou pode ser
     * previamente definido no código;
     **/
    public static void ex02(int value) {
        int old = 0;
        int current = 1;

        if (value == 0) {
            current = 0;
        }
        while(value > current) {
            int newValue = old + current;
            old = current;
            current = newValue;
        }

        String isPresent = current == value ? "" : " não";
        System.out.printf("Resposta EX 02: O valor %d%s está presente na sequencia de Fibonacci\n", value, isPresent);
    }

    /**
     * 3) Dado um vetor que guarda o valor de faturamento diário de uma distribuidora, faça um programa, na linguagem
     * que desejar, que calcule e retorne:
     * • O menor valor de faturamento ocorrido em um dia do mês;
     * • O maior valor de faturamento ocorrido em um dia do mês;
     * • Número de dias no mês em que o valor de faturamento diário foi superior à média mensal.
     */
    public static void ex03(String fileName) {
        Gson gson = new Gson();
        List<FaturamentoDia> faturamentosAcimaMedia = new ArrayList<>();
        try {
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new RuntimeException("Arquivo não encontrado em resources!");
            }
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            Type listType = new TypeToken<List<FaturamentoDia>>() {}.getType();
            List<FaturamentoDia> faturamentoDiario = gson.fromJson(reader, listType);

            FaturamentoDia menorFaturamento = faturamentoDiario.get(0);
            FaturamentoDia maiorFaturamento = faturamentoDiario.get(0);
            double soma = menorFaturamento.getValor();

            for(int i = 1; i < faturamentoDiario.size() - 2; i++) {
                FaturamentoDia faturamento = faturamentoDiario.get(i);
                soma += faturamento.getValor();
                if(faturamento.getValor() > maiorFaturamento.getValor()) {
                    maiorFaturamento = faturamento;
                } else if(faturamento.getValor() < menorFaturamento.getValor()) {
                    menorFaturamento = faturamento;
                }
            }

            double media = soma / faturamentoDiario.size();

            System.out.printf("Resposta 3: O menor faturamento foi no dia %d e o maior foi no dia %d. Os dias com faturamento acima da média foram:\n",
                menorFaturamento.getDia(),
                maiorFaturamento.getDia()
            );

            for (FaturamentoDia faturamentoDia : faturamentoDiario) {
                if(faturamentoDia.getValor() > media) {
                    faturamentosAcimaMedia.add(faturamentoDia);
                    System.out.printf("\t - " + faturamentoDia.toString() + "\n");
                }
            }





        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 4) Dado o valor de faturamento mensal de uma distribuidora, detalhado por estado:
     * • SP – R$67.836,43
     * • RJ – R$36.678,66
     * • MG – R$29.229,88
     * • ES – R$27.165,48
     * • Outros – R$19.849,53
     * Escreva um programa na linguagem que desejar onde calcule o percentual de representação que
     * cada estado teve dentro do valor total mensal da distribuidora.
     */
    public static void ex04(FaturamentoEstado[] faturamentoEstaduais) {
        double total = 0;
        for(FaturamentoEstado faturamentoEstadual : faturamentoEstaduais) {
            total += faturamentoEstadual.getFaturamento();
        }

        System.out.println("Resposta 04:");
        for(FaturamentoEstado faturamentoEstadual : faturamentoEstaduais) {
            double porcentual = (faturamentoEstadual.getFaturamento() / total) * 100;
            faturamentoEstadual.setFaturamento(porcentual);
            System.out.printf("\t- %s representa %f do total\n", faturamentoEstadual.getNome(), porcentual);
        }
    }

    /**
     * 5) Escreva um programa que inverta os caracteres de um string.
     * IMPORTANTE:
     * a) Essa string pode ser informada através de qualquer entrada de sua preferência ou pode ser previamente definida no código;
     * b) Evite usar funções prontas, como, por exemplo, reverse;
     */
    public static void ex05(String value) {
        StringBuilder reverse = new StringBuilder();
        for(int i = value.length() - 1 ; i > 0; i--) {
            reverse.append(value.charAt(i));
        }
        System.out.printf("Resposta 05: O reverso de %s é %s\n",value, reverse);
    }
}