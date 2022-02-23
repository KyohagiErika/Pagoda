package com.viewmodel.exception;

public enum PagodaAppError {
    JDBC_DRIVER_CLASS_NOT_FOUND(2, "JDBC Driver class cannot be found!"),
    SQL_CONNECTION_ERROR(3,"Can't connect to SQL Server!");

    private int codeValue;
    private String errMsg;

    public int getCodeValue() {
        return codeValue;
    }
    public String getErrMsg() {
        return errMsg;
    }

    PagodaAppError(int codeValue, String errMsg) {
        this.codeValue = codeValue;
        this.errMsg = errMsg;
    }
}
