    package src;

    import java.util.ArrayList;

    public class BohrAtom implements Atom {

        private int neutronNumber;
        private int atomicNumber;


        public int[][] orbitals = {
                {0},
                {0, 0},
                {0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0},
                {0, 0}
        };
        private ArrayList<Atom> bondedTo = new ArrayList<>();
        private String symbol;
        private String name;

        private static final OrbitalLabel[] labels = {
                OrbitalLabel.S,
                OrbitalLabel.P,
                OrbitalLabel.D,
                OrbitalLabel.F
        };

        private static final int S = 2;
        private static final int P = 6;
        private static final int D = 10;
        private static final int F = 14;
        private static final int[][] maxOrbitals = {
                {S},
                {S, P},
                {S, P, D},
                {S, P, D, F},
                {S, P, D, F},
                {S, P, D},
                {S, P}
        };

        public static String[] elementSymbols = {
                "H",  "He", "Li", "Be", "B",  "C",  "N",  "O",  "F",  "Ne",
                "Na", "Mg", "Al", "Si", "P",  "S",  "Cl", "Ar", "K",  "Ca",
                "Sc", "Ti", "V",  "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn",
                "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y",  "Zr",
                "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn",
                "Sb", "Te", "I",  "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd",
                "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb",
                "Lu", "Hf", "Ta", "W",  "Re", "Os", "Ir", "Pt", "Au", "Hg",
                "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th",
                "Pa", "U",  "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm",
                "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds",
                "Rg", "Cn", "Nh", "Fl", "Mc", "Lv", "Ts", "Og"
        };

        public static String[] elementNames = {
                "Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon", "Nitrogen", "Oxygen", "Fluorine", "Neon",
                "Sodium", "Magnesium", "Aluminium", "Silicon", "Phosphorus", "Sulfur", "Chlorine", "Argon", "Potassium", "Calcium",
                "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese", "Iron", "Cobalt", "Nickel", "Copper", "Zinc",
                "Gallium", "Germanium", "Arsenic", "Selenium", "Bromine", "Krypton", "Rubidium", "Strontium", "Yttrium", "Zirconium",
                "Niobium", "Molybdenum", "Technetium", "Ruthenium", "Rhodium", "Palladium", "Silver", "Cadmium", "Indium", "Tin",
                "Antimony", "Tellurium", "Iodine", "Xenon", "Cesium", "Barium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium",
                "Promethium", "Samarium", "Europium", "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium", "Ytterbium",
                "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium", "Osmium", "Iridium", "Platinum", "Gold", "Mercury",
                "Thallium", "Lead", "Bismuth", "Polonium", "Astatine", "Radon", "Francium", "Radium", "Actinium", "Thorium",
                "Protactinium", "Uranium", "Neptunium", "Plutonium", "Americium", "Curium", "Berkelium", "Californium", "Einsteinium", "Fermium",
                "Mendelevium", "Nobelium", "Lawrencium", "Rutherfordium", "Dubnium", "Seaborgium", "Bohrium", "Hassium", "Meitnerium", "Darmstadtium",
                "Roentgenium", "Copernicium", "Nihonium", "Flerovium", "Moscovium", "Livermorium", "Tennessine", "Oganesson"
        };

        //ORDER for aufbau();
        //1s
        //2s       2p
        //3s       3p
        //4s    3d 4p
        //5s    4d 5p
        //6s 4f 5d 6p
        //7s 5f 6d 7p

        private static final int[][] shellOrder = {
                {1,1},
                {2,1}, {2,2},
                {3,1}, {3,2},
                {4,1}, {3,3}, {4,2},
                {5,1}, {4,3}, {5,2},
                {6,1}, {4,4}, {5,3}, {6,2},
                {7,1}, {5,4}, {6,3}, {7,2}
        };

        public BohrAtom(int atomicNumber, int neutronNumber, int electronNumber) {
            this.atomicNumber = atomicNumber;
            this.neutronNumber = neutronNumber;
            this.name = elementNames[atomicNumber];
            this.symbol = elementSymbols[atomicNumber];
            aufbau(electronNumber);
        }

        public BohrAtom(int atomicNumber, int neutronNumber) {
                    this(atomicNumber, neutronNumber, atomicNumber);
        }

        public BohrAtom(int atomicNumber) {
            this(atomicNumber, atomicNumber);
        }

        public BohrAtom(String elementName, boolean isName) {
            if(isName) {
                for (int i = 0; i < elementNames.length; i++) {
                    if (elementNames[i].equals(elementName)) {
                        atomicNumber = i+1;
                        neutronNumber = i+1;
                        name = elementNames[i];
                        symbol = elementSymbols[i];
                        aufbau(atomicNumber);
                        break;
                    }
                }
            } else{
                for (int i = 0; i < elementSymbols.length; i++) {
                    if (elementSymbols[i].equals(elementName)) {
                        atomicNumber = i+1;
                        neutronNumber = i+1;
                        name = elementNames[i];
                        symbol = elementSymbols[i];
                        aufbau(atomicNumber);
                        break;
                    }
                }
            }
        }

        public static void addToJSON(String fileName, BohrAtom atom){
            String json = "{";
            
            json += "}";

        }

        public static String deepToString(int[][] orbitals) {
            return Atom.deepToString(orbitals);
        }

        public static int getNumberOfElectrons(int[] shell){
            int numberOfElectrons = 0;
            for (int s: shell) {
                numberOfElectrons += s;
            }
            return numberOfElectrons;
        }

        public static int getNumberOfElectrons(int[][] shells){
            int numberOfElectrons = 0;
            for (int[] shell : shells) {
                numberOfElectrons += getNumberOfElectrons(shell);
            }
            return numberOfElectrons;
        }

        public void setup(int[][] orbitals) {
            orbitals = new int[][]{
                    {0},
                    {0, 0},
                    {0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0},
                    {0, 0}
            };
        }
        public void aufbau(int electronNumber) throws AtomConstructionException {

            for (int[] orbital : shellOrder) {
                int n = orbital[0] - 1; // Convert to zero-based index
                int l = orbital[1] - 1;

                // Safety check: Ensure n and l are within bounds
                if (n < 0 || n >= orbitals.length || l < 0 || l >= orbitals[n].length) {
                    setup(this.orbitals);
                    throw new AtomConstructionException("Principal Quantum Number and Angular Momentum Number out of bounds");
                }

                int maxElectrons = maxOrbitals[n][l];
                int toFill = Math.min(electronNumber, maxElectrons);

                orbitals[n][l] += toFill;
                electronNumber -= toFill;

                if (electronNumber == 0) break;
            }

            // Handle special electron configurations for exceptions
            applyExceptions();
        }
        private void applyExceptions() {
            // Chromium (24) and Molybdenum (42)
            if (atomicNumber == 24 || atomicNumber == 42) {
                orbitals[3][0] = 1; // 4s1
                orbitals[2][2] = 5; // 3d5 (or 4d5 for Mo)
            }

            // Copper (29), Silver (47), Gold (79)
            if (atomicNumber == 29 || atomicNumber == 47 || atomicNumber == 79) {
                orbitals[3][0] = 1; // 4s1 (or equivalent)
                orbitals[2][2] = 10; // 3d10
            }

            // Niobium (41), Ruthenium (44), Rhodium (45)
            if (atomicNumber == 41) { // Niobium
                orbitals[4][0] = 1; // 5s1
                orbitals[3][2] = 4; // 4d4
            }
            if (atomicNumber == 44) { // Ruthenium
                orbitals[4][0] = 1; // 5s1
                orbitals[3][2] = 7; // 4d7
            }
            if (atomicNumber == 45) { // Rhodium
                orbitals[4][0] = 1; // 5s1
                orbitals[3][2] = 8; // 4d8
            }
            // Palladium (46)
            if (atomicNumber == 46) {
                orbitals[4][0] = 0; // 5s0
                orbitals[3][2] = 10; // 4d10
            }
            // Platinum (78)
            if (atomicNumber == 78) {
                orbitals[5][0] = 1; // 6s1
                orbitals[4][2] = 10; // 5d10
            }
            // Lawrencium (103)
            if (atomicNumber == 103) {
                orbitals[6][0] = 1; // 7s1
                orbitals[4][3] = 14; // 5f14
                orbitals[5][2] = 2; // 6d2
            }
        }

        public void ionise(int electronLoss) {
            int electronsToLose = electronLoss;
            int electronsToGain = -electronLoss;
            if (electronLoss > 0) {
                for (int i = orbitals.length - 1; i >= 0; i--) {
                    for (int j = orbitals[i].length - 1; j >= 0; j--) {
                        if (orbitals[i][j] > 0) {
                            int toRemove = Math.min(orbitals[i][j], electronsToLose);
                            electronsToLose -= toRemove;
                            orbitals[i][j] -= toRemove;
                        }
                    }
                }
            } else if (electronsToGain > 0) {
                for (int i = 0; i < orbitals.length; i++) {
                    for (int j = 0; j < orbitals[i].length ; j++) {
                        if (orbitals[i][j] < maxOrbitals[i][j]) {
                            int needed = Math.min(maxOrbitals[i][j] - orbitals[i][j], electronsToGain);
                            electronsToGain -= needed;
                            orbitals[i][j] += needed;
                        }
                    }
                }
            }

        }



        public void bond(Atom other, int bondOrder, boolean needToRecur) throws CovalentBondException {
            int[] thisValenceShell = this.getValenceShell();
            int[] maxThisValenceShell = this.getMaxValenceShell();
            int thisMaxCapacity = this.getMaxCapacityValence();
            int[] otherValenceShell = other.getValenceShell();
            int otherMaxCapacity = other.getMaxCapacityValence();


            if(bondOrder > 3){throw new CovalentBondException("Bond order cannot be more than 3");}
            if(bondOrder < 1) {throw new CovalentBondException("Bond order must be positive");}

            int electronsInThis = getNumberOfElectrons(thisValenceShell);
            int electronsInOther = getNumberOfElectrons(otherValenceShell);

            if(electronsInThis < bondOrder){throw new CovalentBondException("Not enough Electrons to bond");}
            if(electronsInOther < bondOrder){throw new CovalentBondException("Not enough Electrons to bond");}

            if(bondOrder+electronsInThis > thisMaxCapacity){throw new CovalentBondException("Not enough space to bond");}
            if(bondOrder + electronsInOther > otherMaxCapacity){throw new CovalentBondException("Not enough space to bond");}

            if(needToRecur)other.bond(this, bondOrder, false);

            this.bondedTo.add(other);
            this.addElectronsTo(thisValenceShell, maxThisValenceShell, bondOrder);
        }

        public int[] getValenceShell(){
            for(int i = orbitals.length - 1; i >= 0; i--){
                if(orbitals[i][0] != 0){
                    return orbitals[i];
                }
            }
            throw new CovalentBondException("All shells are empty");
        }

        public int[] getMaxValenceShell(){
            for(int i = orbitals.length - 1; i >= 0; i--){
                if(orbitals[i][0] != 0){
                    return maxOrbitals[i];
                }
            }
            throw new CovalentBondException("All shells are empty");
        }

        public void addElectronsTo(int[] shell, int[] maxCapacity, int electronsToAdd) {
            int eToAdd = electronsToAdd;
            for (int j = 0; j < shell.length ; j++) {
                if (shell[j] < maxCapacity[j]) {
                    int needed = Math.min(maxCapacity[j] - shell[j], eToAdd);
                    eToAdd -= needed;
                    shell[j] += needed;
                }
            }
        }


        public int getMaxCapacityValence(){
            int[] valence = getValenceShell();
            return switch (valence.length) {
                case 1 -> 2;
                case 2 -> 8;
                case 3 -> 18;
                case 4 -> 32;
                default -> throw new CovalentBondException("Unknown Error");
            };
        }

        public int[][] getOrbitals() {
            return orbitals;
        }

        public int getNeutronNumber() {return neutronNumber;}
        public int getAtomicNumber() {return atomicNumber;}
        public String getName() {return name;}
        public String getSymbol() {return symbol;}

        public ArrayList<Atom> getBondedTo() {return bondedTo;}

        public BohrAtom alphaDecay(){
            atomicNumber -= 2;
            neutronNumber -= 2;
            setup(orbitals);
            aufbau(atomicNumber);
            return new BohrAtom(2,2);
        }

        public void betaDecay(boolean isPositive){
            if(isPositive){
                atomicNumber--;
                neutronNumber--;
            } else {
                atomicNumber++;
                neutronNumber--;
            }
            setup(orbitals);
            aufbau(atomicNumber);
        }

        public String toString(){
            return "Atom:\n" +
                    "ProtonNumber: "+
                    atomicNumber +"\n"+
                    "NeutronNumber: "
                    +neutronNumber +"\n"+
                    "Orbitals: \n"
                    + deepToString(orbitals);
        }


    }
