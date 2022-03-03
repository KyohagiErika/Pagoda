package com.viewmodel.exception;

public enum PagodaAppError {
    JDBC_DRIVER_CLASS_NOT_FOUND( "JDBC Driver class cannot be found!"),
    SQL_CONNECTION_ERROR("Can't connect to SQL Server!");

    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    PagodaAppError(String errMsg) {
        this.errMsg = errMsg;
    }
}
