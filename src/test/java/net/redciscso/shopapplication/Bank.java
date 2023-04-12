package net.redciscso.shopapplication;

import org.springframework.stereotype.Component;

@Component
public class Bank {
    private Collect collect;
    private Buh buh;
    private Creators creators;

    public Bank(Collect collect, Buh buh, Creators creators) {
        this.collect = collect;
        this.buh = buh;
        this.creators = creators;
    }
}
