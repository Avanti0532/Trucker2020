package org.avanti.exception;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String s) {
        super(s);
    }
}
