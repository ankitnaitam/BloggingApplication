package com.bikkadit.blog.repositories;

import java.util.List; 

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bikkadit.blog.entities.Category;
import com.bikkadit.blog.entities.Post;
import com.bikkadit.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	public List<Post> findByCategory(Category category);

	public List<Post> findByUser(User user);

	public List<Post> findByTitleContaining(String title); // will use Like Operator to fetch

//   OR---by native query
//	@Query(value = "select p from Post p where p.title like :key")
//	public List<Post> searchByTitle(@Param("key") String title);

}
