package com.viewmodel.exception;

public class PagodaAppException extends Exception {
    private int exitCode;

    public int getExitCode() {
        return exitCode;
    }

    public PagodaAppException(PagodaAppError error) {
        super(error.getErrMsg());
        exitCode = error.getCodeValue();
    }
}
