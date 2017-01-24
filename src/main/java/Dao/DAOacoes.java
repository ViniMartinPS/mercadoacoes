package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exceptions.ErrorSQLconection;
import Exceptions.MensagemErro;
import Modelo.EquipesID;
import Modelo.ItemListaAcoes;
import Modelo.ItemListaAcoesDisponiveis;
import Modelo.ItemListaMinhasAcoes;

public class DAOacoes {

	MensagemErro msg = new MensagemErro();
	
	public String updateSaldo(String nomeEquipe, double saldo) throws ErrorSQLconection{
	
		String sqlUpdate;
		Connection conn = null;
		PreparedStatement stm = null;
		
		int hasError = 0;
				
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlUpdate = "UPDATE equipe_saldo set saldo = ? where nomeEquipe = ?;";

			stm = conn.prepareStatement(sqlUpdate);
			stm.setDouble(1, saldo);
			stm.setString(2, nomeEquipe);

			stm.executeUpdate();
			}catch (SQLException e){
				hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return "Saldo Atualizado";
	}
		
	public double getSaldo(String nomeEquipe) throws ErrorSQLconection{
		
		int codigoRetorno = 0;
		
		String sqlSelect;
		Connection conn = null;
		Statement stm = null;
		double saldo = -100;
		int hasError= 0;
		
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlSelect = "SELECT saldo FROM equipe_saldo where nomeEquipe = \"" + nomeEquipe + "\";";
			stm = conn.createStatement();
			java.sql.ResultSet rs = stm.executeQuery(sqlSelect);
			while(rs.next()){
					saldo = rs.getDouble("saldo");
				}
		} catch (Exception e) {
			hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
					e.printStackTrace();
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return saldo;
	}

	public String insertValorAcao(String nomeEquipe, double novoValor, int contador) throws ErrorSQLconection{
		String sqlUpdate;
		Connection conn = null;
		PreparedStatement stm = null;
		int hasError = 0;
		
		int idEquipe = EquipesID.getIdEquipe(nomeEquipe);
				
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlUpdate = "INSERT INTO equipe_acoes values (?,?,?,?)";

			stm = conn.prepareStatement(sqlUpdate);
			stm.setInt(1, idEquipe);
			stm.setInt(3, contador);
			stm.setDouble(4, novoValor);
			stm.setString(2, nomeEquipe);

			stm.executeUpdate();
			}catch (SQLException e){
				hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return "Valor inserido";
	}

	public ArrayList<ItemListaAcoes> getListaAcoes() throws ErrorSQLconection{
		String sqlSelect;
		Connection conn = null;
		Statement stm = null;
		ArrayList<ItemListaAcoes> listaAcoes = new ArrayList<ItemListaAcoes>();
	
		String historico = "";
		double valorAcaoAtual = 0;
		int idEquipe =0 ;
		String nomeEquipe = "";
		int hasError = 0;
		
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlSelect = "SELECT * from equipe_acoes ORDER BY idEquipe,contador;";
			stm = conn.createStatement();
			java.sql.ResultSet rs = stm.executeQuery(sqlSelect);
			while(rs.next()){
				if (idEquipe == rs.getInt("idEquipe")){
					valorAcaoAtual = rs.getDouble("valorAcao");
					historico = historico + rs.getString("valorAcao") + " -> " ;
					nomeEquipe = rs.getString("nomeEquipe");
				} else {
					
					if (idEquipe!=0){
					ItemListaAcoes itemAcoes = new ItemListaAcoes();
					itemAcoes.setHistorico(historico);
					itemAcoes.setNomeEquipe(nomeEquipe);
					itemAcoes.setValorAcao(valorAcaoAtual);
					
					listaAcoes.add(itemAcoes);
					}
					valorAcaoAtual = rs.getDouble("valorAcao");
					historico = rs.getString("valorAcao") + " -> " ;
					nomeEquipe = rs.getString("nomeEquipe");
					idEquipe = rs.getInt("idEquipe");
					
				}
			}
			
			if (rs.first()){
			ItemListaAcoes itemAcoes = new ItemListaAcoes();
			itemAcoes.setHistorico(historico);
			itemAcoes.setNomeEquipe(nomeEquipe);
			itemAcoes.setValorAcao(valorAcaoAtual);
			
			listaAcoes.add(itemAcoes);
			}
		} catch (Exception e) {
			hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return listaAcoes;
	}
	
