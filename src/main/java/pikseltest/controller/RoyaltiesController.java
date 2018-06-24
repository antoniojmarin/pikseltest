package pikseltest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pikseltest.service.RoyaltiesService;
import pikseltest.utils.*;


@RestController
@Validated
@RequestMapping("/royaltymanager")
public class RoyaltiesController 
{
	@Autowired
	RoyaltiesService royaltiesService;


	
	
	
	/**
	 * Resets viewing counters to 0
	 * @param id 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/reset", produces = "application/json")
	public ResponseEntity<Object> reset() 
	{
		royaltiesService.reset();
		
		return getResponseEntity("", HttpStatus.ACCEPTED);
		
	}	


	/**
	 * Adds a view to the episode in the view received
	 * @param view
	 * @return response
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/viewing", produces = "application/json")
	public ResponseEntity<Object> viewing(@RequestBody View view)
	{
		String response = "Required RequestBody is missing";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		if (view != null && view.getEpisode() != null && view.getCustomer() != null) {			
			status = royaltiesService.viewing(view);
			if (status.is2xxSuccessful()) 
				response = "";
			else {
				response = "Episode not found";
			}
		}
		
		return getResponseEntity(response, status);
		
	}	


	/**
	 * Gets a list of royalties of all studios
	 * @return paymentsList
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/payments", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> payments()
	{
		List<Payments> paymentsList = royaltiesService.getPayments();
		
		return getResponseEntity(paymentsList, HttpStatus.OK);		
	}	
	

	
	/**
	 * Gets the payments for the studio recieved by paramenter
	 * @param rOwnerId
	 * @return
	 */  // ajmarin - ver response body, poner etiqueta? 
	@RequestMapping(method = RequestMethod.GET, value = "/payments/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> payment(@PathVariable(value = "id", required = true) String rOwnerId)
	{
		OwnerPayment ownerPayment = royaltiesService.getOwnerPayment(rOwnerId);

		if (ownerPayment == null) {
			return getResponseEntity("RightsOwner not found", HttpStatus.NOT_FOUND);
		}
		return getResponseEntity(ownerPayment, HttpStatus.OK);
		
	}
	
	

	/**
	 * Gets an episode info by id
	 * @param id 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getEpisodeById", produces = "application/json")
	public ResponseEntity<Object> getEpisodeById(@RequestParam(value = "id", required = true) String id) 
	{
		royaltiesService.getEpisodeById(id);
		
		return getResponseEntity("", HttpStatus.ACCEPTED);		
	}
	
	
	/**
	 * Gets an studio info by id
	 * @param id 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getStudioById", produces = "application/json")
	public ResponseEntity<Object> getStudioById(@RequestParam(value = "id", required = true) String id) 
	{
		royaltiesService.getStudioById(id);
		
		return getResponseEntity("", HttpStatus.ACCEPTED);
		
	}
	
	

	
	protected ResponseEntity<Object> getResponseEntity(String key, String value, HttpStatus httpstatus)
	{
		Map<String,Object> map = new HashMap<>();
		map.put(key, value);
		return new ResponseEntity<>(map, httpstatus);
	}

	
	protected ResponseEntity<Object> getResponseEntity(Object o, HttpStatus httpstatus)
	{
		return new ResponseEntity<>(o, httpstatus);
	}

		
	

}
