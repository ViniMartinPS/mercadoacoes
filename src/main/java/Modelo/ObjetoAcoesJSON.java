package Modelo;
import java.util.ArrayList;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObjetoAcoesJSON {

	private double saldo;
	private ArrayList<ItemListaAcoes> itemListaAcoes;
	private ArrayList<ItemListaAcoesDisponiveis> itemListaAcoesDisponiveis;
	private ArrayList<ItemListaMinhasAcoes> itemListaMinhasAcoes;
	
	public double getSaldo(){
		return saldo;
	}
	public void setSaldo(double saldo){
		this.saldo = saldo;
	}
	public ArrayList<ItemListaAcoes> getItemListaAcoes() {
		return itemListaAcoes;
	}
	public void setItemListaAcoes(ArrayList<ItemListaAcoes> itemListaAcoes) {
		this.itemListaAcoes = itemListaAcoes;
	}
	public ArrayList<ItemListaAcoesDisponiveis> getItemListaAcoesDisponiveis() {
		return itemListaAcoesDisponiveis;
	}
	public void setItemListaAcoesDisponiveis(ArrayList<ItemListaAcoesDisponiveis> itemListaAcoesDisponiveis) {
		this.itemListaAcoesDisponiveis = itemListaAcoesDisponiveis;
	}
	public ArrayList<ItemListaMinhasAcoes> getItemListaMinhasAcoes() {
		return itemListaMinhasAcoes;
	}
	public void setItemListaMinhasAcoes(ArrayList<ItemListaMinhasAcoes> itemListaMinhasAcoes) {
		this.itemListaMinhasAcoes = itemListaMinhasAcoes;
	}
	
	
	
}
