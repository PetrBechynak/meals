package org.bechynak.meals.controllers;

import org.bechynak.meals.model.Meal;
import org.bechynak.meals.model.Menu;
import org.bechynak.meals.services.AllRestaurantsService;
import org.bechynak.meals.services.MattesServiceMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {

    @Value("${app.url}")
    String appUrl;

    private final AllRestaurantsService allRestaurantsService;

    @Autowired
    public MyController(AllRestaurantsService allRestaurantsService) {
        this.allRestaurantsService = allRestaurantsService;
    }

    @RequestMapping(value = "/menus",method = RequestMethod.GET)
    public ResponseEntity<List<Menu>> getMenus() {
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Access-Control-Allow-Origin",appUrl);
        ResponseEntity<List<Menu>> entity = new ResponseEntity<>(allRestaurantsService.getMenus(), header, HttpStatus.OK);
        return entity;
    }

}