	public int getContador(String nomeEquipe) throws ErrorSQLconection{
		String sqlSelect;
		Connection conn = null;
		Statement stm = null;
		int hasError = 0;
	
		int contador = -1;
		
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlSelect = "SELECT MAX(contador) from equipe_acoes where nomeEquipe = \"" + nomeEquipe + "\";";
			stm = conn.createStatement();
			java.sql.ResultSet rs = stm.executeQuery(sqlSelect);
			while(rs.next()){
				contador = rs.getInt("MAX(contador)");
			}
			
		} catch (Exception e) {
			hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return contador;
	}
	
	public double getValorAcao(String nomeEquipe, int contador) throws ErrorSQLconection{
		String sqlSelect;
		Connection conn = null;
		Statement stm = null;
	
		double valorAcao = -1;
		int hasError = 0;
		
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlSelect = "SELECT valorAcao from equipe_acoes where nomeEquipe = \"" + nomeEquipe + "\" AND contador = " + contador + ";";
			stm = conn.createStatement();
			java.sql.ResultSet rs = stm.executeQuery(sqlSelect);
			while(rs.next()){
				valorAcao = rs.getDouble("valorAcao");
			}
			
		} catch (Exception e) {
			hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return valorAcao;
	}
	
	public ArrayList<ItemListaAcoesDisponiveis> getAcoesDisponiveis() throws ErrorSQLconection{
		String sqlSelect;
		Connection conn = null;
		Statement stm = null;
		ArrayList<ItemListaAcoesDisponiveis> listaAcoesDisponiveis = new ArrayList<ItemListaAcoesDisponiveis>();
		int hasError = 0;
		
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlSelect = "SELECT * from acoes_disponiveis";
			stm = conn.createStatement();
			java.sql.ResultSet rs = stm.executeQuery(sqlSelect);
			while(rs.next()){
				ItemListaAcoesDisponiveis itemAcoesDisponiveis = new ItemListaAcoesDisponiveis();
				itemAcoesDisponiveis.setNomeEquipe(rs.getString("nomeEquipe"));
				itemAcoesDisponiveis.setQuantidade(rs.getInt("quantidadeDisponivel"));
				// valor da acao atual deve ser pego de outra chamada
				
				listaAcoesDisponiveis.add(itemAcoesDisponiveis);
				}
		} catch (Exception e) {
			hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return listaAcoesDisponiveis;
	}
	
	public ItemListaAcoesDisponiveis getAcaoDisponivel(String nomeEquipe) throws ErrorSQLconection{
		String sqlSelect;
		Connection conn = null;
		Statement stm = null;				
		int hasError = 0;
		ItemListaAcoesDisponiveis itemAcoesDisponiveis = new ItemListaAcoesDisponiveis();
		
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlSelect = "SELECT * from acoes_disponiveis where nomeEquipe = \"" + nomeEquipe + "\";";
			stm = conn.createStatement();
			java.sql.ResultSet rs = stm.executeQuery(sqlSelect);
			while(rs.next()){
				itemAcoesDisponiveis.setNomeEquipe(rs.getString("nomeEquipe"));
				itemAcoesDisponiveis.setQuantidade(rs.getInt("quantidadeDisponivel"));
				// valor da acao atual deve ser pego de outra chamada
								}
		} catch (Exception e) {
			hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return itemAcoesDisponiveis;
	}

	public String updateAcoesDisponiveis(String nomeEquipe, int quantidadeDisponivel) throws ErrorSQLconection{
		String sqlUpdate;
		Connection conn = null;
		PreparedStatement stm = null;
				
		int hasError = 0;
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlUpdate = "UPDATE acoes_disponiveis set quantidadeDisponivel = ? where nomeEquipe = ?;";

			stm = conn.prepareStatement(sqlUpdate);
			stm.setInt(1, quantidadeDisponivel);
			stm.setString(2, nomeEquipe);

			stm.executeUpdate();
			}catch (SQLException e){
				hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return "Quantidade Atualizada";
	}

