package leonardo_conde.models;

public class FaturamentoDia {
    private int dia;
    private double valor;

    public FaturamentoDia(int dia, double valor) {
        this.dia = dia;
        this.valor = valor;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String toString() {
        return "Dia: " + dia + ", Valor: " + valor;
    }
}
