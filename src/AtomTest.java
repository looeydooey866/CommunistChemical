package src;

public class AtomTest {
    public static void main(String[] args) {
        testConstructor();
        testElectronConfiguration();
        testIonisation();
        testBonding();
        testAlphaDecay();
        System.out.println("All tests passed.");
    }

    public static void testConstructor() {
        Atom hydrogen = new Atom(1);
        assertEqual(1, hydrogen.getAtomicNumber(), "Constructor atomic number test failed");
        assertEqual(1, hydrogen.getNeutronNumber(), "Constructor neutron number test failed");
        assertEqual(1, Atom.getNumberOfElectrons(hydrogen.getShells()), "Constructor electron number test failed");
    }

    public static void testElectronConfiguration() {
        Atom oxygen = new Atom(8);
        assertEqual(8, Atom.getNumberOfElectrons(oxygen.getShells()), "Electron configuration test failed");
    }

    public static void testIonisation() {
        Atom sodium = new Atom(11);
        sodium.ionise(1);
        assertEqual(10, Atom.getNumberOfElectrons(sodium.getShells()), "Ionisation test failed");
    }

    public static void testBonding() {
        Atom hydrogen1 = new Atom(1);
        Atom hydrogen2 = new Atom(1);
        try {
            hydrogen1.bond(hydrogen2, 1, true);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected exception: " + e.getMessage());
        }
        assertTrue(hydrogen1.getBondedTo().contains(hydrogen2), "Bonding test failed");
        assertTrue(hydrogen2.getBondedTo().contains(hydrogen1), "Bonding test failed");
    }

    public static void testAlphaDecay() {
        Atom uranium = new Atom(92, 146);
        uranium.alphaDecay();
        assertEqual(90, uranium.getAtomicNumber(), "Alpha decay atomic number test failed");
        assertEqual(144, uranium.getNeutronNumber(), "Alpha decay neutron number test failed");
    }

    public static void assertEqual(int expected, int actual, String message) {
        if (expected != actual) {
            throw new RuntimeException(message + " Expected: " + expected + " but got: " + actual);
        }
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new RuntimeException(message);
        }
    }
}
