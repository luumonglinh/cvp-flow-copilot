# Account Balance Flow (Call Studio design description)

Synthetic flow description for the CVP Flow Copilot lab. It describes a fictional
Call Studio application, element by element, in the order the call moves through it.

## Elements

| # | Element | Type | Purpose | Exit states and where they go |
|---|---------|------|---------|-------------------------------|
| 1 | Start | Start of call | Entry point | (always) → 2 |
| 2 | Welcome | Audio | Plays "Thank you for calling." | done → 3 |
| 3 | CollectAccount | Form (DTMF, 10 digits) | Collects the account number into `accountNumber` | done → 4; noinput → 3; nomatch → 3 |
| 4 | CollectPin | Form (DTMF, 4 digits) | Collects the PIN into `enteredPin` | done → 5; noinput → 4; nomatch → 4 |
| 5 | PinValidation | Custom Java element | Checks the PIN (see `PinValidationElement.java`) | valid → 6; retry → 4 |
| 6 | AccountLookup | Custom Java element | Gets the balance (see `AccountLookupElement.java`) | done → 7 |
| 7 | PlayBalance | Audio (TTS) | Plays "Your balance is {balance} dollars." | done → 8 |
| 8 | Goodbye | Audio | Plays "Thank you, goodbye." | done → 9 |
| 9 | Hang Up | Hang up | Ends the call | — |

## Notes

- Session variables used: `accountNumber`, `enteredPin`, `customerId`, `authenticated`, `balance`.
- Elements 3 and 4 reuse the same prompt on every attempt.
- There is no transfer element in this application.
