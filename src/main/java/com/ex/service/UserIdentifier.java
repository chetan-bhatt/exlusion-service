package com.ex.service;

/**
 * The {@code UserIdentifier} class serves the purpose of being a key in the
 * {@link java.util.HashMap}. To serve the above purpose, this class has been
 * designed in such a way that the objects of this class are immutable in
 * nature.
 * 
 * @author cbhatt
 *
 */
public final class UserIdentifier {

	private String dateOfBirth;

	private String ssn;

	public UserIdentifier(String dateOfBirth, String ssn) {
		this.dateOfBirth = dateOfBirth;
		this.ssn = ssn;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getSsn() {
		return ssn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserIdentifier other = (UserIdentifier) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		return true;
	}
}
