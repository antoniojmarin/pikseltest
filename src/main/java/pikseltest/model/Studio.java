package pikseltest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="studio")
public class Studio {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idStudio;
	

	@NotNull
	private String id;
	@NotNull
	private String name;
	@NotNull
	private Double payment;
	
	
	public Long getIdStudio() {
		return idStudio;
	}
	public void setIdStudio(Long idStudio) {
		this.idStudio = idStudio;
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
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}



	public String toString() {
		return "id: " + this.id + " - name: " + this.name + " - payment: " + this.payment;
	}
}
