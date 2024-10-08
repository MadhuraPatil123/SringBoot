package com.springboot.controller;

import com.springboot.dao.DoctorDAO;
import com.springboot.domain.Doctor;
import com.springboot.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController 
{
    @Autowired
    DoctorService ds;

    @GetMapping("/list")
    List<Doctor> getDoctors() 
    {
        return ds.list();

    }

    @PostMapping("/create")
    Boolean create(@RequestBody Doctor doctor) 
    {
        return ds.save(doctor);

    }
}

----------------------------------------------------------------
package com.springboot.service;

import java.util.*;

import com.springboot.domain.Doctor;

public interface DoctorService {
  List<Doctor> list();
  Boolean save(Doctor doctor);
}
  

----------------------------------------------------------------
package com.springboot.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springboot.dao.DoctorDAO;
import com.springboot.dao.DoctorDAOImpl;
import com.springboot.domain.Doctor;


@Service
public class DoctorServiceImpl implements DoctorService{

  @Autowired
  DoctorDAO ddao;
    
    public Boolean save(Doctor doctor) 
	{
		return ddao.save(doctor);

	}

	public List<Doctor> list() 
	{
		return ddao.list();

	}
    
}
----------------------------------------------------------------
package com.springboot.domain;

public class Doctor {
	
	private Integer id;
	private String name;
	private String qualification;
	private Double experience;
	private String specialization;
	private Double consultingFees;
		
	public Doctor() {
		
	}
	
	public Doctor(Integer id, String name, String qualification,
			Double experience, String specialization, Double consultingFees) {
		super();
		this.id = id;
		this.name = name;
		this.qualification = qualification;
		this.experience = experience;
		this.specialization = specialization;
		this.consultingFees = consultingFees;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public Double getExperience() {
		return experience;
	}
	public void setExperience(Double experience) {
		this.experience = experience;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public Double getConsultingFees() {
		return consultingFees;
	}
	public void setConsultingFees(Double consultingFees) {
		this.consultingFees = consultingFees;
	}
	
	
}
----------------------------------------------------------------
package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.controller.DoctorController;

@SpringBootApplication
public class App {

	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

}
----------------------------------------------------------------

package com.springboot.dao;

import java.util.*;

import com.springboot.domain.Doctor;

public interface DoctorDAO {
  List<Doctor> list();
  Boolean save(Doctor doctor);
}
  



package com.springboot.dao;

import com.springboot.domain.Doctor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorDAOImpl implements DoctorDAO
{
    static ArrayList<Doctor> doctorList = new ArrayList<Doctor>();

	static 
	{
		doctorList.add(new Doctor(1,"Harinii","MBBS",4.2,"Orthologist",750.00));
		doctorList.add(new Doctor(2,"Nithin","MBBS",2.0,"Gynecologist",1500.00));
	}
	
	public Boolean save(Doctor doctor) 
	{
		return doctorList.add(doctor);		

	}

	public List<Doctor> list() 
	{
		return doctorList;

	}
}


----------------------------------------------------------------
