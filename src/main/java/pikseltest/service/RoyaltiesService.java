package pikseltest.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import pikseltest.model.Episode;
import pikseltest.model.Studio;
import pikseltest.utils.*;


public interface RoyaltiesService {

	/**
	 * gets an episode by id
	 * @param id
	 */
	public Episode getEpisodeById(String id);

	/**
	 * gets a studio by id
	 * @param id
	 */
	public Studio getStudioById(String id);
	
	/**
	 * resets viewing counters to 0
	 */
	public void reset();
	
	/**
	 * Adds a view to the episode
	 * @param view
	 */
	public HttpStatus viewing(View view);
	

	/**
	 * Generates list of payments for studios
	 * @return
	 */
	public List<Payments> getPayments();
	
	
	/**
	 * Payment for rOwnerId
	 * @param rOwnerId
	 * @return
	 */
	public OwnerPayment getOwnerPayment(String rOwnerId);
	
	/**
	 * save an episode info
	 * @param episode
	 * @return
	 */
	public Episode save(Episode episode);
	
	/**
	 * save an studio info
	 * @param studio
	 * @return
	 */
	public Studio save(Studio studio);
	
	
}
