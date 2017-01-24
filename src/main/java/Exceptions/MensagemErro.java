package Exceptions;

public class MensagemErro {

	public String getMessage(int errorCode){
		switch(errorCode){
		case 1: return "Erro de conexao com o Banco, por favor avise um administrador";
		case 2: return "Erro de conexao com o Banco, por favor avise um administrador";
		case 3: return "Erro de conexao com o Banco, por favor avise um administrador";
		default : return "ERRO, avise um administrador";
		}
	}
	
}
