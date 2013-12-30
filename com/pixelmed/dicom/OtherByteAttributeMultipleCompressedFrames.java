/* Copyright (c) 2001-2013, David A. Clunie DBA Pixelmed Publishing. All rights reserved. */

package com.pixelmed.dicom;

import com.pixelmed.utils.CopyStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

/**
 * <p>A concrete class specializing {@link com.pixelmed.dicom.Attribute Attribute} for
 * Other Byte (OB) attributes whose values are memory or file resident compressed pixel data frames.</p>
 *
 * @see com.pixelmed.dicom.Attribute
 * @see com.pixelmed.dicom.AttributeList
 *
 * @author	dclunie
 */
public class OtherByteAttributeMultipleCompressedFrames extends Attribute {

	private static final String identString = "@(#) $Header: /userland/cvs/pixelmed/imgbook/com/pixelmed/dicom/OtherByteAttributeMultipleCompressedFrames.java,v 1.1 2013/09/19 10:04:41 dclunie Exp $";
	
	protected byte[][] frames;
	protected File[] files;

	/**
	 * <p>Construct an (empty) attribute.</p>
	 *
	 * @param	t	the tag of the attribute
	 */
	private OtherByteAttributeMultipleCompressedFrames(AttributeTag t) {
		super(t);
	}

	/**
	 * <p>Construct an attribute from a set of compressed frames.</p>
	 *
	 * <p>The VL is not required, since it is undefined by definition.</p>
	 *
	 * @param	t			the tag of the attribute
	 * @param	frames		the frames
	 */
	public OtherByteAttributeMultipleCompressedFrames(AttributeTag t,byte[][] frames) {
		super(t);
		this.frames = frames;
		this.files = null;
		valueLength=0xFFFFFFFF;
		valueMultiplicity=1;
	}

	/**
	 * <p>Construct an attribute from a set of compressed frames.</p>
	 *
	 * <p>The VL is not required, since it is undefined by definition.</p>
	 *
	 * @param	t		the tag of the attribute
	 * @param	files	the files containing the compressed bit streams
	 */
	public OtherByteAttributeMultipleCompressedFrames(AttributeTag t,File[] files) {
		super(t);
		this.frames = null;
		this.files = files;
		valueLength=0xFFFFFFFF;
		valueMultiplicity=1;
	}
	
	protected static final AttributeTag itemTag = TagFromName.Item;
	
	protected void writeItemTag(DicomOutputStream o,long length) throws IOException {
		o.writeUnsigned16(itemTag.getGroup());
		o.writeUnsigned16(itemTag.getElement());
		o.writeUnsigned32(length);
	}
	
	protected static final AttributeTag sequenceDelimitationItemTag = TagFromName.SequenceDelimitationItem;
	
	protected void writeSequenceDelimitationItemTag(DicomOutputStream o) throws IOException {
		o.writeUnsigned16(sequenceDelimitationItemTag.getGroup());
		o.writeUnsigned16(sequenceDelimitationItemTag.getElement());
		o.writeUnsigned32(0);
	}

	/**
	 * @param	o
	 * @exception	IOException
	 * @exception	DicomException	if no byte array or files containing the compressed bitstream have been supplied
	 */
	public void write(DicomOutputStream o) throws DicomException, IOException {
		writeBase(o);
		writeItemTag(o,0);	// empty basic offset table
		int nFrames = 0;
		if (files != null) {
			nFrames = files.length;
		}
		else if (frames != null) {
			nFrames = files.length;
		}
		if (nFrames > 0) {
			for (int f=0; f<nFrames; ++f) {
				File file = null;
				byte[] frame = null;
				long frameLength = 0;
				if (files != null) {
					file = files[f];
					frameLength = file.length();
				}
				else {
					frame = frames[f];
					frameLength = frame.length;
				}
				long padding = frameLength % 2;
				long paddedLength = frameLength + padding;
				writeItemTag(o,paddedLength);	// always one fragment per frame at this time :(
				if (file != null) {
					InputStream in = new FileInputStream(file);
					CopyStream.copy(in,o);
					in.close();
				}
				else {
					o.write(frame);
				}
				if (padding > 0) {
					o.write(0);
				}
			}
			writeSequenceDelimitationItemTag(o);
		}
		else {
			throw new DicomException("No source of compressed pixel data to write");
		}
	}
	
	/**
	 * <p>Get the byte arrays for each frame.</p>
	 *
	 * @return						an array of byte arrays for each frame
	 */
	public byte[][] getFrames() {
		return frames;
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
		frames=null;
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

