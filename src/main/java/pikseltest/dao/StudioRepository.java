package pikseltest.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pikseltest.model.Studio;

@Repository
public interface StudioRepository extends CrudRepository<Studio, Long>{

	@Query("SELECT u FROM Studio u where u.id = :id") 
	public Studio findById(@Param("id") String id);
	

}
