package Tests;

import Tables.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClientTest {
    Client client = new Client();

    @Test
    void getFirstName() {
        for (int i = 0; i < 150; i++){
            System.out.println(client.getFirstName());
        }

    }

    @Test
    void getLastName() {
        for (int i = 0; i < 150; i++){
            System.out.println(client.getLastName());
        }
    }

    @Test
    void getTelephoneNumber() {
        for(int i = 0; i < 150; i++){
            String s = client.getTelephoneNumber();
            System.out.println(s);
            Assertions.assertEquals(11, s.length());
        }
    }

    @Test
    void doubleRND(){
        for(int i = 0; i < 100; i++){
            System.out.println(rndDouble(20, 60));
        }
    }

    double rndDouble(double min, double max){
        return  min + Math.random()*(max-min);

    }
}