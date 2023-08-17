package com.example.demo.parseService;

import com.example.demo.parseRepairs.ModelRepairs;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParseService {
    static String url;
    static Document Doc;
    public ParseService(String url) throws IOException {
        this.url = url;
        this.Doc = Jsoup.connect(url).get();
        //System.out.println(Doc);
    }
    static ModelService[] models = new ModelService[9];///общие услуги
    static ModelService[] model1 = new ModelService[30];///конкретные услуги
    public static Pane getService(ScrollPane scrollPane) throws IOException {
        Pane res = new Pane();
        Elements all = Doc.getElementsByAttributeValue("slot", "item");
        int i = 0;
        for(Element el: all){
            Elements title = el.getElementsByAttributeValue("slot","title");
            if(title.isEmpty())
                continue;
            System.out.println("title: " + title.text());
            Element imageElement = el.select("e-page--booking--subs--item").first();
            String absoluteUrl = imageElement.absUrl("a-bg");
            System.out.println("photo: " + absoluteUrl);

            Element urlElement = el.select("a").first();
            String newUrl = urlElement.absUrl("href");
            System.out.println("newURL: " + newUrl);
            models[i] = new ModelService(title.text(),newUrl,absoluteUrl);
            System.out.println();
            i++;
        }
        res = ModelService.getPane(models, scrollPane);
        return res;
    }

    public static Pane getMoreService() throws IOException {
        Pane res = new Pane();
        Elements all = Doc.getElementsByAttributeValue("slot", "item");
        int i = 0;
        for(Element el: all){
            if(i>29)
                break;
            Elements title = el.getElementsByAttributeValue("slot","title");
            if(title.isEmpty())
                continue;
            System.out.println("title: " + title.text());

            Element imageElement = el.select("e-page--booking--jobs--item").first();
            String absoluteUrl = imageElement.absUrl("a-bg");
            System.out.println("photo: " + absoluteUrl);

            Elements price = el.getElementsByAttributeValue("slot", "price");
            System.out.println("price: " + price.text());

            Element urlElement = el.select("a").first();
            String newUrl = urlElement.absUrl("href");
            System.out.println("newURL: " + newUrl);

            model1[i] = new ModelService(title.text(), price.text(), absoluteUrl,newUrl);
            System.out.println();
            i++;
        }
        res = ModelService.getPane2(model1, i);
        return res;
    }


}


