package src;

public class Atom {
    private int protonNumber;
    private int neutronNumber;
    private static final int S = 2;
    private static final int P = 6;
    private static final int D = 10;
    private static final int F = 14;
    private int[][] orbitals = {
            {0},
            {0, 0},
            {0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0},
            {0, 0}
    };
    private static final int[][] maxOrbitals = {
            {2},
            {2, 6},
            {2, 6, 10},
            {2, 6, 10, 14},
            {2, 6, 10, 14},
            {2, 6, 10},
            {2, 6}
    };
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

    public Atom(int protonNumber, int neutronNumber, int electronNumber) {
        this.protonNumber = protonNumber;
        this.neutronNumber = neutronNumber;
        aufbau(electronNumber);
    }

    public Atom(int protonNumber, int neutronNumber) {
        this(protonNumber, neutronNumber, protonNumber);
    }

    public Atom(int protonNumber) {
        this(protonNumber, protonNumber);
    }

    public void aufbau(int electronNumber) {

    }
}
