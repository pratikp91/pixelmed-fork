/* Copyright (c) 2001-2013, David A. Clunie DBA Pixelmed Publishing. All rights reserved. */

package com.pixelmed.apps;

import com.pixelmed.dicom.*;
import com.pixelmed.display.*;

import java.io.*;
import java.awt.*; 
import java.awt.color.*; 
import java.awt.image.*;

/**
 * <p>A class of static methods to interconvert floating point pixel data to scaled integer values.</p>
 *
 * @author	dclunie
 */
public class IntegerScalingOfFloatingPointPixelData {
	
	private static final String identString = "@(#) $Header: /userland/cvs/pixelmed/imgbook/com/pixelmed/apps/IntegerScalingOfFloatingPointPixelData.java,v 1.5 2013/09/26 14:21:34 dclunie Exp $";

	public static void reportOnRoundTrip(double input,double slope,double intercept,String message) {
		int scaled = (int)Math.round(((input-intercept)/slope))&0x0000ffff;
		double unscaled = scaled*slope+intercept;
		double delta = Math.abs(unscaled-input);
System.err.println("performIntegerScaling(): "+message+" "+input+", scaled = "+scaled+", unscaled = "+unscaled+", abs error = "+delta);
	}

	/**
	 * <p>Read a DICOM image with grayscale floating point PixelData and scale it to integer values sufficient to represent the dynamic range.</p>
	 *
	 * <p>The dynamic range of the input is mapped to the full range of the short unsigned integer putput pixel values (0 to 65535)</p>
	 *
	 * <p>The scaling values are recorded in Rescale Slope and Intercept.</p>
	 *
	 * @param	inputFileName	the input file name
	 * @param	outputFileName	the output file name
	 */
	public static void performIntegerScaling(String inputFileName,String outputFileName) throws DicomException, FileNotFoundException, IOException {
		AttributeList list = new AttributeList();
		DicomInputStream in = new DicomInputStream(new BufferedInputStream(new FileInputStream(inputFileName)));
		list.read(in);
		in.close();
		
		Attribute srcPixelData = list.get(TagFromName.PixelData);

		if (srcPixelData == null) {
			throw new DicomException("Input file does not contain Pixel Data");
		}
		if (!(srcPixelData instanceof OtherFloatAttribute || srcPixelData instanceof OtherDoubleAttribute)) {
			throw new DicomException("Input file does not contain floating point Pixel Data");
		}

		SourceImage sImg = new SourceImage(list);
		if (sImg == null) {
			throw new DicomException("Input file does not contain an image that can be extracted");
		}
		else if (!sImg.isGrayscale()) {
			throw new DicomException("Input file does not contain grayscale data");
		}
		else {
			double allFramesMin = Double.MAX_VALUE;
			double allFramesMax = Double.MIN_VALUE;
			int nFrames = sImg.getNumberOfBufferedImages();
			for (int f=0; f<nFrames; ++f) {
				sImg.getBufferedImage(f);					// don't want BufferedImage, but has side effect of computing min and max for the specified frame
				double frameMin = sImg.getMinimum();
				if (frameMin < allFramesMin) {
					allFramesMin = frameMin;
				}
				double frameMax = sImg.getMaximum();
				if (frameMax > allFramesMax) {
					allFramesMax = frameMax;
				}
			}
System.err.println("performIntegerScaling(): allFramesMin = "+allFramesMin);
System.err.println("performIntegerScaling(): allFramesMax = "+allFramesMax);
						
			double intercept = allFramesMin;
System.err.println("performIntegerScaling(): intercept = "+intercept);
			double slope = (allFramesMax - allFramesMin) / 65535;
System.err.println("performIntegerScaling(): slope = "+slope+" (1/slope = "+(1/slope)+")");

			reportOnRoundTrip(allFramesMin,slope,intercept,"allFramesMin");
			reportOnRoundTrip(allFramesMax,slope,intercept,"allFramesMax");
			
			int nPixels = sImg.getHeight() * sImg.getWidth() * nFrames;		// grayscale so always one sample per pixel
			short[] dstPixels = new short[nPixels];
			
			if (srcPixelData instanceof OtherFloatAttribute) {
				float[] srcPixels = srcPixelData.getFloatValues();
				for (int i=0; i<nPixels; ++i) {
					dstPixels[i] = (short)Math.round(((srcPixels[i]-intercept)/slope));
					//reportOnRoundTrip(srcPixels[i],slope,intercept,"srcPixels["+i+"]");
				}
			}
			else if (srcPixelData instanceof OtherDoubleAttribute) {
				double[] srcPixels = srcPixelData.getDoubleValues();
				for (int i=0; i<nPixels; ++i) {
					dstPixels[i] = (short)Math.round(((srcPixels[i]-intercept)/slope));
					//reportOnRoundTrip(srcPixels[i],slope,intercept,"srcPixels["+i+"]");
				}
			}
			
			{
				Attribute dstPixelData = new OtherWordAttribute(TagFromName.PixelData);
				dstPixelData.setValues(dstPixels);
				list.put(dstPixelData);

				{ Attribute a = new UnsignedShortAttribute(TagFromName.BitsStored); a.addValue(16); list.put(a); }
				{ Attribute a = new UnsignedShortAttribute(TagFromName.BitsAllocated); a.addValue(16); list.put(a); }
				{ Attribute a = new UnsignedShortAttribute(TagFromName.HighBit); a.addValue(15); list.put(a); }
				{ Attribute a = new UnsignedShortAttribute(TagFromName.PixelRepresentation); a.addValue(0); list.put(a); }
				
				// Note that RescaleIntercept and RescaleSlope are required to be 0 and 1 respectively in MultiframeGrayscaleWordSecondaryCaptureImageStorage :(
				// If we are not creating modality-specific Enhanced MF images, then better at top level than in Pixel Value Transformation Sequence; consider inserting both ? :(
				// Overwriting any existing RescaleIntercept and RescaleSlope and RescaleType attributes ... might they be present and should we account for them ? :(
				
				{ Attribute a = new DecimalStringAttribute(TagFromName.RescaleIntercept); a.addValue(intercept); list.put(a); }
				{ Attribute a = new DecimalStringAttribute(TagFromName.RescaleSlope); a.addValue(slope); list.put(a); }
				{ Attribute a = new LongStringAttribute(TagFromName.RescaleType); a.addValue("US"); list.put(a); }
				
				// clean up SOP Class ...
				String sopClass = Attribute.getSingleStringValueOrEmptyString(list,TagFromName.SOPClassUID);
				{
					if (sopClass.equals(SOPClass.PrivatePixelMedLegacyFloatingPointImageStorage)) {
						sopClass = SOPClass.MultiframeGrayscaleWordSecondaryCaptureImageStorage;
					}
					{ Attribute a = new UniqueIdentifierAttribute(TagFromName.SOPClassUID); a.addValue(sopClass); list.put(a); }
				}
				
				if (SOPClass.isSecondaryCaptureImageStorage(sopClass)) {
					{ Attribute a = new CodeStringAttribute(TagFromName.ConversionType); a.addValue("WSD"); list.put(a); }
				}

				{
					String modality = Attribute.getSingleStringValueOrEmptyString(list,TagFromName.Modality);
					if (modality.equals("")) {
						// could actually attempt to guess modality based on SOP Class here :(
						modality = "OT";
					}
					{ Attribute a = new CodeStringAttribute(TagFromName.Modality); a.addValue(modality); list.put(a); }
				}
				
				{
					String presentationLUTShape = Attribute.getSingleStringValueOrEmptyString(list,TagFromName.PresentationLUTShape);
					if (presentationLUTShape.equals("")) {
						presentationLUTShape = "IDENTITY";
					}
					{ Attribute a = new CodeStringAttribute(TagFromName.PresentationLUTShape); a.addValue(presentationLUTShape); list.put(a); }
				}
				
				ClinicalTrialsAttributes.addContributingEquipmentSequence(list,true/*retainExistingItems*/,
					new CodedSequenceItem("109103","DCM","Modifying Equipment"),
					"PixelMed",														// Manufacturer
					"PixelMed",														// Institution Name
					"Software Development",											// Institutional Department Name
					"Bangor, PA",													// Institution Address
					null,															// Station Name
					"com.pixelmed.apps.IntegerScalingOfFloatingPointPixelData.main()",		// Manufacturer's Model Name
					null,															// Device Serial Number
					VersionAndConstants.getBuildDate(),								// Software Version(s)
					"Scaled floating point Pixel Data to integer");

				list.removeMetaInformationHeaderAttributes();
				FileMetaInformation.addFileMetaInformation(list,TransferSyntax.ExplicitVRLittleEndian,"OURAETITLE");
				list.write(outputFileName,TransferSyntax.ExplicitVRLittleEndian,true,true);
			}
		}
	}
	
