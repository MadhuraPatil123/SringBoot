package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dao.DoctorDAO;
import com.springboot.domain.Doctor;
import com.springboot.service.DoctorServiceImpl;

@RestController

public class DoctorController {
	@Autowired
	DoctorServiceImpl  dservice;
	

	@GetMapping("/doctor/list")
	public List<Doctor> getDoctors(){
		return dservice.list();

	}
}
---------------------------------------------------------------
package com.springboot.service;

import java.util.*;
import org.springframework.stereotype.Component;

import com.springboot.domain.Doctor;

public interface DoctorService{
    List<Doctor> list();
}



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
	DoctorDAO dao;
	
	public List<Doctor> list(){

		return dao.list();

	}
    
}

---------------------------------------------------------------
package com.springboot.domain;

public class Doctor {
	
	private Integer id;
	private String name;
	private String qualification;
	private Double experience;
	private String specialization;
	private Double consultingFees;
		
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

---------------------------------------------------------------
package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.springboot.controller.DoctorController;

@SpringBootApplication
public class App {

	public static void main(String[] args) {

	SpringApplication.run(App.class, args);

	}

}
---------------------------------------------------------------
package com.springboot.dao;

import java.util.*;
import org.springframework.stereotype.Repository;

import com.springboot.domain.Doctor;

@Repository
public class DoctorDAOImpl implements DoctorDAO {

    private static List<Doctor> doctors = new ArrayList<>();

	static{
		doctors.add(new Doctor(1, "Elizabeth", "MBBS", 4.2, "Cardiologist", 750.));
		doctors.add(new Doctor(2, "Michael", "MBBS", 2.0, "Dermatologist", 1500.));
		doctors.add(new Doctor(3, "Charlotte", "MBBS", 3.1, "Pediatrics", 200.));
		doctors.add(new Doctor(4, "Lucas", "BDS", 1.9, "Dentist", 250.));
	}	

	public List<Doctor> list() {
		return DoctorDAOImpl.doctors;
	}
	
}
---------------------------------------------------------------

package com.springboot.dao;

import java.util.*;
import org.springframework.stereotype.Component;

import com.springboot.domain.Doctor;

public interface DoctorDAO {
    List<Doctor> list();
}
---------------------------------------------------------------

