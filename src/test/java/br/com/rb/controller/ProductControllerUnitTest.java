package br.com.rb.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.rb.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class ProductControllerUnitTest {

	@Autowired
    private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
    public void test_listByCategoryAlimentos() throws Exception {
        mockMvc.perform(get("/product/listByCategory/1"))
        .andExpect(status().isOk())
	    		.andExpect(jsonPath("$", hasSize(2)))
	        .andExpect(jsonPath("$[0].id", is(1)))
	        .andExpect(jsonPath("$[0].name", is("Arroz")))
	        .andExpect(jsonPath("$[1].id", is(2)))
	        .andExpect(jsonPath("$[1].name", is("Feijão")));
    }

	@Test
	public void test_listByCategoryEletrodomesticos() throws Exception {
		mockMvc.perform(get("/product/listByCategory/2"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$[0].id", is(3)))
		.andExpect(jsonPath("$[0].name", is("Aspirador de pó")))
		.andExpect(jsonPath("$[1].id", is(4)))
		.andExpect(jsonPath("$[1].name", is("Batedeira")))
		.andExpect(jsonPath("$[2].id", is(5)))
		.andExpect(jsonPath("$[2].name", is("Liquidificador")));
	}

	@Test
	public void test_listByCategoryMoveis() throws Exception {
		mockMvc.perform(get("/product/listByCategory/3"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$[0].id", is(6)))
		.andExpect(jsonPath("$[0].name", is("Sofá")))
		.andExpect(jsonPath("$[1].id", is(7)))
		.andExpect(jsonPath("$[1].name", is("Mesa")))
		.andExpect(jsonPath("$[2].id", is(8)))
		.andExpect(jsonPath("$[2].name", is("Estante")));
	}
	
	@Test
	public void test_listByCategoryEletronicos() throws Exception {
		mockMvc.perform(get("/product/listByCategory/4"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$[0].id", is(9)))
		.andExpect(jsonPath("$[0].name", is("TV Led")))
		.andExpect(jsonPath("$[1].id", is(10)))
		.andExpect(jsonPath("$[1].name", is("SmartPhone LG")))
		.andExpect(jsonPath("$[2].id", is(11)))
		.andExpect(jsonPath("$[2].name", is("Laptop Dell")));
	}
	
	@Test
    public void test_listAll() throws Exception {
        mockMvc.perform(get("/product/listAll"))
        .andExpect(status().isOk())
	    	.andExpect(jsonPath("$", hasSize(11)))
	        .andExpect(jsonPath("$[0].id", is(1)))
	        .andExpect(jsonPath("$[0].name", is("Arroz")))
	        .andExpect(jsonPath("$[1].id", is(2)))
	        .andExpect(jsonPath("$[1].name", is("Feijão")))
	        .andExpect(jsonPath("$[2].id", is(3)))
	        .andExpect(jsonPath("$[2].name", is("Aspirador de pó")))
        	.andExpect(jsonPath("$[3].id", is(4)))
        	.andExpect(jsonPath("$[3].name", is("Batedeira")))
	    	.andExpect(jsonPath("$[4].id", is(5)))
        	.andExpect(jsonPath("$[4].name", is("Liquidificador")))
	    	.andExpect(jsonPath("$[5].id", is(6)))
        	.andExpect(jsonPath("$[5].name", is("Sofá")))
	    	.andExpect(jsonPath("$[6].id", is(7)))
        	.andExpect(jsonPath("$[6].name", is("Mesa")))
	    	.andExpect(jsonPath("$[7].id", is(8)))
        	.andExpect(jsonPath("$[7].name", is("Estante")))
	    	.andExpect(jsonPath("$[8].id", is(9)))
        	.andExpect(jsonPath("$[8].name", is("TV Led")))
	    	.andExpect(jsonPath("$[9].id", is(10)))
        	.andExpect(jsonPath("$[9].name", is("SmartPhone LG")))
	    	.andExpect(jsonPath("$[10].id", is(11)))
        	.andExpect(jsonPath("$[10].name", is("Laptop Dell")));
    }
	
	@Test
	public void test_getProductById() throws Exception {
		mockMvc.perform(get("/product/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
	        .andExpect(jsonPath("$.name", is("Arroz")));
	}
	
	@Test
	public void test_handleTypeMismatchException() throws Exception {
		mockMvc.perform(get("/product/a"))
			.andExpect(status().isBadRequest());
	}
}
