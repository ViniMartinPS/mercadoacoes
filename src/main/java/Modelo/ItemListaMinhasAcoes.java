package Modelo;
/**
 * Created by ViniciusMartin on 20/12/2016.
 */
 
 import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemListaMinhasAcoes {

    private String nomeEquipe;
    private int quantidade;
    private double valorComprado;
    private double valorAtual;

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorComprado() {
        return valorComprado;
    }

    public void setValorComprado(double valorComprado) {
        this.valorComprado = valorComprado;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }
}
