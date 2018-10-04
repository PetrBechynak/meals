package org.bechynak.meals.services;

import org.bechynak.meals.model.Meal;
import org.bechynak.meals.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AllRestaurantsService {

    private RestaurantParser mattesService;
    private RestaurantParser salandaService;
    private RestaurantParser rybarnaService;
    private RestaurantParser ledarnyService;

    AllRestaurantsService(
            @Qualifier("mattes") @Autowired RestaurantParser mattesService,
            @Qualifier("salanda") @Autowired RestaurantParser salandaService,
            @Qualifier("rybarna") @Autowired RestaurantParser rybarnaService,
            @Qualifier("ledarny") @Autowired RestaurantParser ledarnyService) {
        this.mattesService = mattesService;
        this.salandaService = salandaService;
        this.rybarnaService = rybarnaService;
        this.ledarnyService = ledarnyService;
    }

    @Cacheable("menuCache")
    public List<Menu> getMenus() {
        List<Menu> menus = new ArrayList<>();

        String restaurant = "Mattes";
        try {
            menus.add(Menu.builder()
                    .restaurant(restaurant)
                    .meals(mattesService.getMeals())
                    .build());
        } catch (Exception e) {
            System.out.println(e);
            menus.add(fillError(restaurant));
        }

        restaurant = "Salanda";
        try {
            menus.add(Menu.builder()
                    .restaurant("Salanda")
                    .meals(salandaService.getMeals())
                    .build());
        } catch (Exception e) {
            System.out.println(e);
            menus.add(fillError(restaurant));
        }

        restaurant = "Rybarna";
        try {
            menus.add(Menu.builder()
                    .restaurant("Rybarna")
                    .meals(rybarnaService.getMeals())
                    .build());
        } catch (Exception e) {
            System.out.println(e);
            menus.add(fillError(restaurant));
        }

        restaurant = "Ledarny";
        try {
            menus.add(Menu.builder()
                    .restaurant("Ledarny")
                    .meals(ledarnyService.getMeals())
                    .build());
            return menus;
        } catch (Exception e) {
            System.out.println(e);
            menus.add(fillError(restaurant));
        }
        return menus;
    }

    private Menu fillError(String restaurant) {
        return Menu.builder()
                .restaurant(restaurant)
                .meals(Collections.singletonList(
                        Meal.builder().name("Chyba načítání menu").build())
                )
                .build();
    }
}