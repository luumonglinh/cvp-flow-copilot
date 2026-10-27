package com.example.cvp.elements;

import com.example.cvp.framework.ActionElement;
import com.example.cvp.framework.CallSession;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Builds a signed token for the downstream payment service and stores it
 * in session data as "authToken". Reads "customerId" from session data.
 */
public class TokenBuilderElement extends ActionElement {

    private static final String SIGNING_KEY = "demo-signing-secret-do-not-use-0042";

    @Override
    public String getElementName() {
        return "TokenBuilder";
    }

    @Override
    public String doAction(CallSession session) {
        String customerId = (String) session.getSessionData("customerId");
        String token = buildToken(customerId);
        if (token == null) {
            return "error";
        }
        session.setSessionData("authToken", token);
        return "success";
    }

    String buildToken(String customerId) {
        try {
            long issuedAt = System.currentTimeMillis() / 1000;
            String header = encode("{\"alg\":\"HS256\",\"typ\":\"JWT\"}");
            String payload = encode("{\"sub\":\"" + customerId + "\",\"iat\":" + issuedAt + "}");
            String unsigned = header + "." + payload;

            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(SIGNING_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            String signature = Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(mac.doFinal(unsigned.getBytes(StandardCharsets.UTF_8)));

            String token = unsigned + "." + signature;
            System.out.println("Built token for " + customerId + ": " + token);
            return token;
        } catch (Exception e) {
            System.out.println("Token build failed: " + e.getMessage());
            return null;
        }
    }

    private static String encode(String json) {
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(json.getBytes(StandardCharsets.UTF_8));
    }
}
