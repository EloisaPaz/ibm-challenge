package br.com.ibm.challenge.exceptions;

public class AtmException extends RuntimeException{

	private static final long serialVersionUID = 1348771109171435607L;

    public AtmException(String message){
        super(message);
    }
}
