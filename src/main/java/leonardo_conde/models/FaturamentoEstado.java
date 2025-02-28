package leonardo_conde.models;

public class FaturamentoEstado {
    private String nome;
    private double faturamento;

    private double percentualTotal = 0;

    public FaturamentoEstado(String nome, double faturamento) {
        this.nome = nome;
        this.faturamento = faturamento;
    }

    public String getNome() {
        return nome;
    }

    public double getFaturamento() {
        return faturamento;
    }

    public double getPercentualTotal(double percentualTotal) {
        return this.percentualTotal;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFaturamento(double faturamento) {
        this.faturamento = faturamento;
    }

    public void setPercentualTotal(double percentualTotal) {
        this.percentualTotal = percentualTotal;
    }
}
