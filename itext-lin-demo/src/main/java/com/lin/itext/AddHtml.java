package com.lin.itext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

public class AddHtml {
    private static final String ORIG = "C:\\Users\\zeyu.lin\\Desktop\\index.html";

    public static void main(String[] args) throws IOException {
        File input = new File(ORIG);
        Document doc = Jsoup.parse(input, "UTF-8");
        Element element = doc.getElementById("unique");

        element.append("    " +
                "<div>\n" +
                "        我是CWM上独有的东西\n" +
                "</div>");
        System.out.println(doc);
    }
}
