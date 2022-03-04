package com.viewmodel.exception;

public class PagodaAppException extends Exception {

    public PagodaAppException(PagodaAppError error) {
        super(error.getErrMsg());
    }

}
