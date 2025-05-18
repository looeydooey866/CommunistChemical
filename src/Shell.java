package src;

import java.util.ArrayList;

public class Shell {
    Atom associatedAtom;
    int shellNumber;
    boolean isValence;
    ShellType shellType;
    ArrayList<ElectronDomain> domains;

    public static int[] maxElectrons = {

    };

    public Shell(Atom associatedAtom, int shellNumber, ShellType shellType, boolean isValence){
        this.associatedAtom = associatedAtom;
        this.shellNumber = shellNumber;
        this.shellType =   shellType;
        this.isValence = isValence;
    }

    //helper method for creating the electron domains
    private void setupDomains(int numElectrons){

    }
}