	/**
	 * <p>Read a DICOM image with grayscale integer PixelData and scale it to floating point values based on the Rescale Slope and Intercept.</p>
	 *
	 * @param	inputFileName	the input file name
	 * @param	outputFileName	the output file name
	 */
	public static void reverseIntegerScaling(String inputFileName,String outputFileName) throws DicomException, FileNotFoundException, IOException {
		AttributeList list = new AttributeList();
		DicomInputStream in = new DicomInputStream(new BufferedInputStream(new FileInputStream(inputFileName)));
		list.read(in);
		in.close();
		
		Attribute srcPixelData = list.get(TagFromName.PixelData);

		if (srcPixelData == null) {
			throw new DicomException("Input file does not contain Pixel Data");
		}
		if (!(srcPixelData instanceof OtherWordAttribute)) {
			throw new DicomException("Input file does not contain short integer Pixel Data");
		}
		
		int samplesPerPixel = Attribute.getSingleIntegerValueOrDefault(list,TagFromName.SamplesPerPixel,1);
		int rows = Attribute.getSingleIntegerValueOrDefault(list,TagFromName.Rows,0);
		int columns = Attribute.getSingleIntegerValueOrDefault(list,TagFromName.Columns,0);
		int nFrames = Attribute.getSingleIntegerValueOrDefault(list,TagFromName.NumberOfFrames,1);
		int nPixels = rows * columns * nFrames * samplesPerPixel;
		
		Attribute aRescaleSlope = list.get(TagFromName.RescaleSlope);
		Attribute aRescaleIntercept = list.get(TagFromName.RescaleIntercept);

		if (aRescaleSlope == null || aRescaleSlope.getVM() == 0 || aRescaleIntercept == null || aRescaleIntercept.getVM() == 0) {
			throw new DicomException("Input file missing RescaleSlope or RescaleIntercept");
		}
		else if (nPixels == 0) {
			throw new DicomException("Input file missing Rows or Columns");
		}
		else {
			double slope     = aRescaleSlope.getSingleDoubleValueOrDefault(1);
			double intercept = aRescaleIntercept.getSingleDoubleValueOrDefault(0);
			
			short[] srcPixels = srcPixelData.getShortValues();
			float[] dstPixels = new float[nPixels];
			
			for (int i=0; i<nPixels; ++i) {
				dstPixels[i] = (float)((srcPixels[i]&0x0000ffff)*slope+intercept);	// should account for signedness of input for general case rather than roundtrip from this class ? :(
			}
			
			{
				Attribute dstPixelData = new OtherFloatAttribute(TagFromName.PixelData);
				dstPixelData.setValues(dstPixels);
				list.put(dstPixelData);

				{ Attribute a = new UnsignedShortAttribute(TagFromName.BitsStored); a.addValue(32); list.put(a); }
				{ Attribute a = new UnsignedShortAttribute(TagFromName.BitsAllocated); a.addValue(32); list.put(a); }
				{ Attribute a = new UnsignedShortAttribute(TagFromName.HighBit); a.addValue(31); list.put(a); }
				{ Attribute a = new UnsignedShortAttribute(TagFromName.PixelRepresentation); a.addValue(0); list.put(a); }
								
				{ Attribute a = new DecimalStringAttribute(TagFromName.RescaleIntercept); a.addValue(0); list.put(a); }
				{ Attribute a = new DecimalStringAttribute(TagFromName.RescaleSlope); a.addValue(1); list.put(a); }
				{ Attribute a = new DecimalStringAttribute(TagFromName.RescaleType); a.addValue("US"); list.put(a); }

				// clean up SOP Class ...
				{
					String sopClass = Attribute.getSingleStringValueOrEmptyString(list,TagFromName.SOPClassUID);
					if (sopClass.equals(SOPClass.MultiframeGrayscaleWordSecondaryCaptureImageStorage)) {
						sopClass = SOPClass.PrivatePixelMedLegacyFloatingPointImageStorage;
					}
					{ Attribute a = new UniqueIdentifierAttribute(TagFromName.SOPClassUID); a.addValue(sopClass); list.put(a); }
				}
				
				ClinicalTrialsAttributes.addContributingEquipmentSequence(list,true/*retainExistingItems*/,
					new CodedSequenceItem("109103","DCM","Modifying Equipment"),
					"PixelMed",														// Manufacturer
					"PixelMed",														// Institution Name
					"Software Development",											// Institutional Department Name
					"Bangor, PA",													// Institution Address
					null,															// Station Name
					"com.pixelmed.apps.IntegerScalingOfFloatingPointPixelData.main()",		// Manufacturer's Model Name
					null,															// Device Serial Number
					VersionAndConstants.getBuildDate(),								// Software Version(s)
					"Scaled integer Pixel Data to floating point");

				list.removeMetaInformationHeaderAttributes();
				FileMetaInformation.addFileMetaInformation(list,TransferSyntax.ExplicitVRLittleEndian,"OURAETITLE");
				list.write(outputFileName,TransferSyntax.ExplicitVRLittleEndian,true,true);
			}
		}
	}

