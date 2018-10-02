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
@Qualifier("mattesmock")
public class MattesServiceMock implements RestaurantParser {
    private DocumentsService documentsService;

    @Override
    public List<Meal> getMeals() {
        String url = "/home/petr/Desktop/MATTES RESTAURACE.html";
        File input = new File(url);
        Document doc = null;
        try {
            doc = Jsoup.parse(input, "UTF-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements el = ((Document) doc).getElementsByClass("row denni-menu");
        Element el2 = el.get(0).child(1).child(3).child(0);
        List<Meal> meals = new ArrayList<>();

        el2.children().stream()
                .filter(child -> !child.children().isEmpty())
                .forEach(htmlMeal -> {
                    Meal meal = Meal.builder()
                            .name(htmlMeal.child(1).childNode(0).toString().replace("&nbsp;",""))
                            .price(htmlMeal.child(2).childNode(0).toString().replace("&nbsp;",""))
                            .weight(htmlMeal.child(0).childNode(0).toString().replace("&nbsp;",""))
                            .build();
                    meals.add(meal);

                });

        return meals;
    }


}
