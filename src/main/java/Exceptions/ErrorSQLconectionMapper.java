package Exceptions;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ErrorSQLconectionMapper implements ExceptionMapper<ErrorSQLconection> {

		@Override
		public Response toResponse(ErrorSQLconection exc) {
			ErrorMessage errorMessage = new ErrorMessage(exc.getMessage(), 490, "_Sem conexao com o banco, avise um administrador");
			return Response.status(Status.NOT_FOUND)
					.entity(errorMessage)
					.build();
		}
	
}

/* Mensagem vem assim por ex
<errorMessage>
<documentation>http://google.com</documentation>
<errorCode>404</errorCode>
<errorMessage>Monstro 12 nao existe!</errorMessage>
</errorMessage>
 */
