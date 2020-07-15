package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Book;
import com.cg.entity.Category;
import com.cg.service.CategoryService;
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService service;
	
	@PostMapping("/create")
	public ResponseEntity<Category> addCategory(@RequestBody Category category ) {
			Category cat=service.addCategory(category);
			ResponseEntity<Category> responseEntity = new ResponseEntity<Category>(cat,HttpStatus.OK);
			return responseEntity;
		}

	// List Particular category data
			@GetMapping("/category/{CategoryId}")
			private ResponseEntity<Category> getCategory(@PathVariable("CategoryId") int id) {
				Category cat=service.getCategoryById(CategoryId);
				if (cat == null) {
					throw new IdNotFoundException("C does not exist,so we couldn't fetch details");
				} else {
					return new ResponseEntity<Category>(cat, new HttpHeaders(), HttpStatus.OK);
				}
			}

			// List All category data
			@GetMapping("/GetAllCategory")
			private ResponseEntity<List<Category>> getAllCategory() {
				List<Category> catlist = serviceobj.getAllCategory();
				return new ResponseEntity<List<Category>>(catlist, new HttpHeaders(), HttpStatus.OK);

			}
	
	@PostMapping("/createbook")
	public Book addBook(@RequestBody Book book ) {
			Book b=service.addBook(book);
			//ResponseEntity<Book> responseEntity = new ResponseEntity<Book>(b,HttpStatus.OK);
			return b;
		}
	
	@PutMapping("/assignBookToCat/{CategoryId}/{book_id}")
	public Book assignbooktoC(@PathVariable(value="CategoryId")int CategoryId,
			@PathVariable(value="book_id")int book_id) {
		return service.btoC(CategoryId, book_id);
		
	}
	
	@PutMapping("/UpdateCategory")
	public Category updateC(@RequestBody Category category) {
		return service.UpdateCategory(category);
		
	}
	
	
}
