package com.example.demo.parseRepairs;
import javafx.scene.layout.Pane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParseRepairs {

        static String url;
        static Document Doc;
        public ParseRepairs(String url) throws IOException {
            this.url = url;
            this.Doc = Jsoup.connect(url).get();
            //System.out.println(Doc);
        }
        static ModelRepairs[] models = new ModelRepairs[20];

        public static Pane getRepairs() throws IOException {
            Pane res = new Pane();
            Elements all = Doc.getElementsByAttributeValue("class", "col-xxx");
            int i = 0;
            for(Element el: all){
                String buyURL = "";
                Elements title = el.getElementsByAttributeValue("class", "item-v-name");
                Elements price = el.getElementsByAttributeValue("class", "price");
                Elements path = el.getElementsByAttributeValue("class", "item_img");
                Element imageElement = path.select("img").first();
                String absoluteUrl = imageElement.absUrl("src");
                Elements status = el.getElementsByAttributeValue("class", "visible-sm visible-md visible-lg");
                String st = "";
                if(status.text().isEmpty()){
                    st = "Нет в наличии";

                }
                else{
                    st = status.text();
                    Elements buy_url = el.getElementsByAttributeValue("class", "003 catalog-vitrina-3 fitem    \t\t\t\t");
                    Elements buy_url2 = el.getElementsByAttributeValue("class", "003 catalog-vitrina-3 fitem    \twith_old_price\t\t\t");
                    Element buyElement = buy_url.select("a").first();
                    Element buyElement2 = buy_url2.select("a").first();
                    if(buyElement == null){
                        buyURL = buyElement2.absUrl("href");
                    }
                    else
                        buyURL = buyElement.absUrl("href");


//                System.out.println(buyURL);
                }
//            System.out.println("name: " + title.text());
//            System.out.println("price: " + price.text());
          System.out.println("path: " + absoluteUrl);
//            System.out.println("status: " + st + "\n\n");

                models[i] = new ModelRepairs(title.text(), absoluteUrl, price.text(), st, buyURL);
                i++;
            }
            res = ModelRepairs.getPane(models, i);
            return res;

        }
}
