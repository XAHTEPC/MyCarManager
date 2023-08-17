package com.example.demo.parseService;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ModelService {
    String title;
    String url;
    String photo;
    String name;
    String price;
    String url_photo;
    String buy;

    public ModelService(String title, String url, String photo) {
        this.title = title;
        this.url = url;
        this.photo = photo;
    }

    public ModelService(String name, String price, String url_photo, String buy) {
        this.name = name;
        this.price = price;
        this.url_photo = url_photo;
        this.buy = buy;
    }
static Pane res = new Pane();
    public static Pane getPane(ModelService[] models, ScrollPane scrollPane) {
        res = new Pane();
        int u = 10;
        for (int i = 0; i < 8; i++, u += 200) {
            InputStream is = null;
            ImageView photo = new ImageView();
            //System.out.println(models[1].photo);
            String fileUrl = models[i].photo;
            //System.out.println("fileURL^: " + fileUrl);
            try {
                URL url = new URL(fileUrl);
                URLConnection connection = url.openConnection();
                is = connection.getInputStream();
                photo = new ImageView(new Image(is));
            } catch (IOException e) {
                e.printStackTrace();
            }
            photo.setX(10);
            photo.setY(10 + u);
            photo.setFitHeight(150);
            photo.setFitWidth(200);

            Text name = new Text();
            name.setText(models[i].title);
            name.setLayoutX(200);
            name.setLayoutY(70 + u);
            name.maxHeight(60);
            name.maxWidth(200);

            Button buy = new Button();
            buy.setText("Подробнее");
            buy.setLayoutX(700);
            buy.setLayoutY(50 + u);
            final int r = i;
            buy.setOnAction(value -> {
                try {
                    URL url = new URL(models[r].url);
                    res = new Pane();
                    ParseService parseService = new ParseService(models[r].url);
                    res = parseService.getMoreService();
                    scrollPane.setContent(res);

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
            res.getChildren().addAll(photo, buy, name);
        }
        return res;
    }
    public static Pane getPane2(ModelService[] models, int t) {

        int u = 10;
        for (int i = 0; i < t; i++, u += 200) {
            InputStream is = null;
            ImageView photo = new ImageView();
            //System.out.println(models[1].photo);
            String fileUrl = models[i].url_photo;
            //System.out.println("fileURL^: " + fileUrl);
            try {
                URL url = new URL(fileUrl);
                URLConnection connection = url.openConnection();
                is = connection.getInputStream();
                photo = new ImageView(new Image(is));
            } catch (IOException e) {
                e.printStackTrace();
            }
            photo.setX(10);
            photo.setY(10 + u);
            photo.setFitHeight(150);
            photo.setFitWidth(200);

            Text name = new Text();
            name.setText(models[i].name);
            name.setLayoutX(200);
            name.setLayoutY(10+u);
            name.maxHeight(60);
            name.maxWidth(200);

            Text price = new Text();
            price.setText("Цена: " + models[i].price);
            price.setLayoutX(200);
            price.setLayoutY(70+u);
            price.maxWidth(100);
            price.maxHeight(30);

            Button buy = new Button();
            buy.setText("Записаться");
            buy.setLayoutX(820);
            buy.setLayoutY(50 + u);
            final int r = i;
            buy.setOnAction(value ->{
                try {
                    openWebpage(models[r].buy);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
            res.getChildren().addAll(photo, buy, name, price);
        }
        return res;
    }
    public static void openWebpage(String urlString) throws IOException {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(urlString));
    }
}
