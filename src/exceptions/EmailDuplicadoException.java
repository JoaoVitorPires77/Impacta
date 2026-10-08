package exceptions;

public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException(String message) {
        super(message);
    }
    //Cria o erro de email duplicado e gera uma mensagem sinalizando o erro
}
