/* Copyright (c) 2001-2012, David A. Clunie DBA Pixelmed Publishing. All rights reserved. */

package com.pixelmed.dicom;

import java.io.*;

import java.util.zip.GZIPInputStream;

import com.pixelmed.utils.CopyStream;

/**
 * <p>A concrete class specializing {@link com.pixelmed.dicom.Attribute Attribute} for
 * Other Byte (OB) attributes whose values are not memory resident but rather are stored in multiple compressed files on disk.</p>
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
public class OtherByteAttributeMultipleCompressedFilesOnDisk extends Attribute {

	private static final String identString = "@(#) $Header: /userland/cvs/pixelmed/imgbook/com/pixelmed/dicom/OtherByteAttributeMultipleCompressedFilesOnDisk.java,v 1.1 2012/12/15 16:56:23 dclunie Exp $";
	
	protected File[] files;

	/**
	 * <p>Construct an (empty) attribute.</p>
	 *
	 * @param	t	the tag of the attribute
	 */
	public OtherByteAttributeMultipleCompressedFilesOnDisk(AttributeTag t) {
		super(t);
	}

	/**
	 * <p>Read an attribute from a set of compressed files.</p>
	 *
	 * @param	t			the tag of the attribute
	 * @param	vl			the value length of the attribute
	 * @param	files		the input files
	 * @exception	IOException
	 * @exception	DicomException
	 */
	public OtherByteAttributeMultipleCompressedFilesOnDisk(AttributeTag t,long vl,File[] files) throws IOException, DicomException {
		super(t);
		doCommonConstructorStuff(files,vl);
	}

	/**
	 * <p>Read an attribute from a set of compressed files.</p>
	 *
	 * @param	t			the tag of the attribute
	 * @param	vl			the value length of the attribute
	 * @param	fileNames	the input files
	 * @exception	IOException
	 * @exception	DicomException
	 */
	public OtherByteAttributeMultipleCompressedFilesOnDisk(AttributeTag t,long vl,String[] fileNames) throws IOException, DicomException {
		super(t);
		File[] files = new File[fileNames.length];
		for (int i=0; i<fileNames.length; ++i) {
			files[i] = new File(fileNames[i]);
		}
		doCommonConstructorStuff(files,vl);
	}

	/**
	 * @param	files		the input files
	 * @param	vl			the value length of the attribute
	 * @exception	IOException
	 * @exception	DicomException
	 */
	private void doCommonConstructorStuff(File[] files,long vl) throws IOException {
		this.files = files;
		valueLength=vl;
	}

	/***/
	public long getPaddedVL() {
		long vl = getVL();
		if (vl%2 != 0) ++vl;
		return vl;
	}
	
	/**
	 * @param	o
	 * @exception	IOException
	 * @exception	DicomException
	 */
	public void write(DicomOutputStream o) throws DicomException, IOException {
		writeBase(o);
		if (valueLength > 0) {
			for (int i=0; i<files.length; ++i) {
				File file = files[i];
				InputStream in = new GZIPInputStream(new FileInputStream(file));
				CopyStream.copy(in,o);
				in.close();
			}
			long npad = getPaddedVL() - valueLength;
			while (npad-- > 0) o.write(0x00);
		}
	}
	
	/***/
	public String toString(DicomDictionary dictionary) {
		StringBuffer str = new StringBuffer();
		str.append(super.toString(dictionary));
		str.append(" []");		// i.e. don't really dump values ... too many
		return str.toString();
	}

	/**
	 * @exception	DicomException
	 */
	public void removeValues() {
		files=null;
		valueMultiplicity=0;
		valueLength=0;
	}

	/**
	 * <p>Get the value representation of this attribute (OB).</p>
	 *
	 * @return	'O','B' in ASCII as a two byte array; see {@link com.pixelmed.dicom.ValueRepresentation ValueRepresentation}
	 */
	public byte[] getVR() { return ValueRepresentation.OB; }
}

