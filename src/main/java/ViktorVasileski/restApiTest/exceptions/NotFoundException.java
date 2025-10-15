package ViktorVasileski.restApiTest.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Non è stato trovata la risorsa.");
    }

    public NotFoundException(UUID id) {
        super("Non è stato trovata la risorsa.");
    }
}
