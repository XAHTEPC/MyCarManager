package com.example.demo;

import com.example.demo.Database.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class ModelTransport {
    String id;
    String name;
    String number;
    String vin;
    String year;
    String type;

    public ModelTransport(String id, String name, String number, String vin, String year, String type) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.vin = vin;
        this.year = year;
        this.type = type;
    }
    public static Pane printAllTransport (ModelTransport[] model, ScrollPane scrollPane) throws FileNotFoundException {
        Pane pane = new Pane();
        ImageView[] TRANSPORT = new ImageView[model.length];
        Text[] number = new Text[model.length];
        Text[] name = new Text[model.length];
        Text[] vin = new Text[model.length];
        Text[] year = new Text[model.length];
        Text[] type = new Text[model.length];
        int u=10;
        for(int i=0; i< model.length; i++ ) {
            System.out.println(model.length+"modellength");
            if (model[i] == null) {
                System.out.println("i: " + i);
                continue;

            }

            FileInputStream bin = new FileInputStream("png/bin.png");
            ImageView bin_img = new ImageView(new Image(bin));
            Button del = new Button();
            del.setGraphic(bin_img);
            del.setBackground(null);

            FileInputStream pen = new FileInputStream("png/pen.png");
            ImageView pen_img = new ImageView(new Image(pen));
            Button edit = new Button();
            edit.setGraphic(pen_img);
            edit.setBackground(null);

            number[i] = new Text();
            number[i].setText("Гос. номер: " + model[i].number);
            number[i].setFont(Font.font("VERDANA", 14));

            name[i] = new Text();
            name[i].setText("Название: " + model[i].name);
            name[i].setFont(Font.font("VERDANA", 14));

            vin[i] = new Text();
            vin[i].setText("VIN номер: " + model[i].vin);
            vin[i].setFont(Font.font("VERDANA", 14));

            year[i] = new Text();
            year[i].setText("Год: " + model[i].year);
            year[i].setFont(Font.font("VERDANA", 14));

            type[i] = new Text();
            String t = "";
            if (model[i].type.equals("1")) {
                t = "Легковая";
                FileInputStream CAR = new FileInputStream("png/car.png");
                TRANSPORT[i] = new ImageView(new Image(CAR));
            } else if (model[i].type.equals("2")) {
                t = "Мотоцикл";
                FileInputStream MOTO = new FileInputStream("png/moto.png");
                TRANSPORT[i] = new ImageView(new Image(MOTO));
            } else if (model[i].type.equals("3")) {
                t = "Грузовая";
                FileInputStream LORRY = new FileInputStream("png/lorry.png");
                TRANSPORT[i] = new ImageView(new Image(LORRY));
            } else {
                t = "Водный транспорт";
                FileInputStream SAIL = new FileInputStream("png/sail.png");
                TRANSPORT[i] = new ImageView(new Image(SAIL));
            }

            type[i].setText("Тип: " + t);
            type[i].setFont(Font.font("VERDANA", 14));

            if (i % 2 == 0) {
                name[i].setLayoutX(180);
                name[i].setLayoutY(u + 10);
                number[i].setLayoutX(180);
                number[i].setLayoutY(u + 30);
                vin[i].setLayoutX(180);
                vin[i].setLayoutY(u + 50);
                year[i].setLayoutX(180);
                year[i].setLayoutY(u + 70);
                type[i].setLayoutX(180);
                type[i].setLayoutY(u + 90);
                edit.setLayoutX(180);
                edit.setLayoutY(u + 95);
                del.setLayoutX(210);
                del.setLayoutY(u + 95);
                TRANSPORT[i].setY(u);
                TRANSPORT[i].setX(30);
                System.out.println("U:" + u);
            } else {
                name[i].setLayoutX(730);
                name[i].setLayoutY(u + 10);
                number[i].setLayoutX(730);
                number[i].setLayoutY(u + 30);
                vin[i].setLayoutX(730);
                vin[i].setLayoutY(u + 50);
                year[i].setLayoutX(730);
                year[i].setLayoutY(u + 70);
                type[i].setLayoutX(730);
                type[i].setLayoutY(u + 90);
                edit.setLayoutX(730);
                edit.setLayoutY(u + 95);
                del.setLayoutX(760);
                del.setLayoutY(u + 95);
                TRANSPORT[i].setY(u);
                TRANSPORT[i].setX(580);
                u += 150;
            }
            final int q = Integer.parseInt(model[i].id);
            edit.setOnAction(value -> {
                try {
                    change(q, scrollPane);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException | FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            del.setOnAction(value -> {
                String e = String.valueOf(q);
                try {
                    DataBase.deleteTransport_Tra(e, scrollPane);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            pane.getChildren().addAll(TRANSPORT[i], number[i], name[i], vin[i], year[i], type[i], edit, del);
        }


        return pane;
    }

    public static void change(int i, ScrollPane scrollPane) throws SQLException, ClassNotFoundException, FileNotFoundException {
        String k = String.valueOf(i);
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

        Text title = new Text("Редактирование транспорта");
        title.setLayoutX(100);
        title.setLayoutY(20);
        title.setFont(Font.font("Verdana",16));

        Text name_text = new Text("Название машины:");
        name_text.setLayoutX(10);
        name_text.setLayoutY(55);
        name_text.setFont(Font.font("Verdana",13));

        TextArea name = new TextArea();
        name.setText(editModel.name);
        name.setLayoutX(160);
        name.setLayoutY(30);
        name.setMaxHeight(13);
        name.setMaxWidth(200);

        Text number_text = new Text("Гос номер машины:");
        number_text.setLayoutX(10);
        number_text.setLayoutY(105);
        number_text.setFont(Font.font("Verdana",13));

        TextArea number = new TextArea();
        number.setText(editModel.number);
        number.setLayoutX(160);
        number.setLayoutY(80);
        number.setMaxHeight(20);
        number.setMaxWidth(200);

        Text vin_text = new Text("VIN номер машины:");
        vin_text.setLayoutX(10);
        vin_text.setLayoutY(155);
        vin_text.setFont(Font.font("Verdana",13));

        TextArea vin = new TextArea();
        vin.setText(editModel.vin);
        vin.setLayoutX(160);
        vin.setLayoutY(130);
        vin.setMaxHeight(40);
        vin.setMaxWidth(200);

        Text year_text = new Text("Год машины:");
        year_text.setLayoutX(10);
        year_text.setLayoutY(205);
        year_text.setFont(Font.font("Verdana",13));

        TextArea year = new TextArea();
        year.setText(editModel.year);
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
        if(editModel.type.equals("1"))
            comboBox.setValue("Легковая");
        else if(editModel.type.equals("2"))
            comboBox.setValue("Мотоцикл");
        else if(editModel.type.equals("3"))
            comboBox.setValue("Грузовая");
        else comboBox.setValue("Водный транспорт");
        comboBox.setLayoutX(160);
        comboBox.setLayoutY(235);

        Button save = new Button("СОХРАНИТЬ");
        save.setLayoutX(170);
        save.setLayoutY(300);

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
        root_add.getChildren().addAll(close);

        save.setOnAction(x ->{
            String t1,t2,t3,t4,t5;
            t1 = name.getText();
            t2 = number.getText();
            t3 = vin.getText();
            t4 = year.getText();
            t5 = comboBox.getSelectionModel().getSelectedItem();
            if(t1.isEmpty()||t2.isEmpty()||t3.isEmpty()||!checkYear(t4))
                name.setText("Проверьте данные");
            else {
                if (t5.equals("Легковая"))
                    t5 = "1";
                else if (t5.equals("Мотоцикл"))
                    t5 = "2";
                else if (t5.equals("Грузовая"))
                    t5 = "3";
                else t5 = "4";

                try {
                    DataBase.UpdateTransport_Tra(k, t1, t2, t3, t4, t5);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Pane p;
                try {
                    p = DataBase.getAllTransport_Tra(scrollPane);
                } catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                newWindow.close();
                scrollPane.setContent(p);
            }
        });
        root_add.getChildren().addAll(save,comboBox,type_text,title,name,name_text, year,year_text, vin, vin_text, number_text, number);
        newWindow.setTitle("Project");
        newWindow.setScene(scene_add);
        newWindow.show();

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
