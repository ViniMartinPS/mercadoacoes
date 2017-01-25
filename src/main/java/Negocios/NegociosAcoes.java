package Negocios;

import Dao.DAOacoes;
import Exceptions.ErrorSQLconection;
import Modelo.ItemListaAcoesDisponiveis;
import Modelo.ObjetoAcoesJSON;

public class NegociosAcoes {

	DAOacoes dao = new DAOacoes();
	
	public ObjetoAcoesJSON getObjetoJsonAcoes(String nomeEquipe) throws ErrorSQLconection{
		
		ObjetoAcoesJSON objeto = new ObjetoAcoesJSON();
		 
		objeto.setSaldo(dao.getSaldo(nomeEquipe));
		
		objeto.setItemListaAcoes(dao.getListaAcoes());
		
		objeto.setItemListaAcoesDisponiveis(dao.getAcoesDisponiveis()); // Lembrar de no android pegar o valor da acao atual
		
		objeto.setItemListaMinhasAcoes(dao.getMinhasAcoes(nomeEquipe)); // Lembrar de no android pegar o valor da acao atual
		
		return objeto;
		
	}
	
	public String comprarAcao(String nomeEquipe, String nomeEquipeAcao, int quantidadeAcao, double valorPago) throws ErrorSQLconection{
		
		double saldo = dao.getSaldo(nomeEquipe);
		int contador = dao.getContador(nomeEquipeAcao);
		double valorAcao = dao.getValorAcao(nomeEquipeAcao,contador);
		ItemListaAcoesDisponiveis itemAcaoDisponivel = dao.getAcaoDisponivel(nomeEquipeAcao);
		
		if (valorPago != valorAcao) return "Valor da acao foi atualizado, atualize seus valores e refaca a transacao!";
		else if (itemAcaoDisponivel.getQuantidade() < quantidadeAcao) return "Quantidade disponivel e menor que a quantidade comprada, atualize seus valores e refaca a transacao";
		else if (saldo < (quantidadeAcao * valorAcao )) return "Saldo insuficiente para realizar a transacao";
		else {
			// realizando transacao
			dao.updateSaldo(nomeEquipe, saldo - (quantidadeAcao * valorAcao));
			dao.updateAcoesDisponiveis(nomeEquipeAcao,itemAcaoDisponivel.getQuantidade() - quantidadeAcao);
			
			int quantidadeMinhasAcoes = dao.verificaAcaoExiste(nomeEquipe, nomeEquipeAcao, valorAcao);
			if (quantidadeMinhasAcoes > -1){
				dao.updateMinhaAcao(nomeEquipe, nomeEquipeAcao, valorAcao,quantidadeMinhasAcoes + quantidadeAcao);
			} else {
				dao.insereMinhaAcao(nomeEquipe, nomeEquipeAcao, valorAcao, quantidadeAcao);
			}
			}
			return "Transacao Concluida";
		
		
	}
	
	public String venderAcao(String nomeEquipe, String nomeEquipeAcao, int quantidadeAcao, double valorPago) throws ErrorSQLconection{
		
		double saldo = dao.getSaldo(nomeEquipe);
		int contador = dao.getContador(nomeEquipeAcao);
		double valorAcao = dao.getValorAcao(nomeEquipeAcao,contador);
		ItemListaAcoesDisponiveis itemAcaoDisponivel = dao.getAcaoDisponivel(nomeEquipeAcao);
		
		dao.updateSaldo(nomeEquipe, saldo + (quantidadeAcao * valorAcao));
		dao.updateAcoesDisponiveis(nomeEquipeAcao,itemAcaoDisponivel.getQuantidade() + quantidadeAcao);
		
		int quantidadeMinhasAcoes = dao.verificaAcaoExiste(nomeEquipe, nomeEquipeAcao, valorPago);
		dao.updateMinhaAcao(nomeEquipe, nomeEquipeAcao, valorPago,quantidadeMinhasAcoes - quantidadeAcao);
		
		return "Transacao Concluida";
	}
	
}
