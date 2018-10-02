package org.bechynak.meals.services;

import org.bechynak.meals.model.Meal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("salandamock")
public class SalandaServiceMock implements RestaurantParser {
    private DocumentsService documentsService;

    @Override
    public List<Meal> getMeals() {
        String url = "/home/petr/Desktop/Detail šalandy _ Restauracesalanda.cz - koncept restaurací Šalanda.html";
        File input = new File(url);
        Document doc = null;
        try {
            doc = Jsoup.parse(input, "UTF-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elementsMeal = doc.select("#day_4995 > tbody > tr");
        List<Meal> meals = new ArrayList<>();

        elementsMeal.stream()
                .forEach(htmlMeal -> {
                    Meal meal = Meal.builder()
                            .weight(htmlMeal.childNode(1).childNodes().isEmpty() ? "" : htmlMeal.childNode(1).childNode(0).toString())
                            .name(htmlMeal.childNode(3).childNode(1).childNode(0).toString()
                                    + htmlMeal.childNode(3).childNode(5).childNode(0).toString())
                            .price(htmlMeal.childNode(5).childNode(0).toString())
                            .build();
                    meals.add(meal);

                });

        return meals;
    }


}
