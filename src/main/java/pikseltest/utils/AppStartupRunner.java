package pikseltest.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import pikseltest.model.Episode;
import pikseltest.model.Studio;
import pikseltest.service.RoyaltiesService;

@Component
public class AppStartupRunner implements ApplicationRunner {
    
	@Autowired
	RoyaltiesService royaltiesService;
	
    public static int counter;
 
    @Override
    public void run(ApplicationArguments args) throws Exception {
        
    	System.out.println("Cargando datos..:");
    	List<Episode> episodes = Utils.loadEpisodes();
    	for (int i = 0; i < episodes.size(); i++) {
    		royaltiesService.save(episodes.get(i));
    		//System.out.println("episode: " + episodes.get(i));
    	}
    	
    	List<Studio> studios = Utils.loadStudios();
    	for (int j = 0; j < studios.size(); j++) {
    		royaltiesService.save(studios.get(j));
    		//System.out.println("studio: " + studios.get(j));
    	}
    }

}