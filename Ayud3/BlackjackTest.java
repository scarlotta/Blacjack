import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackTest {
    List<String> mano = new ArrayList<>();
    List<String> mazo = Blackjack.crearBaraja();

    @Test
    void bajarse() {


    }

    @Test
    void verificarGanador() {
        assertEquals("Jugador", Blackjack.verificarGanador(20, 1));


    }

    @Test
    void pedirCartaPrueba() {
        Blackjack.pedirCartaPrueba(mazo, mano);
        assertEquals(1, mano.size());


    }

    @Test
    void manoNula() {
        Logger logger = Logger.getLogger("Blackjack.class");

        assertThrows(NullPointerException.class,
                () -> Blackjack.esBlackjack(0),
                "La mano es nula.");

        logger.info("""
                [Error de tipo de objeto]
                La mano es nula.
                """);
    }
    @Test

    void testValorMano() {

        Logger logger = Logger.getLogger("Blackjack.class");
        Object[] mazo = {-1, null, true};

        assertThrows(ClassCastException.class,
                () -> Blackjack.contarPuntaje(mano),
                "Formato de objetos incorrectos.");

        logger.info("""
                [Error de tipo de objeto]
                El objeto no corresponde al esperado en el valor de entrada.
                """);


    }
}