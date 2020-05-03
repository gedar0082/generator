package Tests;

import Tables.Good;
import org.junit.jupiter.api.Test;

class GoodTest {

    Good good = new Good();

    @Test
    void getName() {

        System.out.println(good.getName());
    }

    @Test
    void getPrice(){
        System.out.println(good.getPrice());
    }

    @Test
    void getQuantity(){
        System.out.println(good.getQuantity());
    }

}