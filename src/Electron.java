package src;

import java.util.ArrayList;

public class Electron {
    private boolean spin;
    private Electron pairedTo;
    private static final Charge charge = Charge.NEGATIVE;
    private ArrayList<Atom> associatedAtom;
    private ArrayList<Integer> principalQuantumNumbers;
    private ArrayList<Integer> angularMomentumQuantumNumbers;
}

