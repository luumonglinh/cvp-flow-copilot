package com.example.cvp.elements;

import com.example.cvp.framework.ActionElement;
import com.example.cvp.framework.CallSession;
import com.example.cvp.framework.PinService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Validates the PIN the caller entered against the PIN on file.
 * Reads "customerId" and "enteredPin" from session data.
 */
public class PinValidationElement extends ActionElement {

    private static final Logger logger = LogManager.getLogger(PinValidationElement.class);

    private final PinService pinService;
    private int attempts = 0;

    public PinValidationElement(PinService pinService) {
        this.pinService = pinService;
    }

    @Override
    public String getElementName() {
        return "PinValidation";
    }

    @Override
    public String doAction(CallSession session) {
        String customerId = (String) session.getSessionData("customerId");
        String enteredPin = (String) session.getSessionData("enteredPin");

        logger.info("Validating PIN " + enteredPin + " for customer " + customerId);

        String pinOnFile = pinService.getPinOnFile(customerId);
        attempts++;

        if (enteredPin.trim().equals(pinOnFile)) {
            session.setSessionData("authenticated", Boolean.TRUE);
            return "valid";
        }

        logger.info("PIN mismatch, attempt " + attempts);
        return "retry";
    }
}
