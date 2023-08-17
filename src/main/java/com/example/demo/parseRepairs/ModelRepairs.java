package com.example.demo.parseRepairs;
import com.github.axet.desktop.Desktop;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class ModelRepairs {
    String name;
    String path;
    String price;
    String status;
    static FileInputStream url;
    String buy_url;
    public ModelRepairs(String name, String path, String price, String status, String buy_url) throws FileNotFoundException {
        this.name = name;
        this.path = path;
        //this.url = new FileInputStream(path);
        this.price = price;
        this.status = status;
        this.buy_url = buy_url;
    }
    public static Pane getPane (ModelRepairs[] models, int t) throws FileNotFoundException, MalformedURLException {
        Pane res = new Pane();
        int u = 10;
        for(int i = 0; i<t; i++, u+=200){
            //System.out.println(models[i].path);
            InputStream is = null;
            ImageView photo = new ImageView();
            String fileUrl = models[i].path;
            try {
                // create a url object
                URL url = new URL(fileUrl);
                // connection to the file
                URLConnection connection = url.openConnection();
                is = connection.getInputStream();
                photo = new ImageView(new Image(is));
            } catch (IOException e) {
            e.printStackTrace();
        }
            photo.setX(10);
            photo.setY(10 + u);

            Text name = new Text();
            name.setFont(Font.font("VERDANA", 14));
            name.setText(printString(models[i].name));
            name.setLayoutX(170);
            name.setLayoutY(20+u);
            name.maxHeight(60);
            name.minWidth(200);
            name.maxWidth(500);

            Text price = new Text();
            price.setFont(Font.font("VERDANA", 14));
            price.setText("Цена: " + models[i].price);
            price.setLayoutX(170);
            price.setLayoutY(120+u);
            price.maxWidth(100);
            price.maxHeight(30);

            Text status = new Text();
            status.setText(models[i].status);
            status.setLayoutX(710);
            status.setLayoutY(70 + u);
            status.maxWidth(100);
            status.maxHeight(50);
            status.setFont(Font.font("VERDANA", 14));

            Button buy = new Button();
            buy.setText("Купить");
            buy.setFont(Font.font("VERDANA", 14));
            buy.setLayoutX(820);
            buy.setLayoutY(50 + u);
            final int r = i;
            buy.setOnAction(value ->{
                try {
                    URL url = new URL(models[r].buy_url);
                    openWebpage(models[r].buy_url);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

            if(models[i].status.equals("Есть в наличии"))
                res.getChildren().addAll(buy,status,price,name, photo);
            else
                res.getChildren().addAll(status,price,name, photo);

        }
            return res;
    }
    public static void openWebpage(String urlString) throws IOException {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(urlString));
    }

    public static String printString(String s){
        String res = "";
        char[] mas = s.toCharArray();
        for(int i = 0; i< mas.length; i++){
            char c;
            if(i%50 ==0){
                c = '\n';
                res +=c;
            }
            c = mas[i];
            res +=c;
        }
        return res;
    }

}
