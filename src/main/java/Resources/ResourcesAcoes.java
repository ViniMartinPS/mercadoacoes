package Resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Exceptions.ErrorSQLconection;
import Modelo.ObjetoAcoesJSON;
import Negocios.NegociosAcoes;

@Path("acoes")
public class ResourcesAcoes {

	NegociosAcoes negocios = new NegociosAcoes();
	
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/{nomeEquipe}")
public ObjetoAcoesJSON getAcoes(@PathParam("nomeEquipe") String nomeEquipe) throws ErrorSQLconection{
	return negocios.getObjetoJsonAcoes(nomeEquipe);
}

@GET
@Produces(MediaType.TEXT_PLAIN)
@Path("/comprar/{nomeEquipe}/{nomeEquipeAcao}/{quantidadeAcao}/{valorPago}")
public String comprarAcao(@PathParam("nomeEquipe") String nomeEquipe,
									@PathParam("nomeEquipeAcao") String nomeEquipeAcao,
									@PathParam("quantidadeAcao") int quantidadeAcao,
									@PathParam("valorPago") double valorPago) throws ErrorSQLconection{
	return negocios.comprarAcao(nomeEquipe, nomeEquipeAcao, quantidadeAcao, valorPago);
}

@GET
@Produces(MediaType.TEXT_PLAIN)
@Path("/vender/{nomeEquipe}/{nomeEquipeAcao}/{quantidadeAcao}/{valorPago}")
public String venderAcao(@PathParam("nomeEquipe") String nomeEquipe,
									@PathParam("nomeEquipeAcao") String nomeEquipeAcao,
									@PathParam("quantidadeAcao") int quantidadeAcao,
									@PathParam("valorPago") double valorPago) throws ErrorSQLconection{
	return negocios.venderAcao(nomeEquipe, nomeEquipeAcao, quantidadeAcao, valorPago);
}

	
}
