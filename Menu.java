import javatrix.Matrix;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu
{
    private static Random num = new Random();

    public static void main(String[] args)
    {
        Scanner inputReader = new Scanner(System.in);
        
        boolean sentinel = false;

        while(!sentinel)
        {
            System.out.println("Please make your selection below:");
            System.out.println("Print Matrix [1]");
            System.out.println("Transpose Matrix [2]");
            System.out.println("Multiply by Scalar [3]");
            System.out.println("Multiply by Matrix [4]");
            System.out.println("Quit [5]");

            int input = 0;
            boolean flag = false;

            while(!flag)
            {
                try
                {
                    input = inputReader.nextInt();
                    flag = true;
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Please enter a valid integer");
                }
            }

            System.out.println();
            
            double[][] aVals = {{rand(), rand(), rand()}, {rand(), rand(), rand()}, {rand(), rand(), rand()}};
            double[][] bVals = {{rand(), rand(), rand()}, {rand(), rand(), rand()}, {rand(), rand(), rand()}};
            
            Matrix A = new Matrix(aVals);
            Matrix B = new Matrix(bVals);

            switch(input)
            {
                case 1: A.print(5, 2);
                        break;
                case 2: A.print(5, 2);
                        System.out.println();
                        A.transpose().print(5, 2);
                        break;
                case 3: A.print(5, 2);
                        System.out.println();
                        A.times(rand()).print(5, 2);
                        break; 
                case 4: A.print(5, 2);
                        System.out.println();
                        B.print(5, 2);
                        System.out.println();
                        A.times(B).print(5, 2);
                        break;
                case 5: sentinel = true;
                        break;
                default: System.out.println("Valid integer not entered");
                         break;
            }

            System.out.println();
        }
    }

    private static double rand()
    {   
        return Math.floor((num.nextDouble() + (num.nextInt(10)) * 100)) / 100;
    }
}
