package com.cortaFila.cortaFila.data.model;

public enum Role {
    ADMIN("admin"),
    USUARIO("user"),
    BARBEIRO("barbeiro");

    private String role;

    Role(String role) {
        this.role = role;
    }
}
