package com.example.demo;

import com.example.demo.Database.DataBase;
import com.example.demo.parseRepairs.ParseRepairs;
import com.example.demo.parseService.ParseService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {

    int i=0;
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, 1400, 700);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getStyle();
        FileInputStream Url;
        Image url;


        Url = new FileInputStream("png/front.png");
        url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        root.getChildren().add(front);

        Url = new FileInputStream("png/garage.png");
        url = new Image(Url);
        ImageView garage_img = new ImageView(url);
        Button garage = new Button();
        garage.setLayoutX(300);
        garage.setLayoutY(70);
        garage.setGraphic(garage_img);
        garage.setBackground(null);
        root.getChildren().add(garage);

        Url = new FileInputStream("png/add_car.png");
        url = new Image(Url);
        ImageView add_car_img = new ImageView(url);
        Button addCar = new Button();
        addCar.setLayoutX(1100);
        addCar.setLayoutY(300);
        addCar.setGraphic(add_car_img);
        addCar.setBackground(null);

        //ImageView back_car_img = new ImageView(url);
        Button backCar = new Button();
        backCar.setLayoutX(1000);
        backCar.setLayoutY(0);
        backCar.setMinWidth(400);
        backCar.setMinHeight(250);
        backCar.setBackground(null);
        root.getChildren().add(backCar);

        Url = new FileInputStream("png/service.png");
        url = new Image(Url);
        ImageView service_img = new ImageView(url);

        Button service = new Button();
        service.setLayoutX(500);
        service.setLayoutY(70);
        service.setGraphic(service_img);
        service.setBackground(null);
        root.getChildren().add(service);

        Url = new FileInputStream("png/expenses.png");
        url = new Image(Url);
        ImageView expenses_img = new ImageView(url);

        Button expenses = new Button();
        expenses.setLayoutX(700);
        expenses.setLayoutY(70);
        expenses.setGraphic(expenses_img);
        expenses.setBackground(null);
        root.getChildren().add(expenses);

        Url = new FileInputStream("png/repairs.png");
        url = new Image(Url);
        ImageView repairs_img = new ImageView(url);

        Button repairs = new Button();
        repairs.setLayoutX(100);
        repairs.setLayoutY(70);
        repairs.setGraphic(repairs_img);
        repairs.setBackground(null);
        root.getChildren().add(repairs);

        Url = new FileInputStream("png/find.png");
        url = new Image(Url);
        ImageView find_img = new ImageView(url);

        Button find = new Button();
        find.setLayoutX(1050);
        find.setLayoutY(260);
        find.setGraphic(find_img);
        find.setBackground(null);

        TextArea input = new TextArea();
        input.setLayoutX(30);
        input.setLayoutY(260);
        input.setMinWidth(1000);
        input.setMaxHeight(60);

        ScrollPane scroll_garage = new ScrollPane();
        scroll_garage.setLayoutX(30);
        scroll_garage.setLayoutY(250);
        scroll_garage.setMinHeight(0);
        scroll_garage.setMinWidth(1000);
        scroll_garage.setMaxHeight(440);
        scroll_garage.setStyle("-fx-background: transparent; -fx-background-color: #FFffff;");
        scroll_garage.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll_garage.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        ScrollPane scroll_service = new ScrollPane();
        scroll_service.setLayoutX(30);
        scroll_service.setLayoutY(250);
        scroll_service.setMinHeight(0);
        scroll_service.setMinWidth(1000);
        scroll_service.setMaxHeight(440);
        scroll_service.setStyle("-fx-background: transparent; -fx-background-color: #FFffff;");
        scroll_service.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll_service.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        ScrollPane scroll_expenses = new ScrollPane();
        scroll_expenses.setLayoutX(30);
        scroll_expenses.setLayoutY(250);
        scroll_expenses.setMinHeight(0);
        scroll_expenses.setMinWidth(1000);
        scroll_expenses.setMaxHeight(440);
        scroll_expenses.setStyle("-fx-background: transparent; -fx-background-color: #FFffff;");
        scroll_expenses.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll_expenses.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        ScrollPane scroll_repair = new ScrollPane();
        scroll_repair.setLayoutX(30);
        scroll_repair.setLayoutY(350);
        scroll_repair.setMinHeight(0);
        scroll_repair.setMinWidth(1000);
        scroll_repair.setMaxHeight(340);
        scroll_repair.setStyle("-fx-background: transparent; -fx-background-color: #FFffff;");
        scroll_repair.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll_repair.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        garage.setOnAction(value ->{
            i=1;
            //root.getChildren().removeAll(service,repairs,expenses);
            root.getChildren().removeAll(input,scroll_expenses, find, addCar,scroll_repair,scroll_garage,scroll_service);
            root.getChildren().addAll(scroll_garage, addCar);
            Pane p = new Pane();
            try {
                p = DataBase.getAllTransport_Tra(scroll_garage);
            } catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            scroll_garage.setContent(p);
        });

        backCar.setOnAction(value ->{
            root.getChildren().removeAll(input,scroll_expenses, find, addCar,scroll_repair,scroll_garage,scroll_service);
        });

        addCar.setOnAction(value ->{
            Group root_add = new Group();
            Scene scene_add = new Scene(root_add, 400, 600);
            Stage newWindow = new Stage();
            newWindow.initStyle(StageStyle.TRANSPARENT);

            try {
                FileInputStream Url1 = new FileInputStream("png/front3.png");
                Image url1 = new Image(Url1);
                ImageView front1 = new ImageView(url1);
                front1.setX(0);
                front1.setY(0);
                root_add.getChildren().add(front1);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            Text title = new Text("Новый транспорт");
            title.setLayoutX(140);
            title.setLayoutY(20);
            title.setFont(Font.font("Verdana",16));

            Text name_text = new Text("Название машины:");
            name_text.setLayoutX(10);
            name_text.setLayoutY(55);
            name_text.setFont(Font.font("Verdana",13));

            TextArea name = new TextArea();
            name.setPromptText("Название машины:");
            name.setLayoutX(160);
            name.setLayoutY(30);
            name.setMaxHeight(13);
            name.setMaxWidth(200);

            Text number_text = new Text("Гос номер машины:");
            number_text.setLayoutX(10);
            number_text.setLayoutY(105);
            number_text.setFont(Font.font("Verdana",13));

            TextArea number = new TextArea();
            number.setPromptText("Гос номер машины");
            number.setLayoutX(160);
            number.setLayoutY(80);
            number.setMaxHeight(20);
            number.setMaxWidth(200);

            Text vin_text = new Text("VIN номер машины:");
            vin_text.setLayoutX(10);
            vin_text.setLayoutY(155);
            vin_text.setFont(Font.font("Verdana",13));

            TextArea vin = new TextArea();
            vin.setPromptText("VIN номер машины");
            vin.setLayoutX(160);
            vin.setLayoutY(130);
            vin.setMaxHeight(40);
            vin.setMaxWidth(200);

            Text year_text = new Text("Год машины:");
            year_text.setLayoutX(10);
            year_text.setLayoutY(205);
            year_text.setFont(Font.font("Verdana",13));

            TextArea year = new TextArea();
            year.setPromptText("Год машины");
            year.setLayoutX(160);
            year.setLayoutY(180);
            year.setMaxHeight(40);
            year.setMaxWidth(100);

            Text type_text = new Text("Тип транспорта:");
            type_text.setLayoutX(10);
            type_text.setLayoutY(255);
            type_text.setFont(Font.font("Verdana",13));

            ObservableList<String> type = FXCollections.observableArrayList("Легковая", "Мотоцикл",
                    "Грузовая", "Водный транспорт");
            ComboBox<String> comboBox = new ComboBox<String>(type);
            comboBox.setValue("Легковая");
            comboBox.setLayoutX(160);
            comboBox.setLayoutY(235);




            Button close = new Button();
            try {
                FileInputStream Url1 = new FileInputStream("png/del.png");
                Image url1 = new Image(Url1);
                ImageView back = new ImageView(url1);
                close.setGraphic(back);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            close.setBackground(null);
            close.setLayoutX(360);
            close.setLayoutY(5);
            close.setOnAction(e ->{
                newWindow.close();

            });
            root_add.getChildren().addAll(close);

            Button save = new Button("СОХРАНИТЬ");
            save.setLayoutX(170);
            save.setLayoutY(300);

            save.setOnAction(x ->{
                String t1,t2,t3,t4,t5;
                t1 = name.getText();
                t2 = number.getText();
                t3 = vin.getText();
                t4 = year.getText();
                t5 = comboBox.getSelectionModel().getSelectedItem();
                if(t1.isEmpty()||t2.isEmpty()||t3.isEmpty()||!checkYear(t4)){
                    name.setText("Проверьте данные");
                }
                else {
                    if (t5.equals("Легковая"))
                        t5 = "1";
                    else if (t5.equals("Мотоцикл"))
                        t5 = "2";
                    else if (t5.equals("Грузовая"))
                        t5 = "3";
                    else t5 = "4";
                    String k = "";
                    try {
                        k = DataBase.getLastTransport_Tra();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    int e;
                    if (k == null)
                        e = 0;
                    else
                        e = Integer.parseInt(k);
                    e++;
                    k = String.valueOf(e);
                    Pane p;
                    try {
                        DataBase.addTransport_Tra(k, t1, t2, t3, t4, t5);
                        p = DataBase.getAllTransport_Tra(scroll_garage);
                    } catch (SQLException | ClassNotFoundException | FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    root.getChildren().remove(scroll_garage);
                    scroll_garage.setContent(p);
                    root.getChildren().add(scroll_garage);
                    newWindow.close();
                }
            });
            root_add.getChildren().addAll(save,comboBox,type_text,title,name,name_text, year,year_text, vin, vin_text, number_text, number);
            newWindow.setTitle("Project");
            newWindow.setScene(scene_add);
            newWindow.show();
        });

        repairs.setOnAction(value ->{
            i=2;
            //root.getChildren().removeAll(service,garage,expenses);
            //root.getChildren().addAll(input,backCar,find, scroll_repair);
            root.getChildren().removeAll(input,addCar,find, scroll_expenses,scroll_repair,scroll_garage,scroll_service);
            root.getChildren().addAll(input,find,scroll_repair);
            input.setPromptText("Введите наименование или артикул");
        });
        service.setOnAction(value ->{
            i=3;
            //root.getChildren().removeAll(repairs,garage,expenses);
            root.getChildren().removeAll(input,addCar,find, scroll_expenses,scroll_repair,scroll_garage,scroll_service);
           // root.getChildren().addAll(backCar);
            Pane p = new Pane();
            scroll_service.setContent(p);
            ParseService r = null;
            try {
                r = new ParseService("https://fitauto.ru/booking/avtoservis/");
                Pane pane = r.getService(scroll_service);
                scroll_service.setContent(pane);
                root.getChildren().add(scroll_service);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        find.setOnAction(value -> {
            String t = input.getText();
            if(i==2){
                try {
                    ParseRepairs repair = new ParseRepairs("https://www.amag.ru/search/?q=" + t);
                    Pane p = repair.getRepairs();
                    scroll_repair.setContent(p);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        expenses.setOnAction(value ->{
            i = 4;
            Pane pane = new Pane();
            root.getChildren().removeAll(input,addCar, find, scroll_expenses,scroll_repair,scroll_garage,scroll_service);
            //root.getChildren().removeAll(service,garage,repairs);
            root.getChildren().addAll(scroll_expenses);

            try {
                pane = DataBase.getAllExpenses(scroll_expenses);////;lkjhgftdrghjkljhgfdghjkjhgf
                scroll_expenses.setContent(pane);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }


        });
        stage.setTitle("MyCarManager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static boolean checkYear(String year){
        boolean t = year.length() ==4;
        boolean r = true;
        char[] mas = year.toCharArray();
        for(int i=0;i<year.length();i++){
            if(mas[i]<'0' || mas[i]>'9')
                r = false;
        }
        return  t&&r;
    }
}