package Tests;

import Tables.Status;
import org.junit.jupiter.api.Test;

class StatusTest {

    Status status = new Status();

    @Test
    void getStatus() {
        System.out.println(status.getStatus());
    }
}