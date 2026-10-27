package com.example.cvp.elements;

import com.example.cvp.framework.AgentRouter;
import com.example.cvp.framework.CallSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Hand-written baseline test for the reference element.
 * Confirms that Maven, JUnit 5, and Mockito are wired up before AI-generated tests are added.
 */
@ExtendWith(MockitoExtension.class)
class TransferElementTest {

    @Mock
    private AgentRouter agentRouter;

    @Mock
    private CallSession session;

    @InjectMocks
    private TransferElement element;

    @Test
    void doAction_validReason_returnsTransferAndStoresQueue() {
        when(session.getCallId()).thenReturn("call-001");
        when(session.getSessionData(TransferElement.SESSION_CALL_REASON)).thenReturn("billing");
        when(agentRouter.findQueue("billing")).thenReturn("BILLING_Q");

        String exit = element.doAction(session);

        assertEquals(TransferElement.EXIT_TRANSFER, exit);
        verify(session).setSessionData(TransferElement.SESSION_TRANSFER_QUEUE, "BILLING_Q");
    }

    @Test
    void doAction_missingReason_usesGeneralQueue() {
        when(session.getCallId()).thenReturn("call-002");
        when(session.getSessionData(TransferElement.SESSION_CALL_REASON)).thenReturn(null);

        String exit = element.doAction(session);

        assertEquals(TransferElement.EXIT_DEFAULT_QUEUE, exit);
        verify(session).setSessionData(TransferElement.SESSION_TRANSFER_QUEUE, TransferElement.GENERAL_QUEUE);
        verify(agentRouter, never()).findQueue(any());
    }

    @Test
    void doAction_routerThrows_returnsError() {
        when(session.getCallId()).thenReturn("call-003");
        when(session.getSessionData(TransferElement.SESSION_CALL_REASON)).thenReturn("billing");
        when(agentRouter.findQueue("billing")).thenThrow(new IllegalStateException("no queue"));

        String exit = element.doAction(session);

        assertEquals(TransferElement.EXIT_ERROR, exit);
        verify(session, never()).setSessionData(any(), any());
    }
}
