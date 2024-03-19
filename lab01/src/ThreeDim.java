import java.util.Arrays;

public class ThreeDim implements Printing {

    Figure base;
    double height = 0;

    public ThreeDim(double h) throws Exception {
        if(h < 0)
            throw new Exception("Bad parameters");

        height = h;
    }

    public double calculateArea() {
        switch (base) {
            case Triangle tri -> {
                return base.calculateArea() * 2 +
                        (Arrays.stream(tri.sides).sum() * height);
            }
            case Square sqr -> {
                return base.calculateArea() * 2 +
                        (sqr.side * height * 4);
            }
            case Circle cir -> {
                return base.calculateArea() * 2 +
                        (cir.radius * 2 * Math.PI * height);
            }
            case null, default -> System.out.println("ERROR");
        }
        return 0;
    }

    public double calculateVolume() {
        return base.calculateArea() * height;
    }

    @Override
    public void print() {
        switch (base) {
            case Triangle tri -> {
                System.out.println("Graniastosłup o pods. trójkąta");
            }
            case Square sqr -> {
                System.out.println("Graniastosłup o pods. kwadratu");
            }
            case Circle cir -> {
                System.out.println("Graniastosłup o pods. koła");
            }
            case null, default -> System.out.println("ERROR");
        }

        System.out.printf("Objętość: %f\n", calculateVolume());
    }
}
