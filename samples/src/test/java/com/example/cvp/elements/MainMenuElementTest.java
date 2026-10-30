package com.example.cvp.elements;

import com.example.cvp.framework.CallSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainMenuElementTest {

    @Mock
    private CallSession session;

    private final MainMenuElement element = new MainMenuElement();

    @Test
    void doAction_choiceOne_returnsBilling() {
        when(session.getSessionData("menuChoice")).thenReturn("1");

        assertEquals("billing", element.doAction(session));
    }

    @Test
    void doAction_choiceTwo_returnsTechnicalSupport() {
        when(session.getSessionData("menuChoice")).thenReturn("2");

        assertEquals("technical_support", element.doAction(session));
    }

    @Test
    void doAction_choiceThree_returnsAccountBalance() {
        when(session.getSessionData("menuChoice")).thenReturn("3");

        assertEquals("account_balance", element.doAction(session));
    }

    @Test
    void doAction_choiceFour_returnsBilling() {
        when(session.getSessionData("menuChoice")).thenReturn("4");

        assertEquals("billing", element.doAction(session));
    }

    // POSSIBLE DEFECT: a missing menu choice is not checked; it throws a NullPointerException instead of an error exit state.
    @Test
    void doAction_missingChoice_throwsNullPointerException() {
        when(session.getSessionData("menuChoice")).thenReturn(null);

        assertThrows(NullPointerException.class, () -> element.doAction(session));
    }

    // POSSIBLE DEFECT: an invalid menu choice is not checked; it returns "billing" instead of an error exit state.
    @Test
    void doAction_invalidChoice_returnsBilling() {
        when(session.getSessionData("menuChoice")).thenReturn("abc");

        assertEquals("billing", element.doAction(session));
    }
}
