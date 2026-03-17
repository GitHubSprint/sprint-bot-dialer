package pl.sprint.dialer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sprint.dialer.model.ContentType;
import pl.sprint.dialer.model.ErrorResponse;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class CallBotConnector {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private final static String OK = "OK";
    private final String endpoint;
    private final int timeout;
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public CallBotConnector(String endpoint, int timeout) {
        this.endpoint = endpoint;
        this.timeout = timeout;
    }

    public String phoneNoAnswer(String phoneNumber, Long recordId) {
        try {
            return executeRequest(
                    phoneNumber,
                    recordId
            );
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    private <T> T executeRequest(String phoneNumber, Long recordId) {
        log.info("executeRequest method: {} phoneNumber: {} recordId: {}", "phoneNoAnswer", phoneNumber, recordId);

        String fullUrl = endpoint + "/callbotconnector/" + "phoneNoAnswer" + "?phoneNumber="
                + URLEncoder.encode(phoneNumber, StandardCharsets.UTF_8);

        try {
            var builder = HttpRequest.newBuilder()
                    .uri(URI.create(fullUrl))
                    .timeout(Duration.ofMillis(timeout))
                    .header("Accept", ContentType.JSON.getValue())
                    .header("Content-Type", ContentType.URL.getValue());

            builder.POST(HttpRequest.BodyPublishers.noBody());

            HttpResponse<String> response = httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                if (response.body() == null || response.body().isBlank()) {
                    return null;
                }
                return (T) OK;
            } else {
                log.info("executeRequest phoneNoAnswer HTTP error: {}",  response.body());
                ErrorResponse error = mapper.readValue(response.body(), ErrorResponse.class);
                throw new RuntimeException("ERR " + response.statusCode() + " " + error.message());
            }
        } catch (Exception ex) {
            log.error("executeRequest phoneNoAnswer exception", ex);
            throw new RuntimeException(ex.getMessage().startsWith("ERR ") ? ex.getMessage() : "ERR " + ex.getMessage());
        }
    }
}
