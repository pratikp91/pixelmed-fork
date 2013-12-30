/* Copyright (c) 2001-2012, David A. Clunie DBA Pixelmed Publishing. All rights reserved. */

package com.pixelmed.dicom;

/**
 * <p>Standard DICOM PET color palette.</p>
 *
 * @author	dclunie
 */
public class ColorPalettePET extends ColorPalette {
	private static final String identString = "@(#) $Header: /userland/cvs/pixelmed/imgbook/com/pixelmed/dicom/ColorPalettePET.java,v 1.3 2012/07/02 22:30:01 dclunie Exp $";
	
	protected static String sopInstanceUIDStatic = "1.2.840.10008.1.5.2";
	protected static String contentLabelStatic = "PET";
	protected static String contentDescriptionStatic = "PET";
	protected static String contentCreatorNameStatic = "PixelMed^Publishing";
	protected static String referenceEncodedInstanceURLStatic = "ftp://medical.nema.org/Medical/Dicom/Palettes/pet.dcm";
	
	protected static String[] alternateContentDescriptionStatic = { "TEP", "PET" };
	protected static String[] alternateContentLanguageCodeValueStatic = { "fr", "de" };
	protected static String[] alternateContentLanguageCodeMeaningStatic = { "French", "German" };
	
	protected static byte[] redStaticArray = {
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)0,
		(byte)1,
		(byte)3,
		(byte)5,
		(byte)7,
		(byte)9,
		(byte)11,
		(byte)13,
		(byte)15,
		(byte)17,
		(byte)19,
		(byte)21,
		(byte)23,
		(byte)25,
		(byte)27,
		(byte)29,
		(byte)31,
		(byte)33,
		(byte)35,
		(byte)37,
		(byte)39,
		(byte)41,
		(byte)43,
		(byte)45,
		(byte)47,
		(byte)49,
		(byte)51,
		(byte)53,
		(byte)55,
		(byte)57,
		(byte)59,
		(byte)61,
		(byte)63,
		(byte)65,
		(byte)67,
		(byte)69,
		(byte)71,
		(byte)73,
		(byte)75,
		(byte)77,
		(byte)79,
		(byte)81,
		(byte)83,
		(byte)85,
		(byte)86,
		(byte)88,
		(byte)90,
		(byte)92,
		(byte)94,
		(byte)96,
		(byte)98,
		(byte)100,
		(byte)102,
		(byte)104,
		(byte)106,
		(byte)108,
		(byte)110,
		(byte)112,
		(byte)114,
		(byte)116,
		(byte)118,
		(byte)120,
		(byte)122,
		(byte)124,
		(byte)126,
		(byte)128,
		(byte)130,
		(byte)132,
		(byte)134,
		(byte)136,
		(byte)138,
		(byte)140,
		(byte)142,
		(byte)144,
		(byte)146,
		(byte)148,
		(byte)150,
		(byte)152,
		(byte)154,
		(byte)156,
		(byte)158,
		(byte)160,
		(byte)162,
		(byte)164,
		(byte)166,
		(byte)168,
		(byte)170,
		(byte)171,
		(byte)173,
		(byte)175,
		(byte)177,
		(byte)179,
		(byte)181,
		(byte)183,
		(byte)185,
		(byte)187,
		(byte)189,
		(byte)191,
		(byte)193,
		(byte)195,
		(byte)197,
		(byte)199,
		(byte)201,
		(byte)203,
		(byte)205,
		(byte)207,
		(byte)209,
		(byte)211,
		(byte)213,
		(byte)215,
		(byte)217,
		(byte)219,
		(byte)221,
		(byte)223,
		(byte)225,
		(byte)227,
		(byte)229,
		(byte)231,
		(byte)233,
		(byte)235,
		(byte)237,
		(byte)239,
		(byte)241,
		(byte)243,
		(byte)245,
		(byte)247,
		(byte)249,
		(byte)251,
		(byte)253,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255,
		(byte)255
	};
	
	protected static byte[] greenStaticArray = {
		(byte)0,
		(byte)2,
		(byte)4,
		(byte)6,
		(byte)8,
		(byte)10,
		(byte)12,
		(byte)14,
		(byte)16,
		(byte)18,
		(byte)20,
		(byte)22,
		(byte)24,
		(byte)26,
		(byte)28,
		(byte)30,
		(byte)32,
		(byte)34,
		(byte)36,
		(byte)38,
		(byte)40,
		(byte)42,
		(byte)44,
		(byte)46,
		(byte)48,
		(byte)50,
		(byte)52,
		(byte)54,
		(byte)56,
		(byte)58,
		(byte)60,
		(byte)62,
		(byte)65,
		(byte)67,
		(byte)69,
		(byte)71,
		(byte)73,
		(byte)75,
		(byte)77,
		(byte)79,
		(byte)81,
		(byte)83,
		(byte)85,
		(byte)87,
		(byte)89,
		(byte)91,
		(byte)93,
		(byte)95,
		(byte)97,
		(byte)99,
		(byte)101,
		(byte)103,
		(byte)105,
		(byte)107,
		(byte)109,
		(byte)111,
		(byte)113,
		(byte)115,
		(byte)117,
		(byte)119,
		(byte)121,
		(byte)123,
		(byte)125,
		(byte)128,
		(byte)126,
		(byte)124,
		(byte)122,
		(byte)120,
		(byte)118,
		(byte)116,
		(byte)114,
		(byte)112,
		(byte)110,
		(byte)108,
		(byte)106,
		(byte)104,
		(byte)102,
		(byte)100,
		(byte)98,
		(byte)96,
		(byte)94,
		(byte)92,
		(byte)90,
		(byte)88,
		(byte)86,
		(byte)84,
		(byte)82,
		(byte)80,
		(byte)78,
		(byte)76,
		(byte)74,
		(byte)72,
		(byte)70,
		(byte)68,
		(byte)66,
		(byte)64,
		(byte)63,
		(byte)61,
		(byte)59,
		(byte)57,
		(byte)55,
		(byte)53,
		(byte)51,
		(byte)49,
		(byte)47,
		(byte)45,
		(byte)43,
		(byte)41,
		(byte)39,
		(byte)37,
		(byte)35,
		(byte)33,
		(byte)31,
		(byte)29,
		(byte)27,
		(byte)25,
		(byte)23,
		(byte)21,
		(byte)19,
		(byte)17,
		(byte)15,
		(byte)13,
		(byte)11,
		(byte)9,
		(byte)7,
		(byte)5,
		(byte)3,
		(byte)1,
		(byte)0,
		(byte)2,
		(byte)4,
		(byte)6,
		(byte)8,
		(byte)10,
		(byte)12,
		(byte)14,
		(byte)16,
		(byte)18,
		(byte)20,
		(byte)22,
		(byte)24,
		(byte)26,
		(byte)28,
		(byte)30,
		(byte)32,
		(byte)34,
		(byte)36,
		(byte)38,
		(byte)40,
		(byte)42,
		(byte)44,
		(byte)46,
		(byte)48,
		(byte)50,
		(byte)52,
		(byte)54,
		(byte)56,
		(byte)58,
		(byte)60,
		(byte)62,
		(byte)64,
		(byte)66,
		(byte)68,
		(byte)70,
		(byte)72,
		(byte)74,
		(byte)76,
		(byte)78,
		(byte)80,
		(byte)82,
		(byte)84,
		(byte)86,
		(byte)88,
		(byte)90,
		(byte)92,
		(byte)94,
		(byte)96,
		(byte)98,
		(byte)100,
		(byte)102,
		(byte)104,
		(byte)106,
		(byte)108,
		(byte)110,
		(byte)112,
		(byte)114,
		(byte)116,
		(byte)118,
		(byte)120,
		(byte)122,
		(byte)124,
		(byte)126,
		(byte)128,
		(byte)130,
		(byte)132,
		(byte)134,
		(byte)136,
		(byte)138,
		(byte)140,
		(byte)142,
		(byte)144,
		(byte)146,
		(byte)148,
		(byte)150,
		(byte)152,
		(byte)154,
		(byte)156,
		(byte)158,
		(byte)160,
		(byte)162,
		(byte)164,
		(byte)166,
		(byte)168,
		(byte)170,
		(byte)172,
		(byte)174,
		(byte)176,
		(byte)178,
		(byte)180,
		(byte)182,
		(byte)184,
		(byte)186,
		(byte)188,
		(byte)190,
		(byte)192,
		(byte)194,
		(byte)196,
		(byte)198,
		(byte)200,
		(byte)202,
		(byte)204,
		(byte)206,
		(byte)208,
		(byte)210,
		(byte)212,
		(byte)214,
		(byte)216,
		(byte)218,
		(byte)220,
		(byte)222,
		(byte)224,
		(byte)226,
		(byte)228,
		(byte)230,
		(byte)232,
		(byte)234,
		(byte)236,
		(byte)238,
		(byte)240,
		(byte)242,
		(byte)244,
		(byte)246,
		(byte)248,
		(byte)250,
		(byte)252,
		(byte)255
	};

	protected static byte[] blueStaticArray = {
		(byte)0,
		(byte)1,
		(byte)3,
		(byte)5,
		(byte)7,
		(byte)9,
		(byte)11,
		(byte)13,
		(byte)15,
		(byte)17,
		(byte)19,
		(byte)21,
		(byte)23,
		(byte)25,
		(byte)27,
		(byte)29,
		(byte)31,
		(byte)33,
		(byte)35,
		(byte)37,
		(byte)39,
		(byte)41,
		(byte)43,
		(byte)45,
		(byte)47,
		(byte)49,
		(byte)51,
		(byte)53,
		(byte)55,
		(byte)57,
		(byte)59,
		(byte)61,
		(byte)63,
		(byte)65,
		(byte)67,
		(byte)69,
		(byte)71,
		(byte)73,
		(byte)75,
		(byte)77,
		(byte)79,
		(byte)81,
		(byte)83,
		(byte)85,
		(byte)87,
		(byte)89,
		(byte)91,
		(byte)93,
		(byte)95,
		(byte)97,
		(byte)99,
		(byte)101,
		(byte)103,
		(byte)105,
		(byte)107,
		(byte)109,
		(byte)111,
		(byte)113,
		(byte)115,
		(byte)117,
		(byte)119,
		(byte)121,
		(byte)123,
		(byte)125,
		(byte)127,
		(byte)129,
		(byte)131,
		(byte)133,
		(byte)135,
		(byte)137,
		(byte)139,
		(byte)141,
		(byte)143,
		(byte)145,
		(byte)147,
		(byte)149,
		(byte)151,
		(byte)153,
		(byte)155,
		(byte)157,
		(byte)159,
		(byte)161,
		(byte)163,
		(byte)165,
		(byte)167,
		(byte)169,
		(byte)171,
		(byte)173,
		(byte)175,
		(byte)177,
		(byte)179,
		(byte)181,
		(byte)183,
		(byte)185,
		(byte)187,
		(byte)189,
		(byte)191,
		(byte)193,
		(byte)195,
		(byte)197,
		(byte)199,
		(byte)201,
		(byte)203,
		(byte)205,
		(byte)207,
		(byte)209,
		(byte)211,
		(byte)213,
		(byte)215,
		(byte)217,
		(byte)219,
		(byte)221,
		(byte)223,
		(byte)225,
		(byte)227,
		(byte)229,
		(byte)231,
		(byte)233,
		(byte)235,
		(byte)237,
		(byte)239,
		(byte)241,
		(byte)243,
		(byte)245,
		(byte)247,
		(byte)249,
		(byte)251,
		(byte)253,
		(byte)255,
		(byte)252,
		(byte)248,
		(byte)244,
		(byte)240,
		(byte)236,
		(byte)232,
		(byte)228,
		(byte)224,
		(byte)220,
		(byte)216,
		(byte)212,
		(byte)208,
		(byte)204,
		(byte)200,
		(byte)196,
		(byte)192,
		(byte)188,
		(byte)184,
		(byte)180,
		(byte)176,
		(byte)172,
		(byte)168,
		(byte)164,
		(byte)160,
		(byte)156,
		(byte)152,
		(byte)148,
		(byte)144,
		(byte)140,
		(byte)136,
		(byte)132,
		(byte)128,
		(byte)124,
		(byte)120,
		(byte)116,
		(byte)112,
		(byte)108,
		(byte)104,
		(byte)100,
		(byte)96,
		(byte)92,
		(byte)88,
		(byte)84,
		(byte)80,
		(byte)76,
		(byte)72,
		(byte)68,
		(byte)64,
		(byte)60,
		(byte)56,
		(byte)52,
		(byte)48,
		(byte)44,
		(byte)40,
		(byte)36,
		(byte)32,
		(byte)28,
		(byte)24,
		(byte)20,
		(byte)16,
		(byte)12,
		(byte)8,
		(byte)4,
		(byte)0,
		(byte)4,
		(byte)8,
		(byte)12,
		(byte)16,
		(byte)20,
		(byte)24,
		(byte)28,
		(byte)32,
		(byte)36,
		(byte)40,
		(byte)44,
		(byte)48,
		(byte)52,
		(byte)56,
		(byte)60,
		(byte)64,
		(byte)68,
		(byte)72,
		(byte)76,
		(byte)80,
		(byte)85,
		(byte)89,
		(byte)93,
		(byte)97,
		(byte)101,
		(byte)105,
		(byte)109,
		(byte)113,
		(byte)117,
		(byte)121,
		(byte)125,
		(byte)129,
		(byte)133,
		(byte)137,
		(byte)141,
		(byte)145,
		(byte)149,
		(byte)153,
		(byte)157,
		(byte)161,
		(byte)165,
		(byte)170,
		(byte)174,
		(byte)178,
		(byte)182,
		(byte)186,
		(byte)190,
		(byte)194,
		(byte)198,
		(byte)202,
		(byte)206,
		(byte)210,
		(byte)214,
		(byte)218,
		(byte)222,
		(byte)226,
		(byte)230,
		(byte)234,
		(byte)238,
		(byte)242,
		(byte)246,
		(byte)250,
		(byte)255
	};

	public ColorPalettePET() {
		red = redStaticArray;
		green = greenStaticArray;
		blue = blueStaticArray;
		sopInstanceUID= sopInstanceUIDStatic;
		contentLabel = contentLabelStatic;
		contentDescription = contentDescriptionStatic;
		contentCreatorName = contentCreatorNameStatic;
		referenceEncodedInstanceURL = referenceEncodedInstanceURLStatic;
		alternateContentDescription = alternateContentDescriptionStatic;
		alternateContentLanguageCodeValue = alternateContentLanguageCodeValueStatic;
		alternateContentLanguageCodeMeaning = alternateContentLanguageCodeMeaningStatic;
	}

	/**
	 * <p>Create a DICOM color palette storage instance from the palette characteristics.</p>
	 *
	 * @param	arg one or two arguments, the name of the DICOM file to write, and optionally the name of a file containing the ICC profile to include
	 */
	public static void main(String arg[]) {

		try {
			new ColorPalettePET().createDICOMInstance(arg[0],(arg.length > 1 ? arg[1] : null),"OURAETITLE");
		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace(System.err);
			System.exit(0);
		}
	}
}
