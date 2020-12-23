package com.dachan.integration.junitdemo.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/index")
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value="/getData")
    @ResponseBody
    public String getData(@RequestParam(value="searchPhrase", required=false) String searchPhrase) {
        String status = "{\"status\" : \"200\", \"searchPhrase\" : \"" + searchPhrase + "\"}";
        return status;
    }

    @RequestMapping(value="/getVersion")
    @ResponseBody
    public String getVersion(@RequestParam(value="searchBy", required=false) String searchPy) {
        String status = "{\"version\" : \"2020.12.22 Version Super\", \"searchBy\" : \"" + searchPy + "\"}";
        return status;
    }

}
