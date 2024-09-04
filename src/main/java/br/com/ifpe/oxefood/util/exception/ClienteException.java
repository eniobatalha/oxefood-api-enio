package br.com.ifpe.oxefood.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ClienteException extends RuntimeException {

    public static final String MSG_NOME_DUPLICADO = "Já existe um cliente cadastrado com este nome.";

    public ClienteException (String msg){
        super(String.format(msg));
    }
    
}
