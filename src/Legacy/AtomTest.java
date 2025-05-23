package src.Legacy;

public class AtomTest {
    public static void main(String[] args) {
        testConstructor();
        testElectronConfiguration();
        testIonisation();
        testBonding();
        testDecay();
        testSymbols();
        System.out.println("All tests passed.");
    }

    public static void testConstructor() {
        BohrAtom hydrogen = new BohrAtom(1);
        assertEqual(1, hydrogen.getAtomicNumber(), "Constructor atomic number test failed");
        assertEqual(1, hydrogen.getNeutronNumber(), "Constructor neutron number test failed");
        assertEqual(1, BohrAtom.getNumberOfElectrons(hydrogen.getOrbitals()), "Constructor electron number test failed");
    }

    public static void testElectronConfiguration() {
        BohrAtom oxygen = new BohrAtom(8);
        assertEqual(8, BohrAtom.getNumberOfElectrons(oxygen.getOrbitals()), "Electron configuration test failed");
    }

    public static void testIonisation() {
        BohrAtom sodium = new BohrAtom(11);
        sodium.ionise(1);
        assertEqual(10, BohrAtom.getNumberOfElectrons(sodium.getOrbitals()), "Ionisation test failed");
    }

    public static void testBonding() {
        BohrAtom hydrogen1 = new BohrAtom(1);
        BohrAtom hydrogen2 = new BohrAtom(1);
        try {
            hydrogen1.bond(hydrogen2, 1, true);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected exception: " + e.getMessage());
        }
        assertEqual(2, BohrAtom.getNumberOfElectrons(hydrogen1.getOrbitals()), "Bonding test failed");
        assertEqual(2, BohrAtom.getNumberOfElectrons(hydrogen2.getOrbitals()), "Bonding test failed");
        assertTrue(hydrogen1.getBondedTo().contains(hydrogen2), "Bonding test failed");
        assertTrue(hydrogen2.getBondedTo().contains(hydrogen1), "Bonding test failed");
        System.out.println(hydrogen1);
        System.out.println(hydrogen2);

        BohrAtom oxygen1 = new BohrAtom(8);
        BohrAtom oxygen2 = new BohrAtom(8);
        try{
            oxygen1.bond(oxygen2, 2, true);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected exception: " + e.getMessage());
        }
        assertEqual(10, BohrAtom.getNumberOfElectrons(oxygen1.getOrbitals()), "Bonding test failed");
        assertEqual(10, BohrAtom.getNumberOfElectrons(oxygen2.getOrbitals()), "Bonding test failed");
        System.out.println(oxygen1);
        System.out.println(oxygen2);
    }

    public static void testDecay() {
        BohrAtom uranium = new BohrAtom(92, 146);
        BohrAtom helium = uranium.alphaDecay();
        assertEqual(90, uranium.getAtomicNumber(), "Alpha decay atomic number test failed");
        assertEqual(144, uranium.getNeutronNumber(), "Alpha decay neutron number test failed");
        assertEqual(2, helium.getAtomicNumber(), "Alpha decay helium nucleus release failed");

        BohrAtom carbon = new BohrAtom(6,8);
        carbon.betaDecay(false);

        assertEqual(7, carbon.getAtomicNumber(), "Beta decay atomic number test failed");
        assertEqual(7, carbon.getNeutronNumber(), "Beta decay neutron number test failed");
    }

    public static void testSymbols(){
        BohrAtom hydrogen = new BohrAtom("H", false);
        assertEqual(1, hydrogen.getAtomicNumber(), "Symbols and names test failed");
        assertTrue(hydrogen.getName().equals("Hydrogen"), "Symbols and names test failed");

        BohrAtom oxygen = new BohrAtom("Oxygen", true);
        assertEqual(8, oxygen.getAtomicNumber(), "Symbols and names test failed");
        assertTrue(oxygen.getSymbol().equals("O"), "Symbols and names test failed");
    }

    public static void assertEqual(int expected, int actual, String message) {
        if (expected != actual) {
            throw new RuntimeException(message + " Expected: " + expected + " but got: " + actual);
        } else System.out.println("Case Passed: " + expected + " == " + actual);
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new RuntimeException(message);
        } else System.out.println("Case Passed: " + true);
    }
}
