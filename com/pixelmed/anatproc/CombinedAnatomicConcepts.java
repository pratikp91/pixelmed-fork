/* Copyright (c) 2001-2013, David A. Clunie DBA Pixelmed Publishing. All rights reserved. */

package com.pixelmed.anatproc;

import com.pixelmed.dicom.AttributeList;

/**
 * <p>This class supports anatomic concepts that may be combinations of one another.</p>
 * 
 * @author	dclunie
 */
public class CombinedAnatomicConcepts extends DisplayableConcept {

	private static final String identString = "@(#) $Header: /userland/cvs/pixelmed/imgbook/com/pixelmed/anatproc/CombinedAnatomicConcepts.java,v 1.2 2013/01/02 00:35:14 dclunie Exp $";
		
	// new Concept("C1508499"/*"Abdomen and Pelvis"*/)
	// new Concept("C1442171"/*"Chest and Abdomen"*/)
	// new Concept("C1562547"/*"Chest, Abdomen and Pelvis"*/)
	// new Concept("C0460004"/*"Head and Neck"*/)
	// new Concept("C1562459"/*"Neck and Chest"*/)
	// new Concept("C1562378"/*"Neck, Chest and Abdomen"*/)
	// new Concept("C1562776"/*"Neck, Chest, Abdomen and Pelvis"*/)
	
	// new Concept("C0006104"/*"Brain"*/)
	// new Concept("C0018670"/*"Head"*/)
	// new Concept("C0027530"/*"Neck"*/)
	// new Concept("C0817096"/*"Chest"*/)
	// new Concept("C0024109"/*"Lung"*/)
	// new Concept("C0000726"/*"Abdomen"*/)
	// new Concept("C0030797"/*"Pelvis"*/)
	
	protected static Concept[] newConceptArray(Concept... values) { return values; }

	// combinations array is searched in successive order, so more stringent matches should come first ...
	protected static Combination[] combinations = {
		// deal with what are essentially synonyms first (to prevent expanding body part due to synonyms in broader combinations) ...
		
		new Combination(new Concept("C0018670"/*"Head"*/),	newConceptArray(new Concept("C0006104"/*"Brain"*/))),
		new Combination(new Concept("C0817096"/*"Chest"*/),	newConceptArray(new Concept("C0024109"/*"Lung"*/))),
		
		// deal with two pair combinations ...
		new Combination(new Concept("C1508499"/*"Abdomen and Pelvis"*/), newConceptArray(
				new Concept("C0000726"/*"Abdomen"*/),
				new Concept("C0030797"/*"Pelvis"*/)
			)
		),
		new Combination(new Concept("C0460004"   /*"Head and Neck"*/),      newConceptArray(
				new Concept("C0006104"/*"Brain"*/),
				new Concept("C0018670"/*"Head"*/),
				new Concept("C0027530"/*"Neck"*/)
			)
		),
		new Combination(new Concept("C1562459"/*"Neck and Chest"*/),     newConceptArray(
				new Concept("C0027530"/*"Neck"*/),
				new Concept("C0817096"/*"Chest"*/),
				new Concept("C0024109"/*"Lung"*/)
			)
		),
		new Combination(new Concept("C1442171"/*"Chest and Abdomen"*/),  newConceptArray(
				new Concept("C0817096"/*"Chest"*/),
				new Concept("C0024109"/*"Lung"*/),
				new Concept("C0000726"/*"Abdomen"*/)
			)
		),
		
		// deal with three pair combinations ...
		new Combination(new Concept("C1562547"/*"Chest, Abdomen and Pelvis"*/), newConceptArray(
				new Concept("C0817096"/*"Chest"*/),
				new Concept("C0024109"/*"Lung"*/),
				new Concept("C0000726"/*"Abdomen"*/),
				new Concept("C0030797"/*"Pelvis"*/),
				new Concept("C1442171"/*"Chest and Abdomen"*/),
				new Concept("C1508499"/*"Abdomen and Pelvis"*/)
			)
		),
		new Combination(new Concept("C1562378"/*"Neck, Chest and Abdomen"*/), newConceptArray(
				new Concept("C0027530"/*"Neck"*/),
				new Concept("C0817096"/*"Chest"*/),
				new Concept("C0024109"/*"Lung"*/),
				new Concept("C0000726"/*"Abdomen"*/),
				new Concept("C1562459"/*"Neck and Chest"*/), 
				new Concept("C1442171"/*"Chest and Abdomen"*/)
			)
		),
		
		// deal with four pair combinations ...
		new Combination(new Concept("C1562776"/*"Neck, Chest, Abdomen and Pelvis"*/), newConceptArray(
				new Concept("C0027530"/*"Neck"*/),
				new Concept("C0817096"/*"Chest"*/),
				new Concept("C0024109"/*"Lung"*/),
				new Concept("C0000726"/*"Abdomen"*/),
				new Concept("C0030797"/*"Pelvis"*/),
				new Concept("C1562459"/*"Neck and Chest"*/), 
				new Concept("C1442171"/*"Chest and Abdomen"*/),
				new Concept("C1508499"/*"Abdomen and Pelvis"*/),
				new Concept("C1562547"/*"Chest, Abdomen and Pelvis"*/),
				new Concept("C1562378"/*"Neck, Chest and Abdomen"*/)
			)
		)
	};

	/**
	 * <p>Combine two concepts into a single concept containing both if possible.</p>
	 *
	 * @param	a	one concept
	 * @param	b	another concept
	 * @return		a combined concept if it exists, else null
	 */
	public static Concept getCombinedConcept(Concept a,Concept b) {
//System.err.println("CombinedAnatomicConcepts.getCombinedConcept(): comparing "+a+" with "+b);
		Concept combined = null;
		if (a.equals(b)) {
//System.err.println("CombinedAnatomicConcepts.getCombinedConcept(): both concepts are equal");
			return a;
		}
		else {
			for (Combination combination : combinations) {
				if (combination.containsOrIsSelf(a) && combination.containsOrIsSelf(b)) {
					combined = combination.parent;
//System.err.println("CombinedAnatomicConcepts.getCombinedConcept(): found combination "+combined);
					break;
				}
			}
		}
		
		return combined;
	}

	/**
	 * <p>Combine two concepts into a single concept containing both if possible.</p>
	 *
	 * @param	a		one concept
	 * @param	b		another concept
	 * @param	dict	dictionary of concepts to lookup
	 * @return			a combined concept if it exists and is present in the dictionary, else null
	 */
	public static DisplayableConcept getCombinedConcept(Concept a,Concept b,DictionaryOfConcepts dict) {
//System.err.println("CombinedAnatomicConcepts.getCombinedConcept(): using dictionary of "+dict.getDescriptionOfConcept()+" comparing "+a+" with "+b);
		DisplayableConcept displayableCombined = null;
		if (dict != null) {
			Concept combined = getCombinedConcept(a,b);
			if (combined != null) {
//System.err.println("CombinedAnatomicConcepts.getCombinedConcept(): found combined concept "+combined+" now looking up in dictionary");
				displayableCombined = dict.find(combined);
			}
		}
		return displayableCombined;
	}
}

