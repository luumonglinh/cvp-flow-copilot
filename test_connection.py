import os
from dotenv import load_dotenv
from ibm_watsonx_ai import Credentials
from ibm_watsonx_ai.foundation_models import ModelInference

load_dotenv()
model = ModelInference(
    model_id="ibm/granite-4-h-small",
    credentials=Credentials(url=os.environ["WATSONX_URL"], api_key=os.environ["WATSONX_APIKEY"]),
    project_id=os.environ["WATSONX_PROJECT_ID"],
    params={"max_tokens": 200, "temperature": 0},
)

messages = [
    {"role": "system", "content": "You are a concise assistant for IVR developers."},
    {"role": "user", "content": "In one sentence, what is an IVR system?"},
]
response = model.chat(messages=messages)
print(response["choices"][0]["message"]["content"])