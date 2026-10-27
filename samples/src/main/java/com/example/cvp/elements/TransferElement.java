package com.example.cvp.elements;

import com.example.cvp.framework.ActionElement;
import com.example.cvp.framework.AgentRouter;
import com.example.cvp.framework.CallSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Selects the agent queue for a live transfer and stores it in session data
 * as "transferQueue". Reads "callReason" from session data.
 *
 * <p>Exit states:
 * <ul>
 *   <li>{@value #EXIT_TRANSFER} - a queue was found</li>
 *   <li>{@value #EXIT_DEFAULT_QUEUE} - no reason given; the general queue is used</li>
 *   <li>{@value #EXIT_ERROR} - routing failed; play the fallback message</li>
 * </ul>
 */
public class TransferElement extends ActionElement {

    public static final String EXIT_TRANSFER = "transfer";
    public static final String EXIT_DEFAULT_QUEUE = "default_queue";
    public static final String EXIT_ERROR = "error";

    static final String SESSION_CALL_REASON = "callReason";
    static final String SESSION_TRANSFER_QUEUE = "transferQueue";
    static final String GENERAL_QUEUE = "GENERAL";

    private static final Logger logger = LogManager.getLogger(TransferElement.class);

    private final AgentRouter agentRouter;

    public TransferElement(AgentRouter agentRouter) {
        this.agentRouter = agentRouter;
    }

    @Override
    public String getElementName() {
        return "Transfer";
    }

    @Override
    public String doAction(CallSession session) {
        String callId = session.getCallId();
        logger.debug("[{}] {} start", callId, getElementName());

        Object reasonValue = session.getSessionData(SESSION_CALL_REASON);
        if (!(reasonValue instanceof String reason) || reason.isBlank()) {
            logger.warn("[{}] {} no call reason, using general queue", callId, getElementName());
            session.setSessionData(SESSION_TRANSFER_QUEUE, GENERAL_QUEUE);
            return EXIT_DEFAULT_QUEUE;
        }

        try {
            String queue = agentRouter.findQueue(reason);
            session.setSessionData(SESSION_TRANSFER_QUEUE, queue);
            logger.debug("[{}] {} end, queue={}", callId, getElementName(), queue);
            return EXIT_TRANSFER;
        } catch (RuntimeException e) {
            logger.error("[{}] {} routing failed for reason={}", callId, getElementName(), reason, e);
            return EXIT_ERROR;
        }
    }
}
