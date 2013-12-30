/* Copyright (c) 2001-2013, David A. Clunie DBA Pixelmed Publishing. All rights reserved. */

package com.pixelmed.anatproc;

/**
 * <p>This class represents a concept that has a coded representation.</p>
 * 
 * @author	dclunie
 */
public class Concept {

	private static final String identString = "@(#) $Header: /userland/cvs/pixelmed/imgbook/com/pixelmed/anatproc/Concept.java,v 1.3 2013/01/02 00:35:14 dclunie Exp $";
	
	protected String conceptUniqueIdentifier;		// usually a UMLS CUID
	
	public String getConceptUniqueIdentifier() { return conceptUniqueIdentifier; }
	
	public Concept(String conceptUniqueIdentifier) {
		this.conceptUniqueIdentifier=conceptUniqueIdentifier;
	}
	
	protected Concept() {};
	
	public boolean equals(Object o) {
		boolean areEqual = false;
		if (this == o) {
			areEqual = true;
		}
		else if (o != null && o instanceof Concept) {
			areEqual = conceptUniqueIdentifier.equals(((Concept)o).conceptUniqueIdentifier);
		}
		return areEqual;
	}
	
	public int hashCode() {
		return conceptUniqueIdentifier.hashCode();
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("\tCUI: ");
		buf.append(conceptUniqueIdentifier);
		buf.append("\n");
		return buf.toString();
	}
}

