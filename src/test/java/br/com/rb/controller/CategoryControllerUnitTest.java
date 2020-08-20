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
import org.springframework.http.MediaType;
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
public class CategoryControllerUnitTest {

	@Autowired
    private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
    public void test_listAll() throws Exception {
        mockMvc.perform(get("/category/listAll").contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	    	.andExpect(jsonPath("$", hasSize(4)))
	        .andExpect(jsonPath("$[0].id", is(1)))
	        .andExpect(jsonPath("$[0].name", is("Alimentos")))
	        .andExpect(jsonPath("$[1].id", is(2)))
	        .andExpect(jsonPath("$[1].name", is("Eletrodomésticos")))
	        .andExpect(jsonPath("$[2].id", is(3)))
	        .andExpect(jsonPath("$[2].name", is("Móveis")))
	    	.andExpect(jsonPath("$[3].id", is(4)))
	    	.andExpect(jsonPath("$[3].name", is("Eletrônicos")));
		
    }
	
	@Test
	public void test_getCategoryById() throws Exception {
		mockMvc.perform(get("/category/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
	        .andExpect(jsonPath("$.name", is("Alimentos")));
	}
	
	@Test
	public void test_getHighestOccurrenceByLetter() throws Exception {
		mockMvc.perform(get("/category/search/o"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(2)))
	        .andExpect(jsonPath("$.name", is("Eletrodomésticos")));
	}
	
	@Test
	public void test_handleTypeMismatchException() throws Exception {
		mockMvc.perform(get("/category/a"))
			.andExpect(status().isBadRequest());
	}
	
}
