package pikseltest.utils;

/**
 * models responseBody for payments/{id} method
 * @author Antonio
 *
 */
public class OwnerPayment {
	

	private String rightsOwner;
	private Double royalty;
	private Long viewings;
	
	
	/**
	 * getter for rightsOwner
	 * @return
	 */
	public String getRightsOwner() {
		return rightsOwner;
	}
	
	/**
	 * setter for rightsOwner
	 * @param rightsOwner
	 */
	public void setRightsOwner(String rightsOwner) {
		this.rightsOwner = rightsOwner;
	}
	
	/**
	 * getter for royalty
	 * @return
	 */
	public Double getRoyalty() {
		return royalty;
	}
	
	/**
	 * setter for royalty
	 * @param royalty
	 */
	public void setRoyalty(Double royalty) {
		this.royalty = royalty;
	}
	
	/**
	 * getter for viewings
	 * @return
	 */
	public Long getViewings() {
		return viewings;
	}
	
	/**
	 * setter for viewings
	 * @param viewings
	 */
	public void setViewings(Long viewings) {
		this.viewings = viewings;
	}
	
	public String toString(OwnerPayment ownerPayment) {
		return "rightsOwner: " + this.rightsOwner + " - royalty: " + this.royalty + " - viewings: " + this.viewings;
	}
	

}
