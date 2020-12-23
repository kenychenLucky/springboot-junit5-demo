package com.dachan.integration.junitdemo.demo;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DemoApplicationTests {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Test
	void contextLoads() {
	}

	@BeforeAll
	public static void beforeAll(){
		System.out.println("beforeAll");
	}

	@BeforeEach
	public void beforeEach(){
		System.out.println("beforeEach.");
	}

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
	}

	@AfterEach
	public void afterEach(){
		System.out.println("afterEach");
	}

	@AfterAll
	public static void afterAll(){
		System.out.println("afterAll");
	}

	@Test
	public void testTwo() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/index/getData")
				.param("searchPhrase","ABC")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();

		//mvcResult.getResponse().getStatus();
		int status =response.getStatus();
		String content = response.getContentAsString();
		System.out.println("status:" + status + ",content:" + content);
		Assert.assertEquals(content,"{\"status\" : \"200\", \"searchPhrase\" : \"ABC\"}");
	}
	@CsvFileSource()
	@Test
	public void testSearchBy() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/index/getVersion")
				.param("searchBy","kenychen")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();

		//mvcResult.getResponse().getStatus();
		int status =response.getStatus();
		String content = response.getContentAsString();
		System.out.println("status:" + status + ",content:" + content);
		Assert.assertEquals(content,"{\"version\" : \"2020.12.22 Version Super\", \"searchBy\" : \"kenychen\"}");
	}

	@Test
	@DisplayName("Test NotNull")
	public void testNotNull() {
		assertNotNull(new Object());
	}

	@ParameterizedTest
	@CsvSource({
			"keny,1",
			"alibaba,2",
			"sougou,3"
	})
	public void testSearchBy(String searchInfo,int rank)  throws Exception {


		RequestBuilder request = MockMvcRequestBuilders.get("/index/getVersion")
				.param("searchBy",searchInfo)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();

		//mvcResult.getResponse().getStatus();
		int status =response.getStatus();
		String content = response.getContentAsString();
		System.out.println("status:" + status + ",content:" + content);
		Assert.assertEquals(content,"{\"version\" : \"2020.12.22 Version Super\", \"searchBy\" : \""+searchInfo+"\"}");
	}

}
