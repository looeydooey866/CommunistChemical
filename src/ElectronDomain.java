package src;

import java.util.ArrayList;

public class ElectronDomain {
    ArrayList<Shell> associatedShells;
    DomainType domainType;
    ArrayList<Electron> electrons;


    private void setup(Shell shell) {
        this.associatedShells = new ArrayList<>();
        associatedShells.add(shell);
    }

    public ElectronDomain(Shell shell, int numElectrons) {
        setup(shell);
        electrons = new ArrayList<>();
        for (int i = 0; i < numElectrons; i++) {
            electrons.add(new Electron());
        }
        if (numElectrons == 1) {
            this.domainType = DomainType.LONE_ELECTRON;
        } else if (numElectrons == 2) {
            this.domainType = DomainType.LONE_PAIR;
        } else throw new ElectronDomainConstructionException("Electron Domain must have 1 or 2 electrons.");
    }

    public ElectronDomain(Shell shell, ArrayList<Electron> electrons, DomainType domainType) {
        setup(shell);
        this.electrons = electrons;
    }


    public ElectronDomain(Shell shell, Electron electron, DomainType domainType) {
        setup(shell);
        this.electrons = new ArrayList<>();
        electrons.add(electron);
    }

    public ElectronDomain split() {
        switch (this.domainType) {
            case LONE_PAIR -> {
                ElectronDomain newDomain = new ElectronDomain(this.associatedShells.getFirst(), this.electrons.get(1), DomainType.LONE_ELECTRON);
                this.electrons.remove(1);
                this.domainType = DomainType.LONE_ELECTRON;
                return newDomain;
            }
            case LONE_ELECTRON -> throw new DomainException("Cannot split a lone elctron");

            case SIGMA_BOND_PAIR ->{
                ElectronDomain newDomain = new ElectronDomain(this.associatedShells.get(1), this.electrons.get(1), DomainType.LONE_ELECTRON);
                this.electrons.remove(1);
                this.associatedShells.remove(1);
                return newDomain;
            }
            case PI_BOND_PAIR -> {
                //TODO: Add Functionality for splitting pi bond pair with >=2 associated shells
                throw new DomainException("In Progress");
            }
            default -> throw new DomainException("Something went wrong while splitting the domain.");
        }
    }
}

