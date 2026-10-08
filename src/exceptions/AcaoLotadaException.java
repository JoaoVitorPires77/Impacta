package exceptions;

public class AcaoLotadaException extends RuntimeException {
    public AcaoLotadaException(String message) {
        super(message);
    }
    //Cria o erro de limite de açoes exedidos e gera uma mensagem sinalizando o erro
}