package com.weather.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Fill your code here
@SpringBootApplication
public class WeatherJpaApplication {

    public static void main(String[] args) {
		SpringApplication.run(WeatherJpaApplication.class, args);
	}

}


....................
//Controller

package com.weather.jpa.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.weather.jpa.domain.WeatherReport;
import com.weather.jpa.service.WeatherService;

@RestController
@RequestMapping("/")
public class WeatherController {
	@Autowired
	WeatherService ws;
    
	@GetMapping("/weatherReport")
	public List<WeatherReport> getData() {
		return ws.getData();

	}
	@PostMapping("/weatherReport")
	public WeatherReport addCases(@RequestBody WeatherReport cases) {
		return ws.addCases(cases);

	}
	@PutMapping("/weatherReport")
	public WeatherReport updateCases(@RequestBody WeatherReport cases) {
		return ws.updateCases(cases);

	}
	@GetMapping("/weatherReport/{id}")
	public WeatherReport view(@PathVariable Long id) {
		return ws.view(id);

	}
	@DeleteMapping("/weatherReport/{id}")
	public Boolean deleteCases(@PathVariable Long id) {
        return ws.deleteCases(id);

	}
	
}
...............
//Repository

package com.weather.jpa.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.weather.jpa.domain.WeatherReport;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<WeatherReport, Long> {
    
}
.................
//Service

package com.weather.jpa.service;
 
import java.util.List;
 import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.weather.jpa.domain.WeatherReport;
import com.weather.jpa.repository.WeatherRepository;

@Service
public class WeatherService {

	@Autowired
	WeatherRepository wr;

    public List<WeatherReport> getData() {
    	return (List<WeatherReport>) wr.findAll();
	}
	
	public WeatherReport addCases(WeatherReport cases) {
		return wr.save(cases);
	}

	public WeatherReport updateCases(WeatherReport cases) {
		return wr.save(cases);
	}
	
	public WeatherReport view(Long id) {
        Optional<WeatherReport> report = wr.findById(id);
        return report.orElse(null);
    }
	
	public Boolean deleteCases(Long id) {
        try{
			wr.deleteById(id) ;
			return true; 
		}catch(Exception e){
			return false;
		}
				

	}
}
.....................
//Domain

package com.weather.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
public class WeatherReport {

   @Id
    private Long id;
	
	@Column
	private String city;

	@Column
	private Double minTemperature;

	@Column	
	private Double maxTemperature;
	
	public WeatherReport() {}

	public WeatherReport(Long id, String city, Double minTemperature, Double maxTemperature) {
		super();
		this.id = id;
		this.city = city;
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(Double minTemperature) {
		this.minTemperature = minTemperature;
	}

	public Double getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(Double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	
}



