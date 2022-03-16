package com.example.Relation.Controller;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Relation.Repository.TutorialRepository;
import com.example.Relation.model.Tutorial;

@RestController
@RequestMapping("/api")
public class TutorialControlle {
	@Autowired
	TutorialRepository tutorialRepository;

	@GetMapping("/getAllTutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		List<Tutorial> tutorials = new ArrayList<Tutorial>();
		tutorialRepository.findAll().forEach(tutorials::add);
		if (tutorials.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tutorials, HttpStatus.OK);
	}

	@GetMapping("/getTutorialById/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) throws AttributeNotFoundException {
		Tutorial tutorial = tutorialRepository.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("Not found Tutorial with id = " + id));
		return new ResponseEntity<>(tutorial, HttpStatus.OK);
	}

	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		Tutorial _tutorial = tutorialRepository
				.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
		return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
	}

	@PutMapping("/updateTutorial/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Tutorial tut = tutorialRepository.findById(id)
				.orElseThrow(() -> new InvalidParameterException("Not found Tutorial with id = " + id));
		tut.setTitle(tutorial.getTitle());
		tut.setDescription(tutorial.getDescription());
		tut.setPublished(tutorial.isPublished());
		return new ResponseEntity<>(tutorialRepository.save(tut), HttpStatus.OK);
	}

	@DeleteMapping("/deleteTutorial/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		tutorialRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteAllTutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		tutorialRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/findByPublished/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
		if (tutorials.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tutorials, HttpStatus.OK);
	}
}
