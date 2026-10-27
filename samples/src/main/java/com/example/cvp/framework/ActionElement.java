package com.example.cvp.framework;

/**
 * Minimal stand-in for a CVP custom action element.
 * The VXML Server calls {@link #doAction(CallSession)} and follows the returned exit state.
 */
public abstract class ActionElement {

    /** Display name of the element, used in logs. */
    public abstract String getElementName();

    /**
     * Runs the element's logic for one call.
     *
     * @param session the current call session
     * @return the exit state the call flow should follow
     */
    public abstract String doAction(CallSession session);
}
