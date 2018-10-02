package org.bechynak.meals.services;

import org.bechynak.meals.model.Meal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("rybarnamock")
public class RybarnaServiceMock implements RestaurantParser {
    private DocumentsService documentsService;

    @Override
    public List<Meal> getMeals() {
        String url = "/home/petr/Desktop/Denní menu _ Rybarna.net.html";
        File input = new File(url);
        Document doc = null;
        try {
            doc = Jsoup.parse(input, "UTF-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements soupElements = doc.select("#post-87 > div > p:nth-child(9)");
        Elements mealElements = doc.select("#post-87 > div > ol > li");
        List<Meal> meals = new ArrayList<>();

        String soup = soupElements.get(0).childNode(0).toString();
        meals.add(Meal.builder()
                .name(soup.substring(soup.length() - 3, soup.length()))
                .price(soup.substring(0, soup.length() - 3))
                .weight("")
                .build());
        mealElements
                .forEach(htmlMeal -> {
                    String label = htmlMeal.childNode(0).toString();
                    Meal meal = Meal.builder()
                            .price(label.substring(label.length() - 3, label.length()))
                            .name(label.substring(0, label.length() - 3))
                            .weight("")
                            .build();
                    meals.add(meal);

                });
        return meals;
    }


}
