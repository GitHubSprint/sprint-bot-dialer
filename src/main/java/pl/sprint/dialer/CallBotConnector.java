package pl.sprint.dialer;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;

public class CallBotConnector {
    private final static String OK = "OK";
    private String endpoint;
    private final int timeout;
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();
}
