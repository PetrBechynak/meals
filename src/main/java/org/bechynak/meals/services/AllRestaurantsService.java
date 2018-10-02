package org.bechynak.meals.services;

import org.bechynak.meals.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    public List<Menu> getMenus() {
        List<Menu> menus = Arrays.asList(
                Menu.builder()
                        .restaurant("Mattes")
                        .meals(mattesService.getMeals())
                        .build(),
                Menu.builder()
                        .restaurant("Salanda")
                        .meals(salandaService.getMeals())
                        .build(),
                Menu.builder()
                        .restaurant("Rybarna")
                        .meals(rybarnaService.getMeals())
                        .build(),
                Menu.builder()
                        .restaurant("Ledarny")
                        .meals(ledarnyService.getMeals())
                        .build());
        return menus;
    }
}