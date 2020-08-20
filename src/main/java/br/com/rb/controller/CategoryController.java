package br.com.rb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rb.entity.Category;
import br.com.rb.errorhandling.ErrorInfo;
import br.com.rb.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	@Qualifier("categoryService")
	private CategoryService categoryService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
		Category category = categoryService.getCategory(id);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	@GetMapping("/search/{letter}")
	public ResponseEntity<?> getHighestOccurrenceByLetter(@PathVariable("letter") String letter) {
		Category category = categoryService.getHighestOccurrenceByLetter(letter);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@GetMapping("/listAll")
	public ResponseEntity<?> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorInfo handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
		String errorMessage = "Invalid search parameter: ";
		errorMessage += ex.getValue();
		String errorURL = req.getRequestURL().toString();
		return new ErrorInfo(errorURL, errorMessage);
	}

}
