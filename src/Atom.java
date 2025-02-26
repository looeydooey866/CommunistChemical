package src;

public class Atom {
    private static final char[] ORBITAL_LABELS = {'s', 'p', 'd', 'f'};
    private int neutronNumber;
    private int atomicNumber;
    private static final int S = 2;
    private static final int P = 6;
    private static final int D = 10;
    private static final int F = 14;
    public int[][] orbitals = {
            {0},
            {0, 0},
            {0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0},
            {0, 0}
    };
    private static final int[][] maxOrbitals = {
            {S},
            {S, P},
            {S, P, D},
            {S, P, D, F},
            {S, P, D, F},
            {S, P, D},
            {S, P}
    };
    //ORDER for aufbau();
    //1s
    //2s       2p
    //3s       3p
    //4s    3d 4p
    //5s    4d 5p
    //6s 4f 5d 6p
    //7s 5f 6d 7p

    private static final int[][] orbitalOrder = {
            {1,1},
            {2,1}, {2,2},
            {3,1}, {3,2},
            {4,1}, {3,3}, {4,2},
            {5,1}, {4,3}, {5,2},
            {6,1}, {4,4}, {5,3}, {6,2},
            {7,1}, {5,4}, {6,3}, {7,2}
    };

    public Atom(int atomicNumber, int neutronNumber, int electronNumber) {
        this.atomicNumber = atomicNumber;
        this.neutronNumber = neutronNumber;
        aufbau(electronNumber);
    }

    public Atom(int atomicNumber, int neutronNumber) {
                this(atomicNumber, neutronNumber, atomicNumber);
    }

    public Atom(int atomicNumber) {
        this(atomicNumber, atomicNumber);
    }


    public void aufbau(int electronNumber) {
        System.out.println("Starting aufbau with " + electronNumber + " electrons.");

        for (int[] orbital : orbitalOrder) {
            int n = orbital[0] - 1; // Convert to zero-based index
            int l = orbital[1] - 1;

            // Safety check: Ensure n and l are within bounds
            if (n < 0 || n >= orbitals.length || l < 0 || l >= orbitals[n].length) {
                System.err.println("Index out of bounds: n=" + n + ", l=" + l);
                continue; // Skip this iteration instead of crashing
            }

            int maxElectrons = maxOrbitals[n][l];
            int toFill = Math.min(electronNumber, maxElectrons);

            orbitals[n][l] += toFill;
            electronNumber -= toFill;

            System.out.println("Added " + toFill + " electrons to orbital " + (n + 1) + ORBITAL_LABELS[l]);

            if (electronNumber == 0) break;
        }

        // Handle special electron configurations for exceptions
        applyExceptions();
    }
    private void applyExceptions() {
        // Chromium (24) and Molybdenum (42)
        if (atomicNumber == 24 || atomicNumber == 42) {
            System.out.println("Applied exception for Cr/Mo.");
            orbitals[3][0] = 1; // 4s1
            orbitals[2][2] = 5; // 3d5 (or 4d5 for Mo)
        }

        // Copper (29), Silver (47), Gold (79)
        if (atomicNumber == 29 || atomicNumber == 47 || atomicNumber == 79) {
            System.out.println("Applied exception for Cu/Ag/Au.");
            orbitals[3][0] = 1; // 4s1 (or equivalent)
            orbitals[2][2] = 10; // 3d10
        }

        // Niobium (41), Ruthenium (44), Rhodium (45)
        if (atomicNumber == 41) { // Niobium
            System.out.println("Applied exception for Nb.");
            orbitals[4][0] = 1; // 5s1
            orbitals[3][2] = 4; // 4d4
        }
        if (atomicNumber == 44) { // Ruthenium
            System.out.println("Applied exception for Ru.");
            orbitals[4][0] = 1; // 5s1
            orbitals[3][2] = 7; // 4d7
        }
        if (atomicNumber == 45) { // Rhodium
            System.out.println("Applied exception for Rh.");
            orbitals[4][0] = 1; // 5s1
            orbitals[3][2] = 8; // 4d8
        }

        // Palladium (46)
        if (atomicNumber == 46) {
            System.out.println("Applied exception for Pd.");
            orbitals[4][0] = 0; // 5s0
            orbitals[3][2] = 10; // 4d10
        }

        // Platinum (78)
        if (atomicNumber == 78) {
            System.out.println("Applied exception for Pt.");
            orbitals[5][0] = 1; // 6s1
            orbitals[4][2] = 10; // 5d10
        }

        // Lawrencium (103)
        if (atomicNumber == 103) {
            System.out.println("Applied exception for Lr.");
            orbitals[6][0] = 1; // 7s1
            orbitals[4][3] = 14; // 5f14
            orbitals[5][2] = 2; // 6d2
        }
    }

