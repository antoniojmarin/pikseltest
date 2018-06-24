package pikseltest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="episode")
public class Episode 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAuto;
	

	@NotNull
	private String id;
	@NotNull
	private String name;
	@NotNull
	private String rightsowner;
	@NotNull
	private Long views;
	


	
	public Episode() { this.idAuto=-1L; this.views = 0L; }	



	public Long getIdAuto() {
		return idAuto;
	}


	public void setIdAuto(Long idAuto) {
		this.idAuto = idAuto;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRightsowner() {
		return rightsowner;
	}


	public void setRightsowner(String rightsowner) {
		this.rightsowner = rightsowner;
	}


	public Long getViews() {
		return views;
	}


	public void setViews(Long views) {
		this.views = views;
	}
	
	

	public String toString() {
		return "id: " + this.id + " - name: " + this.name + " - rightsowner: " + this.rightsowner + " - views: " + this.views;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Episode other = (Episode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	
	
}
