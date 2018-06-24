package pikseltest.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pikseltest.model.Episode;
import pikseltest.model.Studio;
import pikseltest.service.RoyaltiesService;

public class Utils {
	
	
	@Autowired
	RoyaltiesService royaltiesService;
	
	
	private static String JSON_PATH = "./src/main/resources/static/json/"; 
	

	
	/**
	 * loads studios.json
	 */
	public static List<Studio> loadStudios() {
		
		//read json file data to String
		byte[] bStudios = null;
		try {
			bStudios = Files.readAllBytes(Paths.get(JSON_PATH + "studios.json"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sStudios = null;
		try {
			sStudios = new String(bStudios,"UTF-8");
			//System.out.print(sStudios);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			
		List<Studio> studios = getAllStudios(sStudios);			
		//System.out.println("studios: " + studios);
		
		return studios;
		
	}
	


	private static List<Studio> getAllStudios(String jsonString) {        
	    List<Studio> studios = new java.util.ArrayList<Studio>();                               
	        try {
	            if (jsonString.length() == 0) {
	                throw new Exception("Empty json!");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        ObjectMapper objectMapper = new ObjectMapper();

	        try {
	            JsonNode rootNode = objectMapper.readTree(jsonString);
	            JsonNode resultNode = rootNode.findPath("studios");
	            if (resultNode.size() > 0) {
	            	//System.out.println("resultNode.size: " + resultNode.size());
	                for (int i = 0; i < resultNode.size(); i++) {
	                    studios.add(objectMapper.readValue(resultNode.get(i).toString(), Studio.class));
	                }
	            }
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    return studios;
	}
	
	/**
	 * loads episodes.json
	 */
	public static List<Episode> loadEpisodes() {
		
		//read json file data to String
		byte[] bEpisodes = null;
		try {
			bEpisodes = Files.readAllBytes(Paths.get(JSON_PATH + "episodes.json"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sEpisodes = null;
		try {
			sEpisodes = new String(bEpisodes,"UTF-8");
			//System.out.print(sEpisodes);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			
		List<Episode> episodes = getAllEpisodes(sEpisodes);			
		//System.out.println("episodes: " + episodes);
		
		return episodes;
		
	}
	
	

	private static List<Episode> getAllEpisodes(String jsonString) {        
	    List<Episode> episodes = new java.util.ArrayList<Episode>();                               
	        try {
	            if (jsonString.length() == 0) {
	                throw new Exception("Empty json!");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        ObjectMapper objectMapper = new ObjectMapper();

	        try {
	            JsonNode rootNode = objectMapper.readTree(jsonString);
	            JsonNode resultNode = rootNode.findPath("episodes");
	            if (resultNode.size() > 0) {
	            	System.out.println("resultNode.size: " + resultNode.size());
	                for (int i = 0; i < resultNode.size(); i++) {
	                    episodes.add(objectMapper.readValue(resultNode.get(i).toString(), Episode.class));
	                }
	            }
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    return episodes;
	}
	
	

}
