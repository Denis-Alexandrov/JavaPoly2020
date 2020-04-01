package test1;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("Enter the beginning of the range: ");
        int begin = getInt();
        System.out.print("Enter the end of the range: ");
        int end = getInt();

        if (begin < end) {
            for (; begin < end; begin++) {
                if (isPrime(begin)) System.out.println(begin);
            }
        } else if (begin > end){
            for (; end < begin; end++) {
                if (isPrime(end)) System.out.println(end);
            }
        } else {
            if (isPrime(begin)) System.out.println(begin);
        }
    }

    private static int getInt() {
        Scanner in = new Scanner(System.in);
        int range = 0;
        try {
            range = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.print("Incorrect input. Please, try again: ");
            return getInt();
        }
        return range;
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int k = 2; k*k <= n; k++) {
            if (n % k == 0) return false;
        }
        return true;
    }

}
