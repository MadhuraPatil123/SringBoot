package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieCatalogSystemSpringBootJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogSystemSpringBootJpaApplication.class, args);
	}

}
.........................................................
package com.springboot.Repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Optional<Movie> findById(Integer id);
}

.........................................................
package com.springboot.Domain;

import javax.persistence.*;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Column
	private String genre;

	@Column
	private int releaseYear;

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(Long id, String title, String genre, int releaseYear) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.releaseYear = releaseYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

}
.........................................................
package com.springboot.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Domain.Movie;
import com.springboot.Repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository repo;

	public List<Movie> findAll() {
		return (List<Movie>)repo.findAll();
	}

	public Movie save(Movie movie) {
		return repo.save(movie);
	}

	public Optional<Movie> findById(Integer id) {
		return repo.findById(id);//.orElse(null);
	}

}

.........................................................
package com.springboot.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Domain.Movie;
import com.springboot.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	MovieService movieService;
	@GetMapping("/list")
	public List<Movie> getAllMovies() {
		return movieService.findAll();
	}

	@PostMapping("/add")
	public List<Movie> addMovie(@RequestBody Movie movie) {
		 movieService.save(movie);
		return movieService.findAll();
	}
	
	@GetMapping("/search/{releaseYear}")
	public List<Movie> searchMoviesByReleaseYear(@PathVariable int releaseYear){
		List<Movie> oldList = movieService.findAll();
		List<Movie> newList = new ArrayList<Movie>();
		for(Movie m:oldList) {
			if(m.getReleaseYear()==releaseYear) {
				newList.add(m);
			}
		}
		return newList;
	}
		
}

.........................................................

