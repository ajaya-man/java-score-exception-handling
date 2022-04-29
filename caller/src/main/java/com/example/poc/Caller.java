package com.example.poc;

import score.Address;
import score.Context;
import score.UserRevertException;
import score.UserRevertedException;
import score.annotation.External;

public class Caller {

    public Caller() {

    }

    @External
    public void proxy(Address destination) {
        try {
            Context.call(destination, "increaseCounter");
            throw new CustomException("Hello");
        } catch (CustomException e) {
            throw new UserRevertException("Hello from local");
        }
    }

    @External
    public void proxyCall(Address destination) {
        try {
            Context.call(Context.getAddress(), "proxy", destination);
        } catch (UserRevertedException e) {
            Context.println("Hello from caught revert." + e);
        }
    }

}

class CustomException extends UserRevertException {

    public CustomException(String message) {
        super(message);
    }
}