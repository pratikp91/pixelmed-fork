/* Copyright (c) 2001-2013, David A. Clunie DBA Pixelmed Publishing. All rights reserved. */

package com.pixelmed.dicom;

import java.io.*;

/**
 * <p>A concrete class specializing {@link com.pixelmed.dicom.Attribute Attribute} for
 * Age String (AS) attributes.</p>
 *
 * <p>Though an instance of this class may be created
 * using its constructors, there is also a factory class, {@link com.pixelmed.dicom.AttributeFactory AttributeFactory}.</p>
 *
 * @see com.pixelmed.dicom.Attribute
 * @see com.pixelmed.dicom.AttributeFactory
 * @see com.pixelmed.dicom.AttributeList
 *
 * @author	dclunie
 */
public class AgeStringAttribute extends StringAttribute {

	private static final String identString = "@(#) $Header: /userland/cvs/pixelmed/imgbook/com/pixelmed/dicom/AgeStringAttribute.java,v 1.9 2013/09/09 15:57:59 dclunie Exp $";

	/**
	 * <p>Construct an (empty) attribute.</p>
	 *
	 * @param	t	the tag of the attribute
	 */
	public AgeStringAttribute(AttributeTag t) {
		super(t);
	}

	/**
	 * <p>Read an attribute from an input stream.</p>
	 *
	 * @param	t			the tag of the attribute
	 * @param	vl			the value length of the attribute
	 * @param	i			the input stream
	 * @exception	IOException		if an I/O error occurs
	 * @exception	DicomException	if a DICOM parsing error occurs
	 */
	public AgeStringAttribute(AttributeTag t,long vl,DicomInputStream i) throws IOException, DicomException {
		super(t,vl,i);
	}

	/**
	 * <p>Read an attribute from an input stream.</p>
	 *
	 * @param	t			the tag of the attribute
	 * @param	vl			the value length of the attribute
	 * @param	i			the input stream
	 * @exception	IOException		if an I/O error occurs
	 * @exception	DicomException	if a DICOM parsing error occurs
	 */
	public AgeStringAttribute(AttributeTag t,Long vl,DicomInputStream i) throws IOException, DicomException {
		super(t,vl,i);
	}

	/**
	 * <p>Get the value representation of this attribute (AS).</p>
	 *
	 * @return	'A','S' in ASCII as a two byte array; see {@link com.pixelmed.dicom.ValueRepresentation ValueRepresentation}
	 */
	public byte[] getVR() { return ValueRepresentation.AS; }

}

