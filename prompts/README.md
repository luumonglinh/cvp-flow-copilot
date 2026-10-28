# Prompt Lab Prompts (Phase 3)

Four prompts for the CVP Flow Copilot. Build each one in **watsonx Prompt Lab** in the
**cvp-flow-copilot** project, test it, then save it as a **prompt template**.

| File | Prompt | Mode | Template variable |
|---|---|---|---|
| `01_flow_explainer.md` | Flow Explainer | Structured | `{source_code}` |
| `02_standards_reviewer.md` | Standards Reviewer | Structured (few-shot) | `{source_code}` |
| `03_junit_generator.md` | JUnit Generator | Structured (few-shot) | `{source_code}` |
| `04_doc_writer.md` | Doc Writer | Structured | `{source_code}` |

## Before you start

1. Open Prompt Lab **from the cvp-flow-copilot project** (Projects → cvp-flow-copilot → New asset → Chat and build prompts).
2. Turn on **AI guardrails** and set: HAP 0.5 (input and output), PII On (both), Granite Guardian 0.5 → Save.
3. Select model **granite-4-h-small**.

## How to enter a prompt in Structured mode

1. Click **Structured**.
2. Paste the **Instruction** section into the *Instruction* box.
3. Add each **Example** as one row: *Input* column and *Output* column.
4. Click the **Model parameters** icon (sliders, top right) and set the values listed in the file.
5. Paste a test input into the *Try* box and click **Generate**.
6. To save as a reusable template: replace the test input with `{source_code}`, open the **Prompt variables** panel (`{#}` icon), give the variable a default value, then **Save work → Save as → Prompt template**.

## Giving the model line numbers

The reviewer must cite real line numbers, so give it numbered code. In PowerShell, from the repo root:

```powershell
$f = "samples\src\main\java\com\example\cvp\elements\PinValidationElement.java"
$i = 0; Get-Content $f | ForEach-Object { $i++; "{0,3}| {1}" -f $i, $_ } | Set-Clipboard
```

Then paste (Ctrl+V) into Prompt Lab. Every line now starts with its number, e.g. ` 18|     private int attempts = 0;`.

## Important: keep the evaluation fair

- The few-shot **examples in these files are invented** and do not appear in `samples/`.
  Never paste a sample file *and its expected answer* into the examples. That would leak the
  answer key and inflate the Phase 6 scores.
- Never paste `eval/planted_defects.md` into Prompt Lab.

Record every run in `prompt-log.md`.
