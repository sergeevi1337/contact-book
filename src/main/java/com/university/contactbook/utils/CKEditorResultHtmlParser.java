package com.university.contactbook.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CKEditorResultHtmlParser {

    private static CKEditorResultHtmlParser instance;

    public synchronized static CKEditorResultHtmlParser getInstance() {
        if (instance == null) {
            instance = new CKEditorResultHtmlParser();
        }
        return instance;
    }

    public String parse(String html) {
        String path = "";
        if (StringUtils.hasText(html)) {
            Document doc = Jsoup.parse(html);
            Element element = doc.select("img").get(0);
            path = element.attr("src");
        }
        return path;
    }
}
