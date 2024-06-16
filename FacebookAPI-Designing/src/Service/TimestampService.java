package Service;

public class TimestampService {
    private static int timestamp = 0;
    public static int getTimestamp() {
        return timestamp++;
    }
}
