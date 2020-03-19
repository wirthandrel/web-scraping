package com.wirth.web.scraption.scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class ExtractHtml {

    public List<List<String>> extract() throws IOException {

        List<List<String>> colunas = new LinkedList<>();

        Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
        Element element = doc.getElementById("ctl00_ContentPlaceHolder1_gdvDisponibilidade2");
        Elements tbody = element.getElementsByTag("tbody");

        Elements trElements = tbody.get(0).getElementsByTag("tr");

        for (Element trElement : trElements) {
            List<String> linhas = new LinkedList<>();

            Elements tdElements = trElement.getElementsByTag("td");

            for (Element tdElement : tdElements) {
                if (tdElement.getElementsByTag("img").isEmpty()) {
                    linhas.add(tdElement.html());
                } else {
                    linhas.add(tdElement.getElementsByTag("img").attr("src"));
                }
            }

            if (!linhas.isEmpty()) {
                colunas.add(linhas);
            }
        }

        return colunas;
    }
}
