package com.cp.controller;

import java.util.Date;
import java.util.Optional;

import com.cp.entity.Record;
import com.cp.repository.RecordRepository;
import com.cp.repository.StaffRepository;

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
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private StaffRepository staffRepository;

    private Long record_id = null;

    @GetMapping("")
    public String index(ModelMap model) {
        model.addAttribute("RECORD_LIST", this.recordRepository.findAll());
        return "record/index";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        Record record = new Record();
        record.setDate(new Date());
        model.addAttribute("RECORD", record);
        model.addAttribute("STAFF_LIST", this.staffRepository.findAll());
        this.record_id = null;
        return "record/addOrUpdate";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Record record) {
        if(this.record_id == null){
            this.recordRepository.save(record);
        }else{
            Optional<Record> rd = this.recordRepository.findById(this.record_id);
            record.setId(rd.get().getId());
            this.recordRepository.save(record);
            this.record_id = null;
        }
        return "redirect:/record";
    }

    @GetMapping("/update")
    public ModelAndView update(@RequestParam Long id, ModelMap model) {
        ModelAndView mav = new ModelAndView("record/addOrUpdate");
        Optional<Record> rd = this.recordRepository.findById(id);
        if (rd.isPresent()) {
            mav.addObject("RECORD", rd);
            model.addAttribute("STAFF_LIST", this.staffRepository.findAll());
            this.record_id = id;
        } else {
            System.out.println("Khong ton tai: " + id);
            mav.setViewName("redirect:/record");
        }
        return mav;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        Optional<Record> record = this.recordRepository.findById(id);
        if (record.isPresent()) {
            this.recordRepository.deleteById(id);
        } else {
            System.out.println("Khong ton tai: " + id);
        }
        return "redirect:/record";
    }

}
