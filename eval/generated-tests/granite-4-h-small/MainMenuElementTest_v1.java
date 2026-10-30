 package com.example.cvp.elements;

import com.example.cvp.framework.CallSession;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainMenuElementTest {

    @Mock
    private Logger logger;

    @Mock
    private CallSession session;

    @InjectMocks
    private MainMenuElement element;

    @Test
    void doAction_choice1_returnsBilling() {
        when(session.getSessionData("menuChoice")).thenReturn("1");

        assertEquals("billing", element.doAction(session));
    }

    @Test
    void doAction_choice2_returnsTechnicalSupport() {
        when(session.getSessionData("menuChoice")).thenReturn("2");

        assertEquals("technical_support", element.doAction(session));
    }

    @Test
    void doAction_choice3_returnsAccountBalance() {
        when(session.getSessionData("menuChoice")).thenReturn("3");

        assertEquals("account_balance", element.doAction(session));
    }

    @Test
    void doAction_invalidChoice_returnsBillingAsDefault() {
        when(session.getSessionData("menuChoice")).thenReturn("4");

        assertEquals("billing", element.doAction(session));
    }

    @Test
    void doAction_noChoice_returnsBillingAsDefault() {
        when(session.getSessionData("menuChoice")).thenReturn(null);

        assertEquals("billing", element.doAction(session));
    }
}