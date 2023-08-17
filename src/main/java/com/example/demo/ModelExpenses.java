package com.example.demo;

import com.example.demo.Database.DataBase;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelExpenses {
    String id;
    String name;
    FileInputStream print;

    String price;

    public ModelExpenses(String id, String name, String type) throws FileNotFoundException, SQLException {
        this.id = id;
        this.name = name;
        if (type.equals("1")) {
            this.print = new FileInputStream("png/car.png");
        } else if (type.equals("2")) {
            this.print = new FileInputStream("png/moto.png");
        } else if (type.equals("3")) {
            this.print = new FileInputStream("png/lorry.png");
        } else {
            this.print = new FileInputStream("png/sail.png");
        }
        System.out.println("id: "+id + "fkkj: " +this.id);
        if(!id.isEmpty()) {
//            int r = Integer.getInteger(id);
//            r++;
//            this.id = String.valueOf(r);
            this.price = DataBase.getAll_Exp(id);
            if(this.price == null)
                this.price = "0";
            System.out.println("priceL " + price);
        }
        else
            this.price = "0";
    }

    public static Pane printAll(ModelExpenses[] models, ScrollPane scrollPane){
        Pane res = new Pane();
        int u = 10;
        for(int i=0; i<models.length;i++, u+=200){
            if(models[i] == null)
                continue;
            ImageView photo = new ImageView(new Image(models[i].print));
            photo.setX(10);
            photo.setY(10 + u);

            Text name = new Text();
            name.setFont(Font.font("VERDANA", 14));
            name.setText("Название: " + models[i].name);
            name.setLayoutX(170);
            name.setLayoutY(20 + u);
            name.maxHeight(50);
            name.maxWidth(100);

            Text price = new Text();
            price.setFont(Font.font("VERDANA", 14));
            price.setText("Общий расход: " + models[i].price);
            price.setLayoutX(170);
            price.setLayoutY(120+u);
            price.maxWidth(100);
            price.maxHeight(30);

            Button add = new Button();
            add.setText("Добавить чек");
            add.setFont(Font.font("VERDANA", 14));
            add.setLayoutX(620);
            add.setLayoutY(30 + u);

            String id = models[i].id;


            Button show = new Button();
            show.setText("Показать все расходы");
            show.setFont(Font.font("VERDANA", 14));
            show.setLayoutX(620);
            show.setLayoutY(60 + u);


            add.setOnAction(value -> {
                try {
                    add(id, scrollPane);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            show.setOnAction(value -> {
                try {
                    check(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

            res.getChildren().addAll(photo,name,price,add,show);
        }
        return res;
    }

    public static void add(String k, ScrollPane scrollPane) throws SQLException, ClassNotFoundException, FileNotFoundException {
        ModelTransport editModel = DataBase.getModel_Tra(k);
        Group root_add = new Group();
        Scene scene_add = new Scene(root_add, 400, 600);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.TRANSPARENT);

        FileInputStream Url = new FileInputStream("png/front3.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        root_add.getChildren().add(front);

        Text title = new Text("Новый чек");
        title.setLayoutX(150);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana",16));

        Text car_text = new Text("Название машины:");
        car_text.setLayoutX(10);
        car_text.setLayoutY(55);
        car_text.setFont(Font.font("Verdana",13));

        TextArea car = new TextArea();
        car.setText(editModel.name);
        car.setLayoutX(160);
        car.setLayoutY(30);
        car.setEditable(false);
        car.setMaxHeight(13);
        car.setMaxWidth(200);

        Text check_text = new Text("Описание вложения:");
        check_text.setLayoutX(10);
        check_text.setLayoutY(105);
        check_text.setFont(Font.font("Verdana",13));

        TextArea check = new TextArea();
        check.setPromptText("Описание вложения");
        check.setLayoutX(160);
        check.setLayoutY(80);
        check.setMaxHeight(20);
        check.setMaxWidth(200);

        Text date_text = new Text("Дата");
        date_text.setLayoutX(10);
        date_text.setLayoutY(155);
        date_text.setFont(Font.font("Verdana",13));

        TextArea DATA = new TextArea();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String string = formatter.format(date);
        DATA.setText(string);
        DATA.setLayoutX(160);
        DATA.setLayoutY(130);
        DATA.setMaxHeight(40);
        DATA.setMaxWidth(200);

        Text price_text = new Text("Цена:");
        price_text.setLayoutX(10);
        price_text.setLayoutY(205);
        price_text.setFont(Font.font("Verdana",13));

        TextArea price = new TextArea();
        price.setLayoutX(160);
        price.setLayoutY(180);
        price.setMaxHeight(40);
        price.setMaxWidth(100);

        Url = new FileInputStream("png/del.png");
        url = new Image(Url);
        ImageView back = new ImageView(url);

        Button close = new Button();
        close.setGraphic(back);
        close.setBackground(null);
        close.setLayoutX(360);
        close.setLayoutY(5);
        close.setOnAction(value ->{
            newWindow.close();

        });
        Button save = new Button("СОХРАНИТЬ");
        save.setLayoutX(170);
        save.setLayoutY(300);
        save.setOnAction(value ->{
            String t1,t2,t3,t4,t5;
            try {
               t1 = DataBase.getLastIDExpenses_Exp();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            t2 = String.valueOf(k);
            t3 = DATA.getText();
            t4 = check.getText();
            t5 = price.getText();
            if(checkDate(t3)&&checkPrice(t5)&&!t5.isEmpty()) {
                try {
                    DataBase.addExpenses_Exp(t1, t2, t3, t4, t5);
                    System.out.println("OK");
                    scrollPane.setContent(DataBase.getAllExpenses(scrollPane));
                    newWindow.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                car.setText("Проверьте данные");
            }
        });

        root_add.getChildren().addAll(save,price,price_text,title,DATA,check,date_text, check_text, car_text, car, close);
        newWindow.setTitle("Project");
        newWindow.setScene(scene_add);
        newWindow.show();
    }

    public static void check(String k) throws SQLException, ClassNotFoundException, FileNotFoundException {
        String q = String.valueOf(k);
        ModelTransport editModel = DataBase.getModel_Tra(q);
        String[][] mas = DataBase.getAll_byID(q);
        Group root_add = new Group();
        Scene scene_add = new Scene(root_add, 600, 400);
        Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.TRANSPARENT);

        FileInputStream Url = new FileInputStream("png/front2.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        root_add.getChildren().add(front);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(130);
        scrollPane.setMinWidth(580);
        scrollPane.setMinHeight(10);
        scrollPane.setMaxHeight(390);
        scrollPane.setMaxWidth(590);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Text title = new Text("Все расходы");
        title.setLayoutX(250);
        title.setLayoutY(30);
        title.setFont(Font.font("Verdana",16));

        Text car_text = new Text("Название машины: " + editModel.name);
        car_text.setLayoutX(10);
        car_text.setLayoutY(55);
        car_text.setFont(Font.font("Verdana",13));

        Text num = new Text("#");
        num.setLayoutX(30);
        num.setLayoutY(100);
        num.setFont(Font.font("Verdana",13));

        Text date = new Text("Дата:");
        date.setLayoutX(70);
        date.setLayoutY(100);
        date.setFont(Font.font("Verdana",13));

        Text description = new Text("Описание:");
        description.setLayoutX(170);
        description.setLayoutY(100);
        description.setFont(Font.font("Verdana",13));

        Text price = new Text("Цена:");
        price.setLayoutX(500);
        price.setLayoutY(100);
        price.setFont(Font.font("Verdana",13));

        Pane pane = new Pane();
        int u = 10;
        for(int i =0; i<mas[i].length; i++, u+=50){

            TextArea num_text = new TextArea();
            num_text.setEditable(false);
            num_text.setText(mas[0][i]);
            num_text.setLayoutX(0);
            num_text.setLayoutY(0 + u);
            num_text.setMaxHeight(40);
            num_text.setMaxWidth(40);
            num_text.setMinHeight(40);
            num_text.setMinWidth(40);

            TextArea DATA = new TextArea();
            DATA.setText(mas[1][i]);
            DATA.setEditable(false);
            DATA.setLayoutX(60);
            DATA.setLayoutY(0 + u);
            DATA.setMaxHeight(40);
            DATA.setMaxWidth(80);
            DATA.setMinHeight(40);
            DATA.setMinWidth(80);

            TextArea description_text = new TextArea();
            description_text.setText(mas[2][i]);
            description_text.setEditable(false);
            description_text.setLayoutX(160);
            description_text.setLayoutY(0 + u);
            description_text.setMaxHeight(40);
            description_text.setMaxWidth(310);
            description_text.setMinWidth(310);

            TextArea price_text = new TextArea();
            price_text.setText(mas[3][i]);
            price_text.setEditable(false);
            price_text.setLayoutX(480);
            price_text.setLayoutY(0 + u);
            price_text.setMaxHeight(40);
            price_text.setMaxWidth(100);

            pane.getChildren().addAll(description_text, price_text, DATA, num_text);
            System.out.println(i);
        }

        scrollPane.setContent(pane);

        Url = new FileInputStream("png/del.png");
        url = new Image(Url);
        ImageView back = new ImageView(url);

        Button close = new Button();
        close.setGraphic(back);
        close.setBackground(null);
        close.setLayoutX(550);
        close.setLayoutY(10);
        close.setOnAction(value ->{
                newWindow.close();

        });
        root_add.getChildren().addAll(close,price,scrollPane,title, num, description,date, car_text);
        newWindow.setTitle("Расходы");
        newWindow.setScene(scene_add);
        newWindow.show();
    }

    public  static boolean checkPrice(String price){
        char[] mas = price.toCharArray();
        boolean fl = true;
        for(int i=0;i<mas.length;i++){
            if(mas[i]<'0'||mas[i]>'9')
                fl= false;
        }
        return fl;

    }
    public  static boolean checkDate(String date){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date.trim());
            } catch (ParseException pe) {
                System.out.println("date error");
                return false;
            }
            return true;
    }
}
