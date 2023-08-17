package com.example.demo;

import com.example.demo.Database.DataBase;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        DataBase.Conn();
        //DB_Expenses.Conn();
        DataBase.CreateDB_Tra();
        DataBase.CreateDB_Exp();
        //DB_Expenses.CreateDB();
        HelloApplication.main(args);
    }
}