package pl.sprint.dialer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements DialerInterface {
    private final CallBotConnector callBotConnector;
    private final String endpoint;
    private final int timeout;

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Main m = new Main();
        m.sendCallStatus("123456789", 1L, "", "1", "test", 1, "sprint");
    }

    public Main() {
         Config.configure("solid.properties");
        endpoint = Config.getValue("endpoint", "http://10.22.13.6:8080");
        timeout = Integer.parseInt(Config.getValue("timeout", "8000"));
        callBotConnector = new CallBotConnector(endpoint, timeout);
    }

    @Override
    public String sendCallStatus(String phone, Long recordId, String parameters, String customerId, String campaign, Integer staus, String symbol) {

        StatusPhone status = StatusPhone.fromInt(staus);

        log.info("plugin sendCallStatus phone: {} recordId: {} parameters: {} customerId: {} campaign: {} status: {} symbol: {}",
                phone, recordId, parameters, customerId, campaign, status, symbol);

        if(status == StatusPhone.ANSWER) {
            log.info("Call status is ANSWER, do nothing");
            return "ANSWER";
        }
        return callBotConnector.phoneNoAnswer(phone, recordId);
    }
}