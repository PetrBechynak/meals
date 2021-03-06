package org.bechynak.meals.services;

import org.bechynak.meals.model.Meal;
import org.bechynak.meals.model.Menu;

import java.util.Arrays;
import java.util.List;

public class Temp {
    List<Menu> menus = Arrays.asList(
            Menu.builder()
                    .meals(
                            Arrays.asList(
                                    Meal.builder()
                                            .name("rizek")
                                            .price("150")
                                            .weight("200")
                                            .build(),
                                    Meal.builder()
                                            .name("hambac")
                                            .price("200")
                                            .weight("150")
                                            .build()

                            )
                    )
                    .restaurant("Mattes")
                    .build(),
            Menu.builder()
                    .meals(
                            Arrays.asList(
                                    Meal.builder()
                                            .name("sdflk")
                                            .price("150")
                                            .weight("200")
                                            .build(),
                                    Meal.builder()
                                            .name("njks")
                                            .price("200")
                                            .weight("150")
                                            .build()

                            )
                    )
                    .restaurant("U cejpu")
                    .build()
    );
}