	public ArrayList<ItemListaMinhasAcoes> getMinhasAcoes(String nomeEquipe) throws ErrorSQLconection{
		String sqlSelect;
		Connection conn = null;
		Statement stm = null;
		ArrayList<ItemListaMinhasAcoes> listaMinhasAcoes = new ArrayList<ItemListaMinhasAcoes>();
		int hasError = 0;
		
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlSelect = "SELECT * FROM equipe_acoesCompradas where nomeEquipe = \"" + nomeEquipe + "\" AND quantidadeAcao > 0;";
			stm = conn.createStatement();
			java.sql.ResultSet rs = stm.executeQuery(sqlSelect);
			while(rs.next()){
					ItemListaMinhasAcoes itemMinhasAcoes = new ItemListaMinhasAcoes();
					itemMinhasAcoes.setNomeEquipe(rs.getString("nomeEquipeAcao"));
					itemMinhasAcoes.setQuantidade(rs.getInt("quantidadeAcao"));
					itemMinhasAcoes.setValorComprado(rs.getDouble("acaoValorPago"));
					// Valor atual da acao deve ser pego de outra chamada
					
					listaMinhasAcoes.add(itemMinhasAcoes);
				}
		} catch (Exception e) {
			hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError =2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return listaMinhasAcoes;
	}
	
	public int verificaAcaoExiste(String nomeEquipe, String nomeEquipeAcao, double valorAcao) throws ErrorSQLconection{
		String sqlSelect;
		Connection conn = null;
		Statement stm = null;
		int quantidade = -1;
		int hasError = 0;
		
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlSelect = "SELECT * FROM equipe_acoesCompradas where nomeEquipe = \"" + nomeEquipe + "\" AND nomeEquipeAcao = \"" + nomeEquipeAcao + "\" AND acaoValorPago = " + valorAcao + ";";
			stm = conn.createStatement();
			java.sql.ResultSet rs = stm.executeQuery(sqlSelect);
			while(rs.next()){
					quantidade = rs.getInt("quantidadeAcao");
				}
		} catch (Exception e) {
			hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return quantidade;
	}
	
	public String insereMinhaAcao(String nomeEquipe, String nomeEquipeAcao, double valorAcao, int quantidadeAcao) throws ErrorSQLconection{
		String sqlUpdate;
		Connection conn = null;
		PreparedStatement stm = null;
		
		int idEquipe = EquipesID.getIdEquipe(nomeEquipe);
		int idEquipeAcao = EquipesID.getIdEquipe(nomeEquipeAcao);
				
		int hasError = 0;
		
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlUpdate = "INSERT INTO equipe_acoesCompradas values (?,?,?,?,?,?);";

			stm = conn.prepareStatement(sqlUpdate);
			stm.setInt(1, idEquipe);
			stm.setString(2, nomeEquipe);
			stm.setInt(3, idEquipeAcao);
			stm.setString(4, nomeEquipeAcao);
			stm.setInt(5, quantidadeAcao);
			stm.setDouble(6, valorAcao);

			stm.executeUpdate();
			}catch (SQLException e){
				hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return "Acao inserido";
	}

	public String updateMinhaAcao(String nomeEquipe, String nomeEquipeAcao, double valorAcao, int quantidadeAcao) throws ErrorSQLconection{
		String sqlUpdate;
		Connection conn = null;
		PreparedStatement stm = null;
		
		int hasError = 0;
		
		int idEquipe = EquipesID.getIdEquipe(nomeEquipe);
		int idEquipeAcao = EquipesID.getIdEquipe(nomeEquipeAcao);
				
		try {
			ConexaoBD bd = new ConexaoBD();
			conn = bd.obtemConexao();
			sqlUpdate = "UPDATE equipe_acoesCompradas set quantidadeAcao = ? where idEquipe = ? AND idEquipeAcao = ? AND acaoValorPago = ?;";

			stm = conn.prepareStatement(sqlUpdate);
			stm.setInt(2, idEquipe);
			stm.setInt(3, idEquipeAcao);
			stm.setInt(1, quantidadeAcao);
			stm.setDouble(4, valorAcao);

			stm.executeUpdate();
			}catch (SQLException e){

				hasError = 1;
		}finally{
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					hasError = 2;
				}
			}
			// fecha a conexao
			//
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					hasError = 3;
				}
			}
			if (hasError!=0){
				throw new ErrorSQLconection(msg.getMessage(hasError));
			}
		}
		return "Acao atualizada";
	}
	
}
