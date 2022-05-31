package net.redciscso.shopapplication;

public class Man extends Primat{
    private Woman woman;

    public Man(Woman woman) {
        this.woman = woman;
    }


    @Override
    public void go() {
        System.out.println("Man go");
    }
}
