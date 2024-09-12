package com.example.gadgetshop;

import java.io.Serializable;

public class Account{
        String name;
        String password;
        String email;
        int phone;
        public Account(String Name, String Password, String email, int phone) {
            this.name = Name;
            this.password = Password;
            this.email = email;
            this.phone = phone;
        }
}
