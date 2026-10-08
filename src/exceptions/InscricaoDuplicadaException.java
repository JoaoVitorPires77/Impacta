package exceptions;

public class InscricaoDuplicadaException extends RuntimeException {
    public InscricaoDuplicadaException(String message) {
        super(message);
    }
    //    //Cria o erro de Inscriçao duplicada e gera uma mensagem sinalizando o erro
}
