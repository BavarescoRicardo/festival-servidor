package api.nxmu.festival.excecoes;

public class ValidacaoException extends Exception{

	private static final long serialVersionUID = 5511775166130138100L;


	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
	
	public ValidacaoException(String mensagem, Exception ex) {
		super(mensagem, ex);
	}	

}
