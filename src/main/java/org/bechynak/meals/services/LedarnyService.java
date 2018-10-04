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
@Qualifier("ledarny")
public class LedarnyService implements RestaurantParser {
    private DocumentsService documentsService;

    @Override
    public List<Meal> getMeals() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.senkyrna.cz/senkyrna-na-ledarnach").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements mealElements = doc.getElementsByTag("tbody").get(2).children();
        List<Meal> meals = new ArrayList<>();

        mealElements.stream()
                .filter(child -> !child.children().isEmpty())
                .forEach(htmlMeal -> {
                    Meal.MealBuilder meal = Meal.builder();
                    if (htmlMeal.childNode(5).childNodes().size()>2
                            && !htmlMeal.childNode(5).childNodes().get(1).childNodes().isEmpty()) {
                        meal.price(htmlMeal.childNode(5).childNodes().get(1).childNodes().get(0).toString()
                                .replace("<strong>", "")
                                .replace("&nbsp;", "")
                                .replace("</strong>", ""));
                    }
                    if (htmlMeal.childNode(5).childNodes().size()>2
                            && !htmlMeal.childNode(3).childNodes().get(1).childNodes().isEmpty()) {
                        meal.name(htmlMeal.childNode(3).childNodes().get(1).childNodes().get(0).toString()
                                .replace("<strong>", "")
                                .replace("&nbsp;", "")
                                .replace("</strong>", ""));
                    }
                    if (htmlMeal.childNode(5).childNodes().size()>2
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