	/**
	 * <p>Read a DICOM image input format file and convert floating point pixel data to scaled integer values or vice versa.</p>
	 *
	 * @param	arg	two or three parameters, an optional direction argument (toFloat|toInt, case insensitive, defaults to toInt), the inputFile, and the outputFile
	 */
	public static void main(String arg[]) {
		try {
			boolean bad = true;
			boolean toInt = true;
			String inputFileName = null;
			String outputFileName = null;
			if (arg.length == 2) {
				bad = false;
				toInt = true;
				inputFileName = arg[0];
				outputFileName = arg[1];
			}
			else if (arg.length == 3) {
				inputFileName = arg[1];
				outputFileName = arg[2];
				if (arg[0].toLowerCase(java.util.Locale.US).equals("toint")) {
					bad = false;
					toInt = true;
				}
				else if (arg[0].toLowerCase(java.util.Locale.US).equals("tofloat")) {
					bad = false;
					toInt = false;
				}
			}
			if (bad) {
				System.err.println("usage: IntegerScalingOfFloatingPointPixelData [toFloat|toInt] inputFile outputFile");
			}
			else {
				if (toInt) {
					performIntegerScaling(inputFileName,outputFileName);
				}
				else {
					reverseIntegerScaling(inputFileName,outputFileName);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
