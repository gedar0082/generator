package Tests;

import Tables.Worker;
import org.junit.jupiter.api.Test;

class WorkerTest {

    Worker worker = new Worker();

    @Test
    void getFirstName() {
        System.out.println(worker.getFirstName());
    }

    @Test
    void getLastName() {
        System.out.println(worker.getLastName());
    }

    @Test
    void getJob() {
        System.out.println(worker.getJob());
    }

    @Test
    void getSalary() {
        System.out.println(worker.getSalary(worker.getJob()));
    }
}