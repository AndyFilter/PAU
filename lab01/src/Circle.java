public class Circle extends Figure implements Printing, Inputting {

    double radius = 0;

    public Circle(double r) throws Exception {
        if(r < 0)
            throw new Exception("Bad parameters");
        radius = r;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double calculatePerimeter() {
        return Math.PI * 2 * radius;
    }

    @Override
    public void print() {
        System.out.printf("Promień: %f\nPole Koła to: %f\nObwód: %f\n",
                radius, calculateArea(), calculatePerimeter());
    }

    @Override
    public void InputData() {

    }
}
