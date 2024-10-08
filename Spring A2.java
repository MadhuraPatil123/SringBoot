package com.springboot.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.entity.Mobile;
import com.springboot.service.MobileService;

//Fill your code here
@Controller
public class HomeController {
	
	//Fill your code here
	@Autowired
	private MobileService service;
	//Fill your code here

	@RequestMapping("/add")
	public String addHotel(Model model) {		
		//Fill your code here
		model.addAttribute("mobile",new Mobile());
		return "create-mobile";

	
	}

	//Fill your code her
	@GetMapping("/mobileList")
	public String getAllMovies(Model model) {
		//Fill your code here
		model.addAttribute("mobiles",service.findAll());
		return "home";
	}
	
	//Fill your code here  
	@PostMapping("/submit")
	public String saveHotelDetails(@RequestParam("brand") String brand,
			@RequestParam("model") String model,@RequestParam("price") Double price,
			@RequestParam("releaseYear") int releaseYear) throws ParseException{ 

				Mobile mobile = new Mobile();
				mobile.setBrand(brand);
				mobile.setModel(model);
				mobile.setPrice(price);
				mobile.setReleaseYear(releaseYear);

				service.saveMobile(mobile);

				return "redirect:/mobileList";

                /*Mobile mo = new Mobile();
        mo.setBrand(brand);
        mo.setModel(model);
        mo.setPrice(price);
        mo.setReleaseYear(releaseYear);
        service.saveMobile(mo);
        return "redirect:/mobileList";  */
		
		//Fill your code here 		

	}
	
}


===============

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

======================

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
@GeneratedValue(strategy=GenerationType.IDENTITY)   //if it is not allowed
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

===================

package com.springboot.repository;

import org.springframework.stereotype.Repository;

import com.springboot.entity.Mobile;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MobileRepository extends JpaRepository<Mobile,Long>{

}

===================

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

	public void saveMobile(Mobile m) {
		repo.save(m);
		
	}

}


===============================

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
body {
    background-color: #ffb50042;
    color: black;
}

input {
    padding: 10px;
}

button {
    display: inline-block;
    background-color: #ffb500;
    color: #fff;
    border: none;
    padding: 10px 25px;
    text-transform: uppercase;
    font-weight: 700;
    margin-left: 46%;
    margin-top: 17px;
}

tr {
    height: 60px;
}

.container {
    width: 90%;
    margin: 0 auto;
    text-align: left;
}

h3 {
    color: black;
    font-size: 28px;
    margin-bottom: 20px;
}

table {
    padding-top: 20px;
    padding-left: 37%;
    width: 70%;
}
</style>
</head>
<body>
    <div class="heading">
        <h3>Mobile Details</h3>
    </div>

    <form:form method="post" action="submit" modelAttribute="mobile">
        <table>
            <tr>
                <td><form:label path="brand">Brand</form:label></td>
                <td><form:input path="brand" /></td>
            </tr>
            <tr>
                <td><form:label path="model">Model</form:label></td>
                <td><form:input path="model" /></td>
            </tr>
            <tr>
                <td><form:label path="price">Price</form:label></td>
                <td><form:input path="price" /></td>
            </tr>
            <tr>
                <td><form:label path="releaseYear">Release Year</form:label></td>
                <td><form:input path="releaseYear" /></td>
            </tr>
        </table>
        <button type="submit" id="add">ADD</button>
    </form:form>
</body>
</html>

=============================

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mobile Details</title>
<style>
body {
    background-color: #ffb50042;
    color: black;
}
button {
    display: inline-block;
    background-color: #ffb500;
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
table #hotel-list {  
    border-collapse: collapse;
    width: 100%;
    background-color: #fff;
    box-shadow: 0px 4px 5px 0px #ededed;
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
        <form action="add" method="get">
            <h3>Mobile Detail List</h3>
            <button type="submit" id="add-mobile">Add Mobile</button>
        </form>
        <table id="mobile">  
            <tr>
                <th>Mobile Brand</th>
                <th>Mobile Model</th>
                <th>Price</th>
                <th>Release Year</th>   
            </tr>
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