    public void ionise(int electronLoss) {
        if (electronLoss == 0) {
            System.out.println("No change in electron count. electronLoss must be non-zero.");
            return;
        }

        if (electronLoss > 0) {
            // Remove electrons
            System.out.println("Removing " + electronLoss + " electrons from the atom.");
            removeElectrons(electronLoss);
        } else {
            // Add electrons (electronLoss is negative)
            int electronGain = -electronLoss; // Convert to positive
            System.out.println("Adding " + electronGain + " electrons to the atom.");
            addElectrons(electronGain);
        }
    }

    // Helper method to remove electrons
    private void removeElectrons(int electronLoss) {
        int totalElectrons = getTotalElectrons();
        if (electronLoss > totalElectrons) {
            System.out.println("Cannot remove more electrons than available. Total electrons: " + totalElectrons);
            return;
        }

        // Iterate through orbitals in reverse order (outermost to innermost)
        for (int i = orbitals.length - 1; i >= 0; i--) {
            for (int j = orbitals[i].length - 1; j >= 0; j--) {
                int electronsInOrbital = orbitals[i][j];
                int electronsToRemove = Math.min(electronLoss, electronsInOrbital);

                orbitals[i][j] -= electronsToRemove;
                electronLoss -= electronsToRemove;

                System.out.println("Removed " + electronsToRemove + " electrons from orbital " + (i + 1) + ORBITAL_LABELS[j]);

                if (electronLoss == 0) {
                    return; // All electrons removed
                }
            }
        }
    }

    // Helper method to add electrons
    private void addElectrons(int electronGain) {
        // Iterate through orbitals in the order of the Aufbau principle
        for (int[] orbital : orbitalOrder) {
            int n = orbital[0] - 1; // Convert to zero-based index
            int l = orbital[1] - 1;

            // Safety check: Ensure n and l are within bounds
            if (n < 0 || n >= orbitals.length || l < 0 || l >= orbitals[n].length) {
                System.err.println("Index out of bounds: n=" + n + ", l=" + l);
                continue; // Skip this iteration
            }

            int maxElectrons = maxOrbitals[n][l];
            int availableSpace = maxElectrons - orbitals[n][l];
            int toAdd = Math.min(electronGain, availableSpace);

            orbitals[n][l] += toAdd;
            electronGain -= toAdd;

            System.out.println("Added " + toAdd + " electrons to orbital " + (n + 1) + ORBITAL_LABELS[l]);

            if (electronGain == 0) {
                break; // All electrons added
            }
        }

        if (electronGain > 0) {
            System.out.println("Warning: Not enough orbitals to accommodate all " + electronGain + " additional electrons.");
        }
    }

    // Helper method to calculate the total number of electrons in the atom
    private int getTotalElectrons() {
        int total = 0;
        for (int[] orbital : orbitals) {
            for (int electrons : orbital) {
                total += electrons;
            }
        }
        return total;
    }


    public int[][] getOrbitals() {
        return orbitals;
    }

    public int getNeutronNumber() {return neutronNumber;}

    public void setNeutronNumber(int neutronNumber) {this.neutronNumber = neutronNumber;}

    public int getAtomicNumber() {return atomicNumber;}

    public void alphaDecay(){
        atomicNumber -= 2;
        neutronNumber -= 2;
    }

    
}
