package Modelo;
/**
 * Created by ViniciusMartin on 19/12/2016.
 */

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemListaAcoes {

    private String nomeEquipe;

    private double valorAcao;

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    private String historico;

    public double getValorAcao() {
        return valorAcao;
    }

    public void setValorAcao(double valorAcao) {
        this.valorAcao = valorAcao;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }



}
