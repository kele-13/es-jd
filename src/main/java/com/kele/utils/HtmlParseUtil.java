package com.kele.utils;

import com.kele.pojo.Contents;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUtil {

//    public static void main(String[] args) throws Exception {
//        new HtmlParseUtil().parseJD("vue").forEach(System.out::println);
//    }

    public List<Contents> parseJD(String keywords) throws Exception{
        String url = "https://search.jd.com/Search?keyword=" + keywords;

        Document document = Jsoup.parse(new URL(url), 3000);
        Element element = document.getElementById("J_goodsList");
        //System.out.println(element.html());

        ArrayList<Contents> goodsList = new ArrayList<>();
        Elements elements = element.getElementsByTag("li");
        for (Element el : elements) {
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            //System.out.println("----------------");
//            System.out.println(img);
//            System.out.println(price);
//            System.out.println(title);

            Contents contents = new Contents();
            contents.setImg(img);
            contents.setPrice(price);
            contents.setTitle(title);
            goodsList.add(contents);
        }
        return goodsList;
    }
}
