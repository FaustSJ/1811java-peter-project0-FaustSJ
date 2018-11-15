package com.revature.model;
/*
 *     C_ID NUMBER(10,0),
    C_USERNAME VARCHAR2(40),
    C_PASSWORD VARCHAR2(40),
    C_BALANCE NUMBER(8,2),
    CONSTRAINT PK_CUSTOMER PRIMARY KEY (C_ID)
 */
public class Customer {
	private long id;
	private String username;
	private String password;
	private double balance;
}
