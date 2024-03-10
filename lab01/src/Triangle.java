import java.util.Arrays;

public class Triangle extends Figure implements Printing, Inputting {

    double[] sides = new double[3];

    public Triangle(double a, double b, double c) throws Exception {
        sides = new double[] {a,b,c};
        double max = Math.max(Math.max(a,b),c);
        // Throw if not a valid triangle
        if(max > (Arrays.stream(sides).sum() - max)) {
            sides = null;
            throw new Exception("Bad parameters");
        }
    }

    @Override
    public double calculateArea() {
        double s = Arrays.stream(sides).sum() / 2;
        return Math.sqrt(s * (s-sides[0]) * (s - sides[1]) * (s - sides[2]));
    }

    @Override
    public double calculatePerimeter() {
        return Arrays.stream(sides).sum();
    }

    @Override
    public void print() {
        System.out.printf("Boki: [%f, %f, %f]\nPole trójkąta to: %f\nObwód: %f\n",
                sides[0], sides[1], sides[2], calculateArea(), calculatePerimeter());
    }

    @Override
    public void InputData() {

    }
}
