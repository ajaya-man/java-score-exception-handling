package com.example.receiver;

import score.Context;
import score.UserRevertException;
import score.VarDB;
import score.annotation.External;

import java.math.BigInteger;

public class Receiver {

    private static final VarDB<BigInteger> counter = Context.newVarDB("counter", BigInteger.class);

    public Receiver() {

    }

    @External
    public void increaseCounter() {
        counter.set(counter.getOrDefault(BigInteger.ZERO).add(BigInteger.ONE));
        throw new UserRevertException("From receiver");
    }

    @External(readonly = true)
    public BigInteger getCounter() {
        return counter.getOrDefault(BigInteger.ZERO);
    }
}
