package com.example.Relation.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Relation.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
	List<Tutorial> findByTitleContaining(String title);

	List<Tutorial> findByPublished(boolean published);

}
