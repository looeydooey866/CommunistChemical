package src;

import java.util.Arrays;

public class GeneralTester {
    public static void main(String[] args) {
        int[] testElements = {24, 29, 41, 42, 44, 45, 46, 47, 78, 79, 103};
        // Cr, Cu, Nb, Mo, Ru, Rh, Pd, Ag, Pt, Au, Lr

        for (int e : testElements) {
            System.out.println("\nTesting element with " + e + " electrons:");
            Atom element = new Atom(e, e);
            System.out.println(Arrays.deepToString(element.getOrbitals()));
        }

    }

}
