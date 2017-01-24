package Modelo;
/**
 * Created by ViniciusMartin on 21/12/2016.
 */
 
 import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemListaAcoesDisponiveis {

    private String nomeEquipe;
    private int quantidade;
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

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }
}
