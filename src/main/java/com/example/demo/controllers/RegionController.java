package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.DBConnection;
import com.example.demo.daos.RegionDAO;
import com.example.demo.models.Region;

@Controller
@RequestMapping("region")
public class RegionController {
    private RegionDAO regionDAO = new RegionDAO(DBConnection.getConnection());

    // GET ALL
    // /region
    @GetMapping
    public String index(Model model) {
        model.addAttribute("regions", regionDAO.getAll());
        return "region/index";
    }

    // CREATE 
    // GET
    // /region/form
    @GetMapping(value = {"form"})
    public String create(Model model) {
        model.addAttribute("region", new Region());
        return "region/form";
    }

    // POST
    @PostMapping("save")
    public String save(Region region) {
        Boolean result = regionDAO.insert();
        if(result) {
            return "redirect:/region";
        } else {
            return "region/form";
        }
    }

    // UPDATE
    // GET
    // POST

    // DELETE
    // POST
}
