package org.bechynak.meals.services;

import org.bechynak.meals.model.Meal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("salanda")
public class SalandaService implements RestaurantParser {
    private DocumentsService documentsService;

    @Override
    public List<Meal> getMeals() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.restauracesalanda.cz/cs/salanda/u-cejpu/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Elements elementsMeal = doc.select("#day_5070 > tbody > tr");
        
        List<Node> elementsMeal = doc.getElementById("daily-meals").childNode(1).childNode(5).childNode(1).childNodes();
        List<Meal> meals = new ArrayList<>();

        elementsMeal.stream()
                .filter(child -> !child.childNodes().isEmpty())
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
