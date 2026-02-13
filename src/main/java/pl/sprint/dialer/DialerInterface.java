package pl.sprint.dialer;

public interface DialerInterface {
    String sendCallStatus(String phone, Long recordId, String parameters, String customerId, String campaign, Integer staus, String symbol);
}
