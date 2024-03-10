import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Figure fig = null;
        ThreeDim prism = null;
        loop:
        while(true) {
            if(fig == null) {
                System.out.println("Wybierz opcje:\n1 - Wybierz figurę\n0 - Zamknij");
            }
            else{
                System.out.println("Wybierz opcje:\n1 - Wybierz figurę\n2 - Wypisz informacje o figurze\n3 - Wypisz pole figury\n4 - Wypisz obwód figury\n0 - Zamknij");
            }
            switch (scanner.next())
            {
                case "0":
                {
                    break loop;
                }
                case "1":
                {
                    fig = SelectFigure();
                    break;
                }
                case "2":
                {
                    if(fig != null)
                        ((Printing)fig).print();
                    else
                        System.out.println("Najpierw należy ustawić figurę");

                    break;
                }
                case "3":
                {
                    if(fig != null)
                        System.out.printf("Pole powierzchni to %f\n", fig.calculateArea());
                    else
                        System.out.println("Najpierw należy ustawić figurę");

                    break;
                }
                case "4":
                {
                    if(fig != null)
                        System.out.printf("Obwód to %f\n", fig.calculatePerimeter());
                    else
                        System.out.println("Najpierw należy ustawić figurę");

                    break;
                }
                default:
                {
                    System.out.println("Nieznana komenda!");
                    break;
                }
            }
        }
    }

    public static Figure SelectFigure() {
        System.out.println("Wybierz opcje:\n1 - Trójkąt\n2 - Kwadrat\n3 - Okrąg\n0 - Anuluj");
        switch (scanner.next()) {
            case "1":
            {
                System.out.println("Podaj długość boku 1:");
                float a = scanner.nextFloat();
                System.out.println("Podaj długość boku 2:");
                float b = scanner.nextFloat();
                System.out.println("Podaj długość boku 3:");
                float c = scanner.nextFloat();
                try {
                    return new Triangle(a, b, c);
                }
                catch (Exception ex){
                    System.out.println("Nieprawidłowe parametry!");
                }
                break;
            }
            case "2":
            {
                System.out.println("Podaj długość boku:");
                float a = scanner.nextFloat();
                try {
                    return new Square(a);
                }
                catch (Exception ex){
                    System.out.println("Nieprawidłowe parametry!");
                }
                break;
            }
            case "3":
            {
                System.out.println("Podaj promień:");
                float r = scanner.nextFloat();
                try {
                    return new Circle(r);
                }
                catch (Exception ex){
                    System.out.println("Nieprawidłowe parametry!");
                }
                break;
            }
            default:
            {
                break;
            }
        }
        return null;
    }
}