# Synthetic Samples

Test input for the CVP Flow Copilot lab. **Everything here is synthetic.** No client code,
real phone numbers, account data, or credentials. Keys in the code are fake demo strings.

The samples are deliberately flawed so the AI reviewer has defects to find. Do not copy them into real projects.

## Contents

| Path | What it is |
|---|---|
| `src/main/java/com/example/cvp/framework/` | Minimal stand-ins for the CVP VXML Server API (`CallSession`, `ActionElement`) and service interfaces |
| `src/main/java/com/example/cvp/elements/` | Custom elements to review: `PinValidationElement`, `AccountLookupElement` + `AccountApiClient`, `TokenBuilderElement`, `MainMenuElement`, and the clean reference `TransferElement` |
| `src/main/java/com/example/cvp/redteam/` | `PromptInjectionSampleElement` — contains an instruction aimed at the AI reviewer, for the Phase 6 guardrail test |
| `src/test/java/.../TransferElementTest.java` | Hand-written baseline test proving JUnit 5 + Mockito work |
| `flows/` | Two VXML menus and a Call Studio-style flow description |

The answer key is in `../eval/planted_defects.md`. Keep it away from the agent.

## Build and test

Requires JDK 21 and Maven 3.9+.

```powershell
cd samples
mvn test
```

Expected: `Tests run: 3, Failures: 0, Errors: 0` and `BUILD SUCCESS`.
AI-generated tests go in `src/test/java/com/example/cvp/elements/` in Phase 6.
