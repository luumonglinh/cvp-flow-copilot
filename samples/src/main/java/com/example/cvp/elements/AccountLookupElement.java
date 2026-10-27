package com.example.cvp.elements;

import com.example.cvp.framework.ActionElement;
import com.example.cvp.framework.CallSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Looks up the caller's account balance and stores it in session data as "balance".
 * Reads "accountNumber" from session data.
 */
public class AccountLookupElement extends ActionElement {

    private static final Logger logger = LogManager.getLogger(AccountLookupElement.class);

    private final AccountApiClient apiClient;

    public AccountLookupElement(AccountApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public String getElementName() {
        return "AccountLookup";
    }

    @Override
    public String doAction(CallSession session) {
        String accountNumber = (String) session.getSessionData("accountNumber");
        logger.debug("Looking up balance for account " + accountNumber);

        String balance = "0.00";
        try {
            balance = apiClient.getBalance(accountNumber);
        } catch (Exception e) {
        }

        session.setSessionData("balance", balance);
        return "done";
    }
}
