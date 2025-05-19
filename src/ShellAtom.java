package src;

import java.util.ArrayList;

public class ShellAtom implements Atom{

    @Override
    public void setup(int[][] orbitals) {

    }

    @Override
    public void fill(int electronNumber) {

    }

    @Override
    public void ionise(int electronLoss) {

    }

    @Override
    public void bond(Atom other, int bondOrder, boolean needToRecur) throws CovalentBondException {

    }

    @Override
    public int[] getValenceShell() {
        return new int[0];
    }

    @Override
    public int[] getMaxValenceShell() {
        return new int[0];
    }

    @Override
    public void addElectronsTo(int[] shell, int[] maxCapacity, int electronsToAdd) {

    }

    @Override
    public int getMaxCapacityValence() {
        return 0;
    }

    @Override
    public int getNeutronNumber() {
        return 0;
    }

    @Override
    public int getAtomicNumber() {
        return 0;
    }

    @Override
    public ArrayList<Atom> getBondedTo() {
        return null;
    }

    @Override
    public Atom alphaDecay() {
        return null;
    }

    @Override
    public void betaDecay(boolean isPositive) {

    }
}
