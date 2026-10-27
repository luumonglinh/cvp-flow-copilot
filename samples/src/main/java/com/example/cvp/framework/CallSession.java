package com.example.cvp.framework;

/**
 * Minimal stand-in for the CVP VXML Server session API.
 * Lets the synthetic samples compile and be unit tested without Cisco libraries.
 */
public interface CallSession {

    /** Unique identifier for the current call. */
    String getCallId();

    /** Returns a session variable, or null if it is not set. */
    Object getSessionData(String name);

    /** Sets a session variable. */
    void setSessionData(String name, Object value);

    /** Removes a session variable. */
    void removeSessionData(String name);
}
