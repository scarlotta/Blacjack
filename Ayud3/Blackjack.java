import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {

        menu();
    }

    public static int tryCatchInt(){
        Scanner teclado = new Scanner(System.in);
        int v = 0;
        try {
            v = teclado.nextInt();
        }catch (Exception e){
            teclado.next();
            System.out.println("Introduzca una opcion valida");
        }
        return v;
    }


    public static void menu() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bienvenido a BlackJack");
        int r = 0;
       do {
           System.out.println("Desea jugar? \n [1] si \n [2] no");
           r = tryCatchInt();

       }while (r != 1 && r != 2);


        if (r == 1) {
            jugar();
        } else if (r == 2) {
            System.out.println("Se ha seleccionado no jugar");
        } else {
            System.out.println("La opcion marcada no esta dentro de los parametros");
        }

    }

    public static void jugar() {
        List<String> barajaActual;

        barajaActual = crearBaraja();
        barajar(barajaActual);
        crearMano(barajaActual);
    }

    public static List crearBaraja() {

        String[] pintas = new String[]{"Corazon", "Diamante", "Trebol", "Pica"};
        String[] numeroCartas = new String[]{"As", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "J", "Q", "K"};
        List<String> mazo = new ArrayList<>();
        String carta;

        for (int i = 0; i < pintas.length; i++) {

            for (int j = 0; j < numeroCartas.length; j++) {
                carta = pintas[i] + "_" + numeroCartas[j];
                mazo.add(carta);

            }

        }
        return mazo;
    }

    public static void crearMano(List<String> mazo) {
        List<String> manoJugador = new ArrayList<>();
        List<String> manoDealer = new ArrayList<>();
        repartir(mazo, manoJugador);
        repartir(mazo, manoDealer);
        System.out.println("Mano del Jugador");
        mostrarMano(manoJugador);
        System.out.println();
        System.out.println("Mano Dealer");
        mostrarMano(manoDealer);
        System.out.println();
        pedirCarta(mazo, manoJugador);
        repartirDealer(manoDealer, mazo);

        verificarGanador(contarPuntaje(manoJugador), contarPuntaje(manoDealer));
    }

    //mezclase
    public static void barajar(List<String> mazo) {
        Collections.shuffle(mazo);

    }

    //entregar cartas
    public static void repartir(List<String> mazo, List<String> mano) {
        String carta = mazo.get(0);
        mazo.remove(0);
        mano.add(carta);


    }

    //solicitar otra carta
    public static void pedirCarta(List<String> mazo, List<String> mano) {
        Scanner teclado = new Scanner(System.in);
        int elec, puntaje = 0;

        do {
            System.out.println("Quiere pedir otra carta? Actualmente tiene:");
            mostrarMano(mano);

           do {
               System.out.println("[1] Pedir carta \n[2] Bajarse");
               elec = tryCatchInt();
           }while (elec != 1 && elec != 2);


            if (elec == 1) {

                repartir(mazo, mano);
                puntaje = contarPuntaje(mano);

            }

            if (puntaje > 21 || elec == 2) {
                System.out.println("Jugador: ");
                bajarse(mano);
                System.out.println();
                break;

            }


        } while (elec == 1);


    }

    //poner cartas en la mesa(no se puede pedir mas cartas) dealer saca si o si
    public static void bajarse(List<String> mano) {
        System.out.println("La mano final fue de: ");
        mostrarMano(mano);

    }

    //implementar
    public static boolean esBlackjack(int puntaje) {
        boolean blackJack = false;
        if (puntaje == 21) {
            System.out.println("Es BlackJack");
            blackJack = true;

        }

        return blackJack;
    }

    //comparar entre jugador y dealer
    public static String verificarGanador(int puntajeJugador, int puntajeDealer) {
        String ganador = null;


        if (puntajeJugador > puntajeDealer && puntajeJugador <= 21) {
            ganador = "Jugador";

            System.out.println("El ganador es el jugador con un puntaje de " + puntajeJugador + " Sobre el puntaje de " + puntajeDealer + " del dealer");


        } else if (puntajeDealer > puntajeJugador && puntajeDealer <= 21) {
            ganador = "Dealer";
            System.out.println("El ganador es el Dealer con un puntaje de " + puntajeDealer + " Sobre el puntaje de " + puntajeJugador + " del Jugador");

        } else if (puntajeJugador > 21) {
            ganador = "Dealer";

            System.out.println("El ganador es el Dealer ya que Jugador sobrepaso los 21 con un puntaje de " + puntajeJugador);


        } else if (puntajeDealer > 21) {
            ganador = "Jugador";
            System.out.println("El ganador es el Jugador con un puntaje de " + puntajeJugador + " Sobre el puntaje de " + puntajeDealer + " del Dealer que sobrepaso los 21");

        }

        return ganador;

    }


    public static void repartirDealer(List<String> manoDealer, List<String> mazo) {
        String cartaDealer;
        int puntajeDealer = 0;


        do {

            cartaDealer = mazo.get(0);
            mazo.remove(0);
            manoDealer.add(cartaDealer);
            puntajeDealer = contarPuntaje(manoDealer);

            if (puntajeDealer >= 17) {
                System.out.println("Dealer");
                bajarse(manoDealer);
                System.out.println();
                break;

            }


        } while (puntajeDealer <= 16);


    }


    public static int contarPuntaje(List<String> mano) {
        int puntaje = 0;


        for (int i = 0; i < mano.size(); i++) {


            String carta = mano.get(i);
            String[] cartaActual = carta.split("_");

            switch (cartaActual[1]) {

                case "As":
                    puntaje += 1;
                    break;
                case "Dos":
                    puntaje += 2;
                    break;
                case "Tres":
                    puntaje += 3;
                    break;
                case "Cuatro":
                    puntaje += 4;
                    break;
                case "Cinco":
                    puntaje += 5;
                    break;
                case "Seis":
                    puntaje += 6;
                    break;
                case "Siete":
                    puntaje += 7;
                    break;
                case "Ocho":
                    puntaje += 8;
                    break;
                case "Nueve":
                    puntaje += 9;
                    break;
                case "Diez":
                    puntaje += 10;
                    break;
                case "J":
                    puntaje += 10;
                    break;
                case "Q":
                    puntaje += 10;
                    break;
                case "K":
                    puntaje += 10;
                    break;


            }
        }

        return puntaje;
    }

    public static void mostrarMano(List<String> mano) {
        for (String c : mano) {
            System.out.println(c);

        }


    }
    public static void pedirCartaPrueba(List<String> mazo, List<String> mano) {
        int puntaje = 0;


        repartir(mazo, mano);

        puntaje=contarPuntaje(mano);

        if (puntaje > 21) {
            System.out.println("Jugador: ");
            bajarse(mano);
            System.out.println();


        }

    }

}