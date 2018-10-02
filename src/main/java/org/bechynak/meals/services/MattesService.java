package org.bechynak.meals.services;

import org.bechynak.meals.model.Meal;
import org.bechynak.meals.model.Menu;
import org.bechynak.meals.model.Restaurants;
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
@Qualifier("mattes")
public class MattesService implements RestaurantParser {
    private DocumentsService documentsService;

    @Override
    public List<Meal> getMeals() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.mattesrestaurace.cz/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements el = doc.getElementsByClass("row denni-menu");
        Element el2 = el.get(0).child(1).child(3).child(0);
        List<Meal> meals = new ArrayList<>();

        el2.children().stream()
                .filter(child -> !child.children().isEmpty())
                .forEach(htmlMeal -> {
                    Meal.MealBuilder mealbuilder = Meal.builder();

                    if (htmlMeal.child(1) != null && htmlMeal.child(1).childNode(0) != null) {
                        mealbuilder.name(htmlMeal.child(1).childNode(0).toString().replace("&nbsp;", ""));
                    }
                    if (htmlMeal.child(2) != null && htmlMeal.child(1).childNode(0) != null) {
                        mealbuilder.price(htmlMeal.child(2).childNode(0).toString().replace("&nbsp;", ""));
                    }
                    if (htmlMeal.child(0) != null && htmlMeal.child(1).childNode(0) != null) {
                        mealbuilder.weight(htmlMeal.child(0).childNode(0).toString().replace("&nbsp;", ""));
                    }
                    meals.add(mealbuilder.build());

                });

        return meals;
    }


}
