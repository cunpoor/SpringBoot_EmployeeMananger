package com.cp.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Staffs")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "gender", nullable = false)
    private boolean gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @Column(name = "email", nullable = true, length = 64)
    private String email;
    @Column(name = "phone", nullable = false, length = 13)
    private String phone;
    @Column(name = "salary", nullable = false)
    private int salary;
    @Column(name = "notes", nullable = true)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "depart_id")
    @JsonIgnoreProperties("staffs")
    private Depart departs;
    //
    @OneToMany(mappedBy = "staffs", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("staffs")
    private Set<Record> record;

    public Staff() {
    }

    public Staff(Long id, String name, boolean gender, Date birthday, String email, String phone, int salary,
            String notes, Depart departs, Set<Record> record) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.notes = notes;
        this.departs = departs;
        this.record = record;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDeparts(Depart departs) {
        this.departs = departs;
    }

    public Depart getDeparts() {
        return departs;
    }

    public Set<Record> getRecord() {
        return record;
    }

    public void setRecord(Set<Record> record) {
        this.record = record;
    }
    
}
