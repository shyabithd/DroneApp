package com.transport.utils;

public enum PackageStatus {
    Inprogress("InProgress"),
    Completed("Completed");

    private String status;

    private PackageStatus(String status) {
        this.status = status;
    }
}
