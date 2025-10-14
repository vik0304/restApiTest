package ViktorVasileski.restApiTest.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Non è stato trovata la risorsa.");
    }
}
