package vn.edu.tdtu;

import vn.edu.tdtu.ArrayOutput;
import vn.edu.tdtu.ArrayHandler;

public class Program {
    public static void main(String[] args) {
        int[] a = {1, 3, 2, 6, 8};
        int[] b = {2, 5, 1, 2, 7};

        ArrayOutput.print(a);
        ArrayOutput.print(b);

        int[] c = ArrayHandler.merge(a, b);
        ArrayOutput.print(c);

        ArrayHandler.sort(c);
        ArrayOutput.print(c);

        ArrayOutput.write(c, "D:\\Year_III\\HK1\\Java\\Lab\\Lab 1\\ex2\\vn\\edu\\tdtu\\output.txt");
    }
}
