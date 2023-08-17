package com.example.demo.Database;

import com.example.demo.ModelExpenses;
import com.example.demo.ModelTransport;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.sql.*;

public class DataBase {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;
    public static ResultSet resSet1;
    public static ResultSet resSet2;

    public static void Conn() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:Store.db");
        System.out.println("База Подключена!");
        statmt = conn.createStatement();
    }
    public static void CreateDB_Exp() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists Expenses (\n" +
                "    id INTEGER PRIMARY KEY,\n" +
                "    id_transport         INTEGER,\n" +
                "    date       TEXT,\n" +
                "    description          TEXT,\n" +
                "    price         INTEGER\n" +
                ");");

        System.out.println("Таблица создана или уже существует.");
    }

    public  static String getAll_Exp(String id) throws SQLException {
        statmt = conn.createStatement();
        resSet1 = statmt.executeQuery("SELECT sum(price) FROM Expenses where id_transport =" + id + " group by id_transport;");
        if(resSet1 == null){
            System.out.println("Nuuuuul");
            return "";
        }
        String t = resSet1.getString("sum(price)");
        return t;
    }

    public static void deleteExpenses_Exp(String t1) throws SQLException {
        statmt.execute(" DELETE FROM 'Expenses' WHERE id_transport ='" + t1 +"';");

    }
    public  static String getCount_byID(String id) throws SQLException {
        statmt = conn.createStatement();
        resSet2= statmt.executeQuery("SELECT count(price) FROM Expenses where id_transport =" + id + " group by id_transport;");
        if(resSet2 == null){
            System.out.println("Nuuuuul");
            return "1";
        }
        String t = resSet2.getString("count(price)");
        return t;
    }

    public  static String[][] getAll_byID(String id) throws SQLException {
        statmt = conn.createStatement();
        String kol = getCount_byID(id);
        if(kol == null)
            kol = "0";
        int k = Integer.parseInt(kol);
        String[][] mas = new String[4][k];
        resSet2 = statmt.executeQuery("SELECT * FROM Expenses where id_transport =" + id + ";");
        int i =0;
        while (resSet2.next()) {
            String t1;
            String t2 = resSet2.getString("date");
            String t3 = resSet2.getString("description");
            String t4 = resSet2.getString("price");
            int y= i+1;
            t1 = String.valueOf(y);
            mas[0][i] = t1;
            mas[1][i] = t2;
            mas[2][i] = t3;
            mas[3][i] = t4;
            i++;
        }
        return mas;
    }

    public static void addExpenses_Exp(String t1, String t2, String t3, String t4, String t5) throws SQLException {
        statmt.execute("INSERT INTO 'Expenses' ('id', 'id_transport', 'date', 'description', 'price') " +
                "VALUES ("+ t1+", " + t2 + ", '" + t3 + "', '" +
                t4 + "', " + t5 + ");");

    }

    public static String getLastIDExpenses_Exp() throws SQLException, ClassNotFoundException {
        statmt = conn.createStatement();
        resSet = statmt.executeQuery("SELECT max(id)+1 FROM Expenses");
        if(resSet == null)
            return "0";
        String t = resSet.getString("max(id)+1");
        return t;
    }

    public static void CreateDB_Tra() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists Transport (\n" +
                "    id_transport INTEGER PRIMARY KEY,\n" +
                "    name         TEXT,\n" +
                "    number       TEXT,\n" +
                "    vin          TEXT,\n" +
                "    year         INTEGER,\n" +
                "    type         INTEGER\n" +
                ");");

        System.out.println("Таблица создана или уже существует.");
    }

    public static ModelTransport getModel_Tra(String t) throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        resSet = statmt.executeQuery("SELECT * FROM Transport WHERE id_transport ='" + t + "';");
        System.out.println(t);

        String t2 = resSet.getString("number");
        String t1 = resSet.getString("name");
        String t3= resSet.getString("vin");
        String t5= resSet.getString("type");
        String t4 = resSet.getString("year");
        ModelTransport transport = new ModelTransport(t,t1,t2,t3,t4,t5);
        return transport;
    }

    public static String getLastTransport_Tra() throws SQLException, ClassNotFoundException {
        statmt = conn.createStatement();
        resSet = statmt.executeQuery("SELECT max(id_transport) FROM Transport");
        String t = resSet.getString("max(id_transport)");
        return t;
    }

    public static Pane getAllTransport_Tra(ScrollPane scrollPane) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Pane res = new Pane();
        statmt = conn.createStatement();
        String kol = getLastTransport_Tra();
        System.out.println(kol);
        if(kol == null)
            return res;
        else {
            int k = Integer.parseInt(kol);
            ModelTransport[] model = new ModelTransport[k];
            int i = 0;
            resSet = statmt.executeQuery("SELECT * \n" +
                    "FROM Transport;");
            while (resSet.next()) {
                String t1 = resSet.getString("id_transport");
                String t2 = resSet.getString("name");
                String t3 = resSet.getString("number");
                String t4 = resSet.getString("vin");
                String t5 = resSet.getString("year");
                String t6 = resSet.getString("type");
                model[i] = new ModelTransport(t1, t2, t3, t4, t5, t6);
                System.out.println(t1);
                System.out.println(t2);
                System.out.println(t3);
                System.out.println(t4);
                System.out.println(t5);
                System.out.println(t6);
                i++;
            }
            res = ModelTransport.printAllTransport(model, scrollPane);
            return res;
        }
    }

    public static void addTransport_Tra(String t1, String t2, String t3, String t4, String t5, String t6) throws SQLException {
        statmt.execute("INSERT INTO 'Transport' ('id_transport', 'name', 'number', 'vin', 'year', " +
                "'type') VALUES ('"+ t1+"', '" + t2 + "', '" + t3 + "', '" +
                t4 + "', " + t5 + ", " + t6 + ");");
    }

    public static void UpdateTransport_Tra(String t1, String t2, String t3, String t4,
                                       String t5, String t6) throws SQLException {
        statmt.execute("UPDATE 'Transport'" +
                "SET  ('name', 'number', 'vin', 'year', 'type') " +
                "= ( '" + t2 + "', '" + t3 + "', '" +  t4 + "', " + t5 + ", " + t6 + ") " +
                "WHERE id_transport ='" + t1 +"';");
    }

    public static void deleteTransport_Tra(String t1, ScrollPane scrollPane) throws SQLException {
        statmt.execute(" DELETE FROM 'Transport' WHERE id_transport ='" + t1 +"';");
        Pane p;
        try {
            p = DataBase.getAllTransport_Tra(scrollPane);
        } catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        deleteExpenses_Exp(t1);
        scrollPane.setContent(p);
    }

    public static Pane getAllExpenses(ScrollPane scrollPane) throws SQLException, ClassNotFoundException, FileNotFoundException {
        Pane res = new Pane();
        statmt = conn.createStatement();
        String kol = getLastTransport_Tra();
        System.out.println("kol"+ kol);
        if(kol == null)
            return res;
        else {
            int k = Integer.parseInt(kol);
            ModelExpenses[] model = new ModelExpenses[k];
            int i = 0;
            resSet = statmt.executeQuery("SELECT * \n" +
                    "FROM Transport;");
            while (resSet.next()) {
                String t1 = resSet.getString("id_transport");
                String t2 = resSet.getString("name");
                String t3 = resSet.getString("type");
                model[i] = new ModelExpenses(t1, t2, t3);
                i++;
            }
            System.out.println("-");
            res = ModelExpenses.printAll(model, scrollPane);

            return res;
        }
    }

    public static void CloseDB() throws ClassNotFoundException, SQLException {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }
}
