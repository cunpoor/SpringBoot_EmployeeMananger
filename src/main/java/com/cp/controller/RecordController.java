package com.cp.controller;

import com.cp.entity.Record;
import com.cp.repository.RecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("")
    public String index(ModelMap model) {
        model.addAttribute("RECORD_LIST", recordRepository.findAll());
        return "record/index";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        model.addAttribute("RECORD", new Record());
        return "record/addOrUpdate";
    }

    @PostMapping(value = "path")
    public String save(@RequestParam Record Record) {
        
        return "Redirect:/record";
    }

}
