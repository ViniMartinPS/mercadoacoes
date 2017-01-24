package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	// Define o Metodo de Conexao quando a classe � chamada
			static {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
			
			public Connection obtemConexao() throws SQLException{
	System.setProperty("javax.net.ssl.keyStore","rds-combined-ca-bundle.pem");
	System.setProperty("javax.net.ssl.trustStore","truststore.jks");
	System.setProperty("javax.net.ssl.trustStorePassword", "juriteam");
	return DriverManager.getConnection(
			"jdbc:mysql://sp6xl8zoyvbumaa2.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/h50iycg4e53jt7xi", 	//banco de dados
			"xyi1h245niulusvv", 									//usuario ViniciusMartin << trabalho
			"ixiy4hyhch15fsrj");									//senha
}

	/*public Connection obtemConexao() throws SQLException{
				return DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/mercado_acoes_teste", 	//banco de dados
						"root", 									//usuario ViniciusMartin << trabalho
						"vinimartin");									//senha
			}		*/
			
}


