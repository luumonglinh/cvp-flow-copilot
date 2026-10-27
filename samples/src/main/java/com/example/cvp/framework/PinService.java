package com.example.cvp.framework;

/** Looks up the PIN on file for a customer. */
public interface PinService {

    /** Returns the PIN on file for the customer, or null if none is found. */
    String getPinOnFile(String customerId);
}
