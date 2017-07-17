package com.allstate.compozed.springplayground.home;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
final class HomeController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    HomeModel list() {
        return new HomeModel("Hello spring");
    }
}


