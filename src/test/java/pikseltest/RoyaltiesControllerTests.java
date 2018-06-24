package pikseltest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;


import pikseltest.service.RoyaltiesService;
import pikseltest.utils.Payments;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = { "pikseltest" })
public class RoyaltiesControllerTests
{

	@Autowired
	private MockMvc mvc;


	@Autowired
	RoyaltiesService royaltiesService;
 
    @Autowired
    WebApplicationContext wac;


	@Before
	public void setup() 
	{
	    MockitoAnnotations.initMocks(this);
	    this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
	}

	/**
	 * Test for reset method with expected result Ok
	 * @throws Exception
	 */
	@Test
	public void resetOKTest() throws Exception 
	{

		String res = this.mvc.perform(post("/royaltymanager/reset"))
				             .andExpect(status().isAccepted())
				             .andReturn().getResponse().getContentAsString();
		
		assertTrue(res.equals(""));
	}
	

	/**
	 * Test for viewing method with expected result Accepted, 202
	 * @throws Exception
	 */
	@Test
	public void viewingOKTest() throws Exception 
	{		
		String jsonView = "{\"episode\": \"6a1db5d6610a4c048d3df9a6268c68dc\",\"customer\": \"Antonio\"}";

		String res = this.mvc.perform(post("/royaltymanager/viewing")
							.contentType(MediaType.APPLICATION_JSON).content(jsonView))
				             .andExpect(status().isAccepted())
				             .andReturn().getResponse().getContentAsString();
		
		assertTrue(res.equals(""));
	}
	
	
	/**
	 * Test for viewing method with no parameter, expected result 400, bad request
	 */
	@Test
	public void viewingKONoBodyTest() throws Exception 
	{
		String jsonView = "";
		
		String res = this.mvc.perform(post("/royaltymanager/viewing")
				.contentType(MediaType.APPLICATION_JSON).content(jsonView))
	             .andExpect(status().isBadRequest())
	             .andReturn().getResponse().getContentAsString();

		assertTrue(res.equals(""));
	}
	
	
	/**
	 * Test for viewing method, where episode id is not found in the system, expected result 400, bad request
	 * @throws Exception
	 */
	@Test
	public void viewingKOEpisodeTest() throws Exception 
	{
		String jsonView = "{\"episode\": \"WrongEpisode\",\"customer\": \"Antonio\"}";
		
		String res = this.mvc.perform(post("/royaltymanager/viewing")
				.contentType(MediaType.APPLICATION_JSON).content(jsonView))
	             .andExpect(status().isBadRequest())
	             .andReturn().getResponse().getContentAsString();
		
		assertTrue(res.equals("Episode not found"));
	}
	

	/**
	 * Test for general payments method, expected result 200, ok
	 * @throws Exception
	 */
	@Test
	public void paymentsOKTest() throws Exception 
	{
		String res = this.mvc.perform(get("/royaltymanager/payments"))
				             .andExpect(status().isOk())
				             .andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        
		List<Payments> paymentsList = Arrays.asList(objectMapper.readValue(res, Payments[].class));
		assertNotNull(paymentsList);
	}
	

	/**
	 * Test for owner payment method for an existing rightsOwner id, expected result 200, ok
	 * @throws Exception
	 */
	@Test
	public void ownerPaymentOKTest() throws Exception 
	{
		String rightsOwnerId = "665115721c6f44e49be3bd3e26606026";
		String res = this.mvc.perform(get("/royaltymanager/payments/{id}", rightsOwnerId))
				             .andExpect(status().isOk())
				             .andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        
		Payments payment = objectMapper.readValue(res, Payments.class);
		assertNotNull(payment);
	}
	

	/**
	 * Test for owner payment method for a non-existing rightsOwner id, expected result 404 , not found
	 * @throws Exception
	 */
	@Test
	public void ownerPaymentKOTest() throws Exception 
	{
		String rightsOwnerId = "1234";
		String res = this.mvc.perform(get("/royaltymanager/payments/{id}", rightsOwnerId))
				             .andExpect(status().isNotFound())
				             .andReturn().getResponse().getContentAsString();

		assertTrue(res.equals("RightsOwner not found"));
	}

}
