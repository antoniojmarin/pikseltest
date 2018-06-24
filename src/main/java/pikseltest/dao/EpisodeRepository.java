package pikseltest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pikseltest.model.*;

@Repository
public interface EpisodeRepository extends CrudRepository<Episode, Long>{

	@Query("SELECT e FROM Episode e where e.id = :id") 
	public Episode findById(@Param("id") String id);
	
	@Query("SELECT e FROM Episode e WHERE e.rightsowner = :id")
	public List<Episode> findByRightsOwner(@Param("id") String id);
	
	@Query("SELECT sum(e.views) FROM Episode e WHERE e.rightsowner = :id")
	public Long getStudioViews(@Param("id") String id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Episode ep set ep.views = 0")
	public void resetViews();

}
