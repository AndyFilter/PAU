import java.util.Arrays;

public class Square extends Figure implements Printing {

    double side = 0;

    public Square(double a) throws Exception {
        if(a < 0)
            throw new Exception("Bad parameters");

        side = a;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public double calculatePerimeter() {
        return side * 4;
    }

    @Override
    public void print() {
        System.out.printf("Boki: [%f, %f]\nPole kwadratu to: %f\nObwód: %f\n",
                side, side, calculateArea(), calculatePerimeter());
    }
}
