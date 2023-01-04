package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.DBConnection;
import com.example.demo.daos.RegionDAO;

@Controller
@RequestMapping("region")
public class RegionController {
    private RegionDAO regionDAO = new RegionDAO(DBConnection.getConnection());

    @GetMapping
    public String index(Model model) {
        Object region = regionDAO.getAll();
        model.addAttribute("regions", regionDAO.getAll());
        return "";
    }
}
