package com.cp.controller;

import java.util.Optional;

import com.cp.entity.Staff;
import com.cp.repository.DepartRepository;
import com.cp.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private DepartRepository departRepository;

    private Long id_staff = null;

    @GetMapping("")
    public String index(ModelMap model) {
        model.addAttribute("STAFF_LIST", staffRepository.findAll());
        return "staff/index";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        model.addAttribute("STAFF", new Staff());
        model.addAttribute("DEPART_LIST", departRepository.findAll());
        id_staff = null;
        return "staff/addOrUpdate";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("STAFF") Staff staff) {
        if(id_staff == null){
            this.staffRepository.save(staff);
        }else{
            Optional<Staff> staff_find = staffRepository.findById(id_staff);
            staff.setId(staff_find.get().getId());
            this.staffRepository.save(staff);
            id_staff = null;
        }
        return "redirect:/staff";
    }

    @GetMapping("/update")
    public ModelAndView update(@RequestParam Long id, ModelMap model) {
        Optional<Staff> staff = staffRepository.findById(id);
        ModelAndView mav = new ModelAndView("staff/addOrUpdate");
        if (staff.isPresent()) {
            mav.addObject("STAFF", staff);
            model.addAttribute("DEPART_LIST", departRepository.findAll());
            id_staff = id;
        } else {
            System.out.println("Khong ton tai: " + id);
            mav.setViewName("redirect:/staff");
        }
        return mav;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isPresent()) {
            staffRepository.deleteById(id);
        } else {
            System.out.println("Khong ton tai: " + id);
        }
        return "redirect:/staff";
    }

}
