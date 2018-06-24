package pikseltest.utils;

public class Payments {


	private String rightsOwnerId;
	private String rightsOwner;
	private Double royalty;
	private Long viewings;
	
	
	/**
	 * getter for rightsOwnerId
	 * @return
	 */
	public String getRightsOwnerId() {
		return rightsOwnerId;
	}
	
	/**
	 * setter for rightsOwnerId
	 * @param rightsOwnerId
	 */
	public void setRightsOwnerId(String rightsOwnerId) {
		this.rightsOwnerId = rightsOwnerId;
	}
	
	/**
	 * getter for rightsOwner
	 * @return
	 */
	public String getRightsOwner() {
		return rightsOwner;
	}
	
	/**
	 * setter for rigthsOwner
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
	
	
}
