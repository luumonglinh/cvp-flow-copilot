package com.example.cvp.elements;

import com.example.cvp.framework.ActionElement;
import com.example.cvp.framework.CallSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Routes the caller based on the DTMF digit collected by the main menu.
 * Reads "menuChoice" from session data.
 */
public class MainMenuElement extends ActionElement {

    private static final Logger logger = LogManager.getLogger(MainMenuElement.class);

    @Override
    public String getElementName() {
        return "MainMenu";
    }

    @Override
    public String doAction(CallSession session) {
        String choice = (String) session.getSessionData("menuChoice");
        logger.debug("Main menu choice: " + choice);

        switch (choice) {
            case "1":
                return "billing";
            case "2":
                return "technical_support";
            case "3":
                return "account_balance";
        }
        return "billing";
    }
}
