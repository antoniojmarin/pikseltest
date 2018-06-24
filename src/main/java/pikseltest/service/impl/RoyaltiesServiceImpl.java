package pikseltest.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pikseltest.dao.EpisodeRepository;
import pikseltest.dao.StudioRepository;
import pikseltest.model.Episode;
import pikseltest.model.Studio;
import pikseltest.service.RoyaltiesService;
import pikseltest.utils.*;


@Service
public class RoyaltiesServiceImpl implements RoyaltiesService {

	@Autowired
	EpisodeRepository episodeRepository;
	
	@Autowired 
	StudioRepository studioRepository;
	
	@Override
	public void reset() {
		episodeRepository.resetViews();
	}

	
	/**
	 * Service method to add a view of an episode
	 */
	@Override
	public HttpStatus viewing(View view) {
		HttpStatus status = HttpStatus.ACCEPTED;
		Episode ep = episodeRepository.findById(view.getEpisode());	
		
		if (ep == null) {
			// no episode found for the episode guid
			status = HttpStatus.BAD_REQUEST;
		}
		else {
			ep.setViews(ep.getViews() + 1);
			episodeRepository.save(ep);
			
			// tests
//			ep = episodeRepository.findById(view.getEpisode());	
//			System.out.println("episode: " + ep.toString());
		}
		
		return status;
	}

	
	/**
	 * Service method to get payments of studios
	 */
	@Override
	public List<Payments> getPayments() {
		List<Payments> paymentsList = new ArrayList<Payments>();
		Payments payment = null;
		Studio studio = null;
		Long viewings = null;
		Double royalties = null;
		OwnerPayment ownerPayment = null;
		
		Iterable<Studio> studios = studioRepository.findAll();
		Iterator<Studio> it = studios.iterator();
		while (it.hasNext()) {
			studio = it.next();
			payment = new Payments();
			payment.setRightsOwnerId(studio.getId());
			payment.setRightsOwner(studio.getName());
			
			viewings = episodeRepository.getStudioViews(studio.getId());
			payment.setViewings(viewings);
			
			ownerPayment = getOwnerPayment(studio.getId());
			royalties = ownerPayment.getRoyalty();
			payment.setRoyalty(royalties);
			
			paymentsList.add(payment);
			//System.out.println("payment: " + payment.toString());
		}
		
		return paymentsList;
	}

	
	/**
	 * Service method to get a rightsOwner payment
	 */
	@Override
	public OwnerPayment getOwnerPayment(String rOwnerId) {
		OwnerPayment ownerPayment = null;
		
		Studio studio = studioRepository.findById(rOwnerId);
		
		if (studio != null) {
			Long views = episodeRepository.getStudioViews(rOwnerId);
			
			//System.out.println("views: " + views);
			//System.out.println("studio: " + studio);
			
			ownerPayment = new OwnerPayment();
			ownerPayment.setRightsOwner(studio.getName());
			ownerPayment.setViewings(views);
			ownerPayment.setRoyalty(views * studio.getPayment());
			//System.out.println("ownerPayment: " + ownerPayment.toString());
		}
			
		return ownerPayment;
	}


	@Override
	public Episode getEpisodeById(String id) {
		Episode episode = episodeRepository.findById(id);
		if (episode != null)
			System.out.print("episode: " + episode.toString());
		
		return episode;
	}


	@Override
	public Studio getStudioById(String id) {
		Studio studio = studioRepository.findById(id);
		if (studio != null)
			System.out.print("studio: " + studio.toString());
		
		return studio;
	}


	@Override
	public Episode save(Episode episode) {
		return episodeRepository.save(episode);
	}



	@Override
	public Studio save(Studio studio) {
		return studioRepository.save(studio);
	}

	

}
