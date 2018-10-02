package org.bechynak.meals.services;

import org.bechynak.meals.model.Meal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("ledarnymock")
public class LedarnyServiceMock implements RestaurantParser {
    private DocumentsService documentsService;

    @Override
    public List<Meal> getMeals() {
        String url = "/home/petr/Desktop/Restaurace - vinárna, restaurace Praha 4, vinárna Praha 6, vinárna Praha 3.html";
        File input = new File(url);
        Document doc = null;
        try {
            doc = Jsoup.parse(input, "UTF-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements mealElements = doc.getElementsByTag("tbody").get(2).children();
        List<Meal> meals = new ArrayList<>();

        mealElements.stream()
                .filter(child -> !child.children().isEmpty())
                .forEach(htmlMeal -> {
                    Meal.MealBuilder meal = Meal.builder();
                    if (!htmlMeal.childNode(5).childNodes().isEmpty()
                            && !htmlMeal.childNode(5).childNodes().get(1).childNodes().isEmpty()) {
                        meal.price(htmlMeal.childNode(5).childNodes().get(1).childNodes().get(0).toString()
                                .replace("<strong>", "")
                                .replace("&nbsp;", "")
                                .replace("</strong>", ""));
                    }
                    if (!htmlMeal.childNode(3).childNodes().isEmpty()
                            && !htmlMeal.childNode(3).childNodes().get(1).childNodes().isEmpty()) {
                        meal.name(htmlMeal.childNode(3).childNodes().get(1).childNodes().get(0).toString()
                                .replace("<strong>", "")
                                .replace("&nbsp;", "")
                                .replace("</strong>", ""));
                    }
                    if (!htmlMeal.childNode(1).childNodes().isEmpty()
                            && !htmlMeal.childNode(1).childNodes().get(1).childNodes().isEmpty()) {
                        meal.weight(htmlMeal.childNode(1).childNodes().get(1).childNodes().get(0).toString()
                                .replace("<strong>", "")
                                .replace("&nbsp;", "")
                                .replace("</strong>", ""));
                    }
                    meals.add(meal.build());

                });
        return meals;
    }


}
