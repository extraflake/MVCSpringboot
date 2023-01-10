package com.example.demo.controllers;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.DBConnection;
import com.example.demo.daos.DivisionDAO;
import com.example.demo.daos.RegionDAO;
import com.example.demo.models.Division;

@Controller
@RequestMapping("division")
public class DivisionController {
    private DivisionDAO divisionDAO = new DivisionDAO(DBConnection.getConnection());
    private RegionDAO regionDAO = new RegionDAO(DBConnection.getConnection());

    // GET ALL
    // /region
    @GetMapping
    public String index(Model model) {
        Object data = divisionDAO.getAll();
        model.addAttribute("divisions", divisionDAO.getAll());
        return "division/index";
    }

    @GetMapping(value = {"form", "form/{id}"})
    public String form(@PathVariable(required = false) Integer id, Model model) {
        model.addAttribute("regions", regionDAO.getAll());
        if(id != null) {
            model.addAttribute("division", divisionDAO.getById(id));
        } else {
            model.addAttribute("division", new Division());
        }
        return "division/form";
    }

    @PostMapping("save")
    public String save(@Nullable com.example.demo.dto.Division division) {
        Boolean result;
        if(division.getId() != null) {
            result = divisionDAO.update(division);
        }
        else { 
            result = divisionDAO.insert(division);
        }
        if(result) {
            return "redirect:/division";
        } else {
            return "region/form";
        }
    }

    @PostMapping(value = {"delete/{id}"})
    public String delete(@PathVariable(required = true) Integer id) {
        divisionDAO.delete(id);
        return "redirect:/division";
    }
}
