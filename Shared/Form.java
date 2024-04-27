package Shared;

import java.time.LocalDate;
import java.util.Date;
//NITHIN
public class Form {
    private String requesterFirstName;
    private String requesterLastName;
    private String requesterEmail;
    private Address requesterAddress;
    private String immigrantFirstName;
    private String immigrantLastName;
    private Address placeOfBirth;
    private LocalDate dateOfBirth;
	private String formType;

	public String getRequesterFirstName() {
		return requesterFirstName;
	}
	public void setRequesterFirstName(String requesterFirstName) {
		this.requesterFirstName = requesterFirstName;
	}
	public String getRequesterLastName() {
		return requesterLastName;
	}
	public void setRequesterLastName(String requesterLastName) {
		this.requesterLastName = requesterLastName;
	}
	public String getRequesterEmail() {
		return requesterEmail;
	}
	public void setRequesterEmail(String requesterEmail) {
		this.requesterEmail = requesterEmail;
	}
	public Address getRequesterAddress() {
		return requesterAddress;
	}
	public void setRequesterAddress(Address requesterAddress) {
		this.requesterAddress = requesterAddress;
	}
	public String getImmigrantFirstName() {
		return immigrantFirstName;
	}
	public void setImmigrantFirstName(String immigrantFirstName) {
		this.immigrantFirstName = immigrantFirstName;
	}
	public String getImmigrantLastName() {
		return immigrantLastName;
	}
	public void setImmigrantLastName(String immigrantLastName) {
		this.immigrantLastName = immigrantLastName;
	}
	public Address getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(Address placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
    public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFormType() {
		return formType;
	}
	public void setformType(String formType) {
		this.formType = formType;
	}
  }
