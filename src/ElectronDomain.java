package src;

import src.Exceptions.DomainException;
import src.Exceptions.ElectronDomainConstructionException;

import java.util.ArrayList;

public class ElectronDomain {
    private ArrayList<Shell> associatedShells;
    private DomainType domainType;
    private ArrayList<Electron> electrons;


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
        if(domainType != DomainType.UNKNOWN) this.domainType = domainType;
        else if (electrons.size() == 1) this.domainType = DomainType.LONE_ELECTRON;
        else if (electrons.size() == 2) this.domainType = DomainType.LONE_PAIR;
    }

    public ElectronDomain(Shell shell, Electron electron, DomainType domainType) {
        setup(shell);
        this.electrons = new ArrayList<>();
        electrons.add(electron);
    }

    public ElectronDomain[] split() {
        switch (this.domainType) {
            case LONE_PAIR -> {
                ElectronDomain newDomain = new ElectronDomain(this.associatedShells.getFirst(), this.electrons.get(1), DomainType.LONE_ELECTRON);
                this.electrons.remove(1);
                this.domainType = DomainType.LONE_ELECTRON;
                return new ElectronDomain[]{newDomain};
            }
            case LONE_ELECTRON -> throw new DomainException("Cannot split a lone electron");

            case SIGMA_BOND_PAIR ->{
                ElectronDomain newDomain = new ElectronDomain(this.associatedShells.get(1), this.electrons.get(1), DomainType.LONE_ELECTRON);
                this.electrons.remove(1);
                this.associatedShells.remove(1);
                return new ElectronDomain[]{newDomain};
            }
            case PI_BOND_GROUP -> {
                int electronsPerShell = associatedShells.size() / electrons.size();
                if(associatedShells.size() % electrons.size() == 0){
                    ElectronDomain[] domains = new ElectronDomain[associatedShells.size()/electrons.size()];
                    for (int i = 0; i < associatedShells.size(); i++) {
                        if(i == 0){
                            continue;
                        }
                        Shell sh = associatedShells.get(i);
                        ArrayList<Electron> es = new ArrayList<>();
                        int min = i*electrons.size();
                        int max = i*(electrons.size()+1);
                        DomainType dtype;
                        if (electronsPerShell == 1){
                            dtype = DomainType.LONE_ELECTRON;
                        } else if (electronsPerShell == 2){
                            dtype = DomainType.LONE_PAIR;
                        } else dtype = DomainType.UNKNOWN;
                        for(int j = min;j<max;j++){
                            es.add(electrons.get(j));
                        }
                        domains[i] = new ElectronDomain(sh, es,dtype);
                    }
                    return domains;
                } else throw new DomainException("Each shell is not contributing the same number of electrons.");
            }
            default -> throw new DomainException("Something went wrong while splitting the domain.");
        }
    }
}

