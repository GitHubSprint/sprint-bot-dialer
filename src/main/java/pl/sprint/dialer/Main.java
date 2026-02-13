package pl.sprint.dialer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements DialerInterface {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Main m = new Main();

        m.sendCallStatus("123456789", 1L, "", "1", "test", 1, "sprint");
    }

    @Override
    public String sendCallStatus(String phone, Long recordId, String parameters, String customerId, String campaign, Integer staus, String symbol) {
        log.info("plugin sendCallStatus phone: {} recordId: {} parameters: {} customerId: {} campaign: {} staus: {} symbol: {}", phone, recordId, parameters, customerId, campaign, staus, symbol);
        return "OK";
    }
}