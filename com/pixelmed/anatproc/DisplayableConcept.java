/* Copyright (c) 2001-2013, David A. Clunie DBA Pixelmed Publishing. All rights reserved. */

package com.pixelmed.anatproc;

import com.pixelmed.utils.StringUtilities;

/**
 * <p>This class represents a concept that may be displayed, for example as a menu item in a pick list.</p>
 * 
 * @author	dclunie
 */
public class DisplayableConcept extends CodedConcept {

	private static final String identString = "@(#) $Header: /userland/cvs/pixelmed/imgbook/com/pixelmed/anatproc/DisplayableConcept.java,v 1.2 2013/01/02 00:35:14 dclunie Exp $";
	
	protected String[] shortcutMenuEntry;				// e.g. "Wrist"
	protected String[] fullyQualifiedMenuEntry;			// e.g. "Limb","Upper","Wrist"
	
	public DisplayableConcept(String conceptUniqueIdentifier,String conceptIdentifier,
			String codingSchemeDesignator,String legacyCodingSchemeDesignator,String codingSchemeVersion,String codeValue,String codeMeaning,String codeStringEquivalent,String[] synonynms,
			String[] shortcutMenuEntry,String[] fullyQualifiedMenuEntry
			) {
		super(conceptUniqueIdentifier,conceptIdentifier,codingSchemeDesignator,legacyCodingSchemeDesignator,codingSchemeVersion,codeValue,codeMeaning,codeStringEquivalent,synonynms);
		this.shortcutMenuEntry=shortcutMenuEntry;
		this.fullyQualifiedMenuEntry=fullyQualifiedMenuEntry;
	}
	
	protected DisplayableConcept() {};
	
	public String[] getShortcutMenuEntry() { return shortcutMenuEntry; }
	
	public String[] getFullyQualifiedMenuEntry() { return fullyQualifiedMenuEntry; }
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toString());
		buf.append("\tshortcutMenuEntry: ");
		buf.append(StringUtilities.toString(shortcutMenuEntry));
		buf.append("\n");
		buf.append("\tfullyQualifiedMenuEntry: ");
		buf.append(StringUtilities.toString(fullyQualifiedMenuEntry));
		buf.append("\n");
		return buf.toString();
	}
}


	
