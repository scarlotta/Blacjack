import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackTest {
    List<String> mano = new ArrayList<>();
    List<String> mazo = Blackjack.crearBaraja();

    @Test
    void bajarse() {

        
    }

    @Test
    void verificarGanador() {
        assertEquals("Jugador",Blackjack.verificarGanador(20,1) );


    }

    @Test
    void pedirCartaPrueba() {
        Blackjack.pedirCartaPrueba(mazo, mano);
        assertEquals(1, mano.size());


    }

}