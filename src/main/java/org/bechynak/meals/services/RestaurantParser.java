package org.bechynak.meals.services;

import org.bechynak.meals.model.Meal;
import org.bechynak.meals.model.Menu;
import org.bechynak.meals.model.Restaurants;

import java.util.List;

public interface RestaurantParser {
    List<Meal> getMeals();
}
