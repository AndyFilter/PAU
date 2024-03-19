import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Figure fig = null;
        ThreeDim prism = null;
        loop:
        while(true) {
            System.out.println("Wybierz opcje:\n1 - Wybierz figurę");

            // Nie wyświetlaj niepotrzebnych opcji
            if(fig != null)
                System.out.println("2 - Wypisz informacje o figurze\n3 - Wypisz pole figury\n4 - Wypisz obwód figury");

            if(prism != null)
                System.out.println("5 - Wypisz objętość\n6 - Wypisz pole graniastosłupa\n7 - Wypisz informacje o graniastosłupie");

            System.out.println("0 - Zamknij");
            switch (scanner.next())
            {
                // Zamknij program
                case "0":
                {
                    break loop;
                }
                case "1":
                {
                    // Wybierz figurę lub graniastosłup
                    System.out.println("Wybierz opcje:\n1 - Trójkąt\n2 - Kwadrat\n3 - Okrąg\n4 - Graniastosłup\n0 - Anuluj");
                    int _in = scanner.nextInt();
                    if(_in < 4) { // Wybrano figurę
                        fig = null;
                        fig = SelectFigure(_in);
                        if(fig != null)
                            System.out.println("Stworzono figurę");
                    }
                    else if(_in == 4) // Wybrano graniastosłup
                    {
                        // Poproś o podanie podstawy graniastosłupa
                        Figure base = null;
                        System.out.println("Wybierz podstawę graniastosłupa:\n1 - Trójkąt\n2 - Kwadrat\n3 - Okrąg\n0 - Anuluj");
                        base = SelectFigure(scanner.nextInt());

                        if(base == null)
                            break;

                        System.out.println("Podaj wysokość:");

                        try {
                            prism = new ThreeDim(scanner.nextFloat());
                            prism.base = base;
                            System.out.println("Stworzono graniastosłup");
                        }
                        catch (Exception ex)
                        {
                            System.out.println("Zły parametr");
                        }
                    }
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
                case "5":
                {
                    if(prism != null)
                        System.out.printf("Objętość to %f\n", prism.calculateVolume());
                    else
                        System.out.println("Najpierw należy ustalić graniastosłup");
                    break;
                }
                case "6":
                {
                    if(prism != null)
                        System.out.printf("Pole graniastosłupa to %f\n", prism.calculateArea());
                    else
                        System.out.println("Najpierw należy ustalić graniastosłup");
                    break;
                }
                case "7":
                {
                    if(prism != null)
                        prism.print();
                    else
                        System.out.println("Najpierw należy ustalić graniastosłup");
                    break;
                }
                default:
                {
                    // Poinformuj użytkownika o braku podanej komendy
                    System.out.println("Nieznana komenda!");
                    break;
                }
            }
        }
    }

    public static Figure SelectFigure(int selection) {
        //System.out.println("Wybierz opcje:\n1 - Trójkąt\n2 - Kwadrat\n3 - Okrąg\n0 - Anuluj");
        switch (selection) {
            case 1:
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
            case 2:
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
            case 3:
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