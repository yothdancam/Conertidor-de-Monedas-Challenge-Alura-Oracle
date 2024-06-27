
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ConversorMoneda conversor= new ConversorMoneda();
        String mensaje = """
              ***********************************************************
              Sea bienvenido/a  al Conversor de Moneda =]
             
              1) Dólar =>> Peso argentino
              2) Peso argentino =>> Dólar
              3) Dólar =>> Real brasileño
              4) Real brasileño =>> Dólar
              5) Dólar =>> Peso colombiano
              6) Peso colombiano =>> Dólar
              7) Salir
              Elija una opción valida:
              ***********************************************************
              """;

        while (true) {
            System.out.println(mensaje);
            try {
                int opcion = scanner.nextInt();
                if (opcion == 7) {
                    System.out.println("Gracias por usar el conversor de moneda.");
                    break;
                }
                double resultado = 0;
                double cantidad = 0;
                switch (opcion) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        System.out.print("Ingrese el valor que desea convertir: ");
                        cantidad = scanner.nextDouble();
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción válida.");
                        continue;
                }

                switch (opcion) {
                    case 1:
                        resultado = conversor.convertirMoneda("USD", "ARS", cantidad);
                        break;
                    case 2:
                        resultado = conversor.convertirMoneda("ARS", "USD", cantidad);
                        break;
                    case 3:
                        resultado = conversor.convertirMoneda("USD", "BRL", cantidad);
                        break;
                    case 4:
                        resultado = conversor.convertirMoneda("BRL", "USD", cantidad);
                        break;
                    case 5:
                        resultado = conversor.convertirMoneda("USD", "COP", cantidad);
                        break;
                    case 6:
                        resultado = conversor.convertirMoneda("COP", "USD", cantidad);
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción válida.");
                        continue;
                }

                System.out.println("El valor " + cantidad + " corresponde al valor final de =>> " + resultado);
            }catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        scanner.close();
    }
}