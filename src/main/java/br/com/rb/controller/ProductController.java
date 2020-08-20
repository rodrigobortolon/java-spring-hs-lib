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

import br.com.rb.entity.Product;
import br.com.rb.errorhandling.ErrorInfo;
import br.com.rb.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	@Qualifier("productService")
	private ProductService productService;

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
		Product product = productService.getProduct(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/listByCategory/{id}")
	public ResponseEntity<List<Product>> listByCategory(@PathVariable("id") Long id) {
		List<Product> list = productService.getAllProductsByCategory(id);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
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
