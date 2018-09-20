package org.bechynak.meals.controllers;

import org.bechynak.meals.model.Menu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MenuController {

    @GetMapping(name = "/menus")
    public List<Menu> getMenus() {
        List<Menu> menus = Arrays.asList(
                Menu.builder().menu("a").restaurant("b").build(),
                Menu.builder().menu("c").restaurant("d").build());
        return menus;
    }

}