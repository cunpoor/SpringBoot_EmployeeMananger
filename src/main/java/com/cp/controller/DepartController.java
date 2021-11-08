package com.cp.controller;

import java.util.Optional;


import com.cp.entity.Depart;
import com.cp.repository.DepartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/depart")
public class DepartController {
    @Autowired
    private DepartRepository departRepository;

    private Long id_Depart = null;

    @GetMapping("")
    public String index(ModelMap model) {
        model.addAttribute("DEPART_LIST", this.departRepository.findAll());
        return "depart/index";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        model.addAttribute("DEPART", new Depart());
        this.id_Depart = null;
        return "depart/addOrUpdate";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("DEPART") Depart depart) {
        if(this.id_Depart == null){
            this.departRepository.save(depart);
        }else{
            Optional<Depart> dp = departRepository.findById(this.id_Depart);
            depart.setId(dp.get().getId());
            this.departRepository.save(depart);
            this.id_Depart = null;
        }
        return "redirect:/depart";
    }

    @GetMapping("/update")
    public ModelAndView edit(@RequestParam Long id) {
        Optional<Depart> dp = this.departRepository.findById(id);
        ModelAndView mav = new ModelAndView("depart/addOrUpdate");
        if(dp.isPresent()){
            mav.addObject("DEPART", dp);
            this.id_Depart = id;
        }else{
            System.out.println("Khong ton tai "+id);
            mav.setViewName("redirect:/depart");
        }
        return mav;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        Optional<Depart> dp = this.departRepository.findById(id);
        if(dp.isPresent()){
            this.departRepository.deleteById(id);
        }else{
            System.out.println("Khong ton tai "+id);
        }
        return "redirect:/depart";
    }

}
