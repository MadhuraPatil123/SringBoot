package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Mobile;
import com.springboot.service.MobileService;

@Controller
public class HomeController {
	
	@Autowired
	MobileService service;

	@GetMapping("/mobileList")
	public String getAllMovies(Model model) {
		model.addAttribute("mobiles",service.findAll());
		return "home";
	}

}

======================================================

package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Fill your code here
@SpringBootApplication
public class MobileDetailsMvc1Application {

	public static void main(String[] args) {
		SpringApplication.run(MobileDetailsMvc1Application.class, args);
	}

}

=======================================================

package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class Mobile {
    @Id

    private Long id;
    

    private String brand;
    

    private String model;

    private Double price;
    

    private int releaseYear;

	public Mobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mobile(Long id, String brand, String model, Double price, int releaseYear) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.releaseYear = releaseYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
    	
}

====================================================

package com.springboot.repository;

import org.springframework.stereotype.Repository;

import com.springboot.entity.Mobile;

import org.springframework.data.jpa.repository.JpaRepository;

//Fill your code here
@Repository
public interface MobileRepository extends JpaRepository<Mobile,Long>{

}

===============================================

package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Mobile;
import com.springboot.repository.MobileRepository;

@Service
public class MobileService {
	
@Autowired
MobileRepository repo;
	
	public List<Mobile> findAll() {
        return (List<Mobile>) repo.findAll();
    	}

}

===============================================

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mobile Details</title>
<style>
.form-list .form-group input {
	width: 100%;
	font-size: 13px;
	height: 38px;
	box-sizing: border-box;
	padding-left: 15px;
	padding-right: 15px;
}

button {
	display: inline-block;
	background-color: #2196F3;
	color: #fff;
	border: none;
	padding: 10px 25px;
	text-transform: uppercase;
	font-weight: 700;
	cursor: pointer;
	margin-bottom: 10px;
}

.container {
	width: 90%;
	margin: 0 auto;
	text-align: left;
}

h3 {
	color: #C71585;
	font-size: 28px;
}

table#hotel-list {
	border-collapse: collapse;
	width: 100%;
	background-color: #fff;
	box-shadow: 0px 4px 5px 0px #ededed;
}

table{
width: 100%;
}
table th {
    background-color: #ffb500;
    color: white;
    padding: 12px 15px;
}

table td {
	padding: 12px 15px;
}

tr:nth-child(even) {
  background-color: #ffb50042;
}

table tr {
	background-color: #ffb5001f;
}
</style>
</head>
<body>
	<div class="container">
		<div class="form-list">
			<h3>Mobile Detail List</h3>
		</div>
		<table id="mobile">
			<tr>
				<th>Mobile Brand</th>
				<th>Mobile Model</th>
				<th>Price</th>
				<th>Release Year</th>
			</tr>
			<!--//Fill your code here-->
			<c:forEach var="mobile" items="${mobiles}">
            <tr>
               
                <td>${mobile.brand}</td>
                <td>${mobile.model}</td>
                <td>RS.${mobile.price}</td>
                <td>${mobile.releaseYear}</td>
            </tr>
        </c:forEach>
		</table>
	</div>
</body>
</html>
