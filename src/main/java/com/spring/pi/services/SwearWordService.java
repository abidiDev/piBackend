package com.spring.pi.services;

import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Service
public class SwearWordService {
    public List<String> getSwearWords() throws IOException {


        List<String> swearWords = new ArrayList<>();

        Document doc = Jsoup.connect("http://www.bannedwordlist.com/lists/swearWords.xml").get();

        Elements elements = doc.select("word");

        for (Element element : elements) {
            swearWords.add(element.text());
        }

        return swearWords;
    }
}
