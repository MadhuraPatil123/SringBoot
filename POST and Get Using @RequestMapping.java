package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.springboot.controller", "com.springboot.service", "com.springboot.dao", "com.springboot.domain"})
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
-------------------------------------------------------------------------------------------
package com.springboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.service.PatientServiceImpl;
import com.springboot.service.PatientService;
import com.springboot.dao.PatientDAO;
import com.springboot.domain.Patient;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired 
	PatientService pservice;

	//@GetMapping("/list")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Patient> listPatients(){
		return pservice.getPatients();
		
	}

	//@PostMapping("/create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public List<Patient> createPatient(@RequestBody Patient patient){
		 return  pservice.createPatient(patient);
		
	}
	
}

-------------------------------------------------------------------------------------------
package com.springboot.service;

import java.util.List;

import com.springboot.domain.Patient;

public interface PatientService {
    List<Patient> createPatient(Patient patient);
    List<Patient> getPatients();
}





package com.springboot.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springboot.dao.PatientDAO;
import com.springboot.dao.PatientDAOImpl;
import com.springboot.domain.Patient;



@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	PatientDAO pdao;

	@Override
	public List<Patient> createPatient(Patient p){
		return pdao.createPatient(p);//.get(p.size() - 1);
	}
	
	@Override
	public List<Patient> getPatients(){
		return pdao.getPatients();
		
	}
    
}
-------------------------------------------------------------------------------------------
package com.springboot.domain;

import java.util.Date;

public class Patient {
	
	private Long id;
	private String name;
	private String contactNumber;
	private String email;
	private String dateOfBirth;
	
	public Patient() {
		super();
	}
	public Patient(Long id, String name, String contactNumber, String email, String dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.contactNumber = contactNumber;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}

-------------------------------------------------------------------------------------------
package com.springboot.dao;

import java.util.List;
import com.springboot.domain.Patient;

public interface PatientDAO {
  List<Patient> createPatient(Patient p);
    List<Patient> getPatients();
}
  

package com.springboot.dao;


import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

import com.springboot.domain.Patient;


@Repository
public class PatientDAOImpl implements PatientDAO {
    
	static List<Patient> patientList = new ArrayList<>(); 
	
	static {
			patientList.add(new Patient(1L, "Swathy", "9876567234", "swathy@gmail.com", "31-07-1989"));
			patientList.add(new Patient(2L, "Vanmathi", "9873877234", "vanmathi@gmail.com", "23-04-1992"));
			patientList.add(new Patient(3L, "Kevin", "9823641234", "kevin@gmail.com", "01-04-2000"));
	}
	
	public List<Patient> createPatient(Patient p){
		patientList.add(p);
		return patientList;
	}
	
	public List<Patient> getPatients(){
		return patientList;
	}
	
}

-------------------------------------------------------------------------------------------
