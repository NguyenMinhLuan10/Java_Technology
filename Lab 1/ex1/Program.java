public class Program {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Invalid expression");
            return;
        }

        else {
            int number1 = Integer.parseInt(args[0]);
            String operator = args[1];
            int number2 = Integer.parseInt(args[2]);

            switch (operator) {
                case "+":
                    System.err.println(number1 + number2);
                    break;
                case "-":
                    System.err.println(number1 - number2);
                    break;
                case "/":
                    System.out.println(divide(number1, number2));
                    break;
                case "x":
                    System.out.println(multiplication(number1, number2));
                    break;
                case "^":
                    System.out.println(power(number1, number2));
                    break;
                default:
                    System.err.println("Unsupported operator");
                    break;
            }
        }
    }

    public static double divide(int number1, int number2) {
        if (number2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return (double) number1 / number2;
    }

    public static int multiplication(int number1, int number2) {
        return number1 * number2;
    }

    public static int power(int number1, int number2) {
        return (int) Math.pow(number1, number2);
    }
}
