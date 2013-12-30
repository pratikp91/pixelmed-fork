OBJS = \
	ConvertAmicasJPEG2000FilesetToDicom.class \
	NIfTI1ToDicom.class \
	NIfTI1Header.class \
	NIfTI1Exception.class \
	NRRDToDicom.class \
	NRRDHeader.class \
	NRRDException.class \
	RawRGBInformation.class \
	RawRGBToDicomMultiFrame.class \
	UnencapsulateCompressedPixelData.class \
	EncapsulateCompressedPixelData.class \
	IndexedLabelMapToSegmentation.class

all:	${OBJS}

PATHTOROOT = ../../..

include ${PATHTOROOT}/Makefile.common.mk

testamicas:	ConvertAmicasJPEG2000FilesetToDicom.class
	mkdir -p /tmp/todicomMR1
	java -cp ${PATHTOROOT}:${PATHTOADDITIONAL}/jai_imageio.jar com.pixelmed.convert.ConvertAmicasJPEG2000FilesetToDicom "${PATHTOHOME}/Pictures/Medical/AmicasJ2K/ELMR20030206" /tmp/todicomMR1
	mkdir -p /tmp/todicomCT1
	java -cp ${PATHTOROOT}:${PATHTOADDITIONAL}/jai_imageio.jar com.pixelmed.convert.ConvertAmicasJPEG2000FilesetToDicom "${PATHTOHOME}/Pictures/Medical/AmicasJ2K/CD0105153529" /tmp/todicomCT1
	mkdir -p /tmp/todicomCT2
	java -cp ${PATHTOROOT}:${PATHTOADDITIONAL}/jai_imageio.jar com.pixelmed.convert.ConvertAmicasJPEG2000FilesetToDicom "${PATHTOHOME}/Pictures/Medical/AmicasJ2K/CD0114154520" /tmp/todicomCT2

testamicasdialog:	ConvertAmicasJPEG2000FilesetToDicom.class
	java -cp ${PATHTOROOT}:${PATHTOADDITIONAL}/jai_imageio.jar com.pixelmed.convert.ConvertAmicasJPEG2000FilesetToDicom


testnifti1header:	NIfTI1Header.class NIfTI1Exception.class
	java -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.NIfTI1Header \
		"$${HOME}/Pictures/Medical/ParametricMaps/DICOM_FP/GE_Normalized_Boxerman_rBFMap_LeakageCorrected.nii"
		#"$${HOME}/Pictures/Medical/ParametricMaps/DICOM_FP/dti_adc.nii"

testnifti1convert:	NIfTI1ToDicom.class NIfTI1Header.class NIfTI1Exception.class
	rm -rf /tmp/testnifti1fp.dcm
	rm -rf /tmp/testnifti1fp_scaledtoint.dcm
	java -Xmx512m -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.NIfTI1ToDicom \
		"$${HOME}/Pictures/Medical/ParametricMaps/DICOM_FP/DICOM_FP/GE_Normalized_Boxerman_rBFMap_LeakageCorrected.nii" \
		/tmp/testnifti1fp.dcm \
		"Doe^Jane" 1234 5678 100 1
	java -Xmx512m -cp .:${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.apps.IntegerScalingOfFloatingPointPixelData \
		/tmp/testnifti1fp.dcm /tmp/testnifti1fp_scaledtoint.dcm
	andump /tmp/testnifti1fp.dcm
	#dciodvfy /tmp/testnifti1fp.dcm
	andump /tmp/testnifti1fp_scaledtoint.dcm
	dciodvfy /tmp/testnifti1fp_scaledtoint.dcm
	#dchist -h /tmp/testnifti1fp_scaledtoint.dcm
	#andiff /tmp/testnifti1fp.dcm /tmp/testnifti1fp_scaledtoint.dcm

testnifti1talairach:	NIfTI1ToDicom.class NIfTI1Header.class NIfTI1Exception.class
	java -Xmx512m -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.NIfTI1ToDicom \
		"$${HOME}/Documents/Medical/stuff/Atlases/Talairach/talairach.nii" \
		"$${HOME}/Documents/Medical/stuff/Atlases/Talairach/talairach.dcmm" \
		"Talairach" 1234 5678 100 1

testnrrdheaderseparate:	NRRDHeader.class NRRDException.class
	java -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.NRRDHeader \
		"$${HOME}/Pictures/Medical/ParametricMaps/PkModeling_verification/PCAMPMRI-0241/SlicerScene/MRMLMultiVolume.nhdr"

testnrrdheadercombined:	NRRDHeader.class NRRDException.class
	java -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.NRRDHeader \
		"$${HOME}/Pictures/Medical/ParametricMaps/PkModeling_verification/PCAMPMRI-0241/SlicerScene/MRMLMultiVolume frame 21.nrrd"

testnrrdconvertseparate:	NRRDToDicom.class NRRDHeader.class NRRDException.class
	rm -f /tmp/testnrrdfp.dcm
	rm -f /tmp/testnrrdfp_scaledtoint.dcm
	java -Xmx2g -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.NRRDToDicom \
		"$${HOME}/Pictures/Medical/ParametricMaps/PkModeling_verification/PCAMPMRI-0241/SlicerScene/MRMLMultiVolume.nhdr" \
		/tmp/testnrrdfp.dcm \
		"Doe^Jane" 1234 5678 100 1
	java -Xmx1g -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.apps.IntegerScalingOfFloatingPointPixelData \
		/tmp/testnrrdfp.dcm /tmp/testnrrdfp_scaledtoint.dcm
	#andump /tmp/testnrrdfp.dcm
	#dciodvfy /tmp/testnrrdfp.dcm
	andump /tmp/testnrrdfp_scaledtoint.dcm
	dciodvfy /tmp/testnrrdfp_scaledtoint.dcm
	#dchist -h /tmp/testnrrdfp_scaledtoint.dcm
	#andiff /tmp/testnrrdfp.dcm /tmp/testnrrdfp_scaledtoint.dcm

testnrrdconvertcombined:	NRRDToDicom.class NRRDHeader.class NRRDException.class
	rm -f /tmp/testnrrdfp.dcm
	rm -f /tmp/testnrrdfp_scaledtoint.dcm
	java -Xmx1g -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.NRRDToDicom \
		"$${HOME}/Pictures/Medical/ParametricMaps/PkModeling_verification/PCAMPMRI-0241/SlicerScene/OncoQuant-AutoAIF-Results/PCAMPMRI-0241_GE-Ktrans.nrrd" \
		/tmp/testnrrdfp.dcm \
		"Doe^Jane" 1234 5678 100 1
	#"$${HOME}/Pictures/Medical/ParametricMaps/PkModeling_verification/PCAMPMRI-0241/SlicerScene/MRMLMultiVolume frame 21.nrrd"
	java -Xmx1g -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.apps.IntegerScalingOfFloatingPointPixelData \
		/tmp/testnrrdfp.dcm /tmp/testnrrdfp_scaledtoint.dcm
	#andump /tmp/testnrrdfp.dcm
	#dciodvfy /tmp/testnrrdfp.dcm
	andump /tmp/testnrrdfp_scaledtoint.dcm
	dciodvfy /tmp/testnrrdfp_scaledtoint.dcm
	#dchist -h /tmp/testnrrdfp_scaledtoint.dcm
	#andiff /tmp/testnrrdfp.dcm /tmp/testnrrdfp_scaledtoint.dcm

testnrrdheaderseparate:	NRRDHeader.class NRRDException.class
	java -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.NRRDHeader \
		"$${HOME}/Pictures/Medical/ParametricMaps/PkModeling_verification/PCAMPMRI-0241/SlicerScene/MRMLMultiVolume.nhdr"

testrawrgbheader:	RawRGBInformation.class
	if [ ! -f "$${HOME}/work/vhm/testfiles/README" ]; \
	then \
		mkdir -p "$${HOME}/work/vhm/testfiles"; \
		cp "$${HOME}/work/vhm/srcimages/vhnet.nlm.nih.gov/Male/70mm/README" "$${HOME}/work/vhm/testfiles/README"; \
	fi
	java -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.RawRGBInformation \
		"$${HOME}/work/vhm/testfiles/README"

testrawrgbvhmuncompressed:	RawRGBToDicomMultiFrame.class RawRGBInformation.class
	mkdir -p "$${HOME}/work/vhm/testfiles"
	mkdir -p "$${HOME}/work/vhm/testfiles/srcimages/uncompressed"
	mkdir -p "$${HOME}/work/vhm/testfiles/dstimages/testrawrgbvhm"
	cp "$${HOME}/work/vhm/srcimages/vhnet.nlm.nih.gov/Male/70mm/README" "$${HOME}/work/vhm/testfiles/"
	gzcat <"$${HOME}/work/vhm/srcimages/vhnet.nlm.nih.gov/Male/70mm/fullbody/2000.rgb.gz" >"$${HOME}/work/vhm/testfiles/srcimages/uncompressed/2000.rgb"
	gzcat <"$${HOME}/work/vhm/srcimages/vhnet.nlm.nih.gov/Male/70mm/fullbody/2001.rgb.gz" >"$${HOME}/work/vhm/testfiles/srcimages/uncompressed/2001.rgb"
	gzcat <"$${HOME}/work/vhm/srcimages/vhnet.nlm.nih.gov/Male/70mm/fullbody/2002.rgb.gz" >"$${HOME}/work/vhm/testfiles/srcimages/uncompressed/2002.rgb"
	java -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.RawRGBToDicomMultiFrame \
		"$${HOME}/work/vhm/testfiles/README" \
		"$${HOME}/work/vhm/testfiles/srcimages/uncompressed" \
		".rgb" \
		"$${HOME}/work/vhm/testfiles/dstimages/testrawrgbvhm" \
		"Doe^Jane" 1234 5678 100

testrawrgbvhmcompressed:	RawRGBToDicomMultiFrame.class RawRGBInformation.class
	mkdir -p "$${HOME}/work/vhm/testfiles"
	mkdir -p "$${HOME}/work/vhm/testfiles/srcimages/compressed"
	mkdir -p "$${HOME}/work/vhm/testfiles/dstimages/testrawrgbvhm"
	cp "$${HOME}/work/vhm/srcimages/vhnet.nlm.nih.gov/Male/70mm/README" "$${HOME}/work/vhm/testfiles/"
	cp "$${HOME}/work/vhm/srcimages/vhnet.nlm.nih.gov/Male/70mm/fullbody/2000.rgb.gz" "$${HOME}/work/vhm/testfiles/srcimages/compressed/"
	cp "$${HOME}/work/vhm/srcimages/vhnet.nlm.nih.gov/Male/70mm/fullbody/2001.rgb.gz" "$${HOME}/work/vhm/testfiles/srcimages/compressed/"
	cp "$${HOME}/work/vhm/srcimages/vhnet.nlm.nih.gov/Male/70mm/fullbody/2002.rgb.gz" "$${HOME}/work/vhm/testfiles/srcimages/compressed/"
	java -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.RawRGBToDicomMultiFrame \
		"$${HOME}/work/vhm/testfiles/README" \
		"$${HOME}/work/vhm/testfiles/srcimages/compressed" \
		".rgb.gz" \
		"$${HOME}/work/vhm/testfiles/dstimages/testrawrgbvhm" \
		"Doe^Jane" 1234 5678 100

testunencap:	UnencapsulateCompressedPixelData.class
	mkdir -p /tmp/crapunencap
	rm -f /tmp/crapunencap/*
	java -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.UnencapsulateCompressedPixelData \
			"$${HOME}/Pictures/Medical/MultiframeEncapsulated/JPEGBaseline/Acuson/ACUSON-24-YBR_FULL_422-Jpeg_Baseline_1.dcm" \
			/tmp/crapunencap/frame_

testreencap:	UnencapsulateCompressedPixelData.class EncapsulateCompressedPixelData.class
	cp "$${HOME}/Pictures/Medical/MultiframeEncapsulated/JPEGBaseline/Acuson/ACUSON-24-YBR_FULL_422-Jpeg_Baseline_1.dcm" /tmp/srcfile.dcm
	mkdir -p /tmp/crapunencap
	rm -f /tmp/crapunencap/*
	java -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.UnencapsulateCompressedPixelData \
			/tmp/srcfile.dcm \
			/tmp/crapunencap/frame_
	rm -f /tmp/crapreencap.dcm
	java -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.EncapsulateCompressedPixelData \
			/tmp/crapreencap.dcm \
			/tmp/srcfile.dcm \
			/tmp/crapunencap/frame_*
	#rm -f /tmp/srcfile.dcm

/tmp/grayscale2008.dcm:
	java -Xmx1g -cp ${PATHTOROOT} -Djava.awt.headless=true com.pixelmed.convert.NRRDToDicom \
		"${HOME}/Documents/Work/NCI/Slicer/BrainAtlas/2008/SPL_PNL_Brain_Atlas2008/01-MRI/grayscale.nrrd" \
		/tmp/grayscale2008.dcm \
		"Brain^Atlas SPL-PNL 2008" 2008 5678 100 1 \
		MR

/tmp/labelmap2008.dcm:
	java -Xmx1g -cp ${PATHTOROOT} -Djava.awt.headless=true com.pixelmed.convert.NRRDToDicom \
		"${HOME}/Documents/Work/NCI/Slicer/BrainAtlas/2008/SPL_PNL_Brain_Atlas2008/02-labels/seg.nrrd" \
		/tmp/labelmap2008.dcm \
		"Brain^Atlas SPL-PNL 2008" 2008 5678 101 1

# use 2007 label map since none for 2008 ... some values are missing (and some in map are unused, mostly duplicates, but also pituitary)
testlabelmapbrainatlas2008:	IndexedLabelMapToSegmentation.class /tmp/labelmap2008.dcm /tmp/grayscale2008.dcm
	java -Xms6g -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.IndexedLabelMapToSegmentation \
		/tmp/labelmap2008.dcm \
		"${HOME}/Documents/Work/NCI/Slicer/BrainAtlas/2007/SPL-BrainAtlas-label_list_fields_RGB.csv" \
		/tmp/seg2008.dcm \
		/tmp/grayscale2008.dcm \
		102 "Segmented Brain" BRAIN_ATLAS_2008 "SPL-PNL Brain Atlas 2008" "SPL-PNL^Talos et al"
	dcentvfy /tmp/seg2008.dcm /tmp/grayscale2008.dcm



/tmp/grayscale2013_10.dcm:
	java -Xmx1g -cp ${PATHTOROOT} -Djava.awt.headless=true com.pixelmed.convert.NRRDToDicom \
		"${HOME}/Documents/Work/NCI/Slicer/BrainAtlas/2013/nac-brain-atlas-1.0/volumes/A1_grayT1.nrrd" \
		/tmp/grayscale2013_10.dcm \
		"Brain^Atlas NAC 2013" 2013 5678 100 1 \
		MR

/tmp/labelmap2013_10.dcm:
	java -Xmx1g -cp ${PATHTOROOT} -Djava.awt.headless=true com.pixelmed.convert.NRRDToDicom \
		"${HOME}/Documents/Work/NCI/Slicer/BrainAtlas/2013/nac-brain-atlas-1.0/volumes/A1_labels.nrrd" \
		/tmp/labelmap2013_10.dcm \
		"Brain^Atlas NAC 2013" 2013 5678 101 1

testlabelmapbrainatlas2013_10:	IndexedLabelMapToSegmentation.class /tmp/labelmap2013_10.dcm /tmp/grayscale2013_10.dcm
	java -Xms6g -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.IndexedLabelMapToSegmentation \
		/tmp/labelmap2013_10.dcm \
		"${HOME}/Documents/Work/NCI/Slicer/BrainAtlas/2013/BrainAtlas-label_list_fields_RGB.csv" \
		/tmp/seg2013_10.dcm \
		/tmp/grayscale2013_10.dcm \
		102 "Segmented Brain" BRAIN_ATLAS_2013 "NAC Brain Atlas 2013" "NAC^Halle et al"
	dcentvfy /tmp/seg2013_10.dcm /tmp/grayscale2013_10.dcm



/tmp/grayscale2013_nac_hncma.dcm:
	java -Xmx1g -cp ${PATHTOROOT} -Djava.awt.headless=true com.pixelmed.convert.NRRDToDicom \
		"${HOME}/Documents/Work/NCI/Slicer/BrainAtlas/2013/nac-hncma-atlas2013-Slicer4Version/Data/A1_grayT1.nrrd" \
		/tmp/grayscale2013_nac_hncma_fp.dcm \
		"Brain^Atlas NAC HNCMA 2013" 2013 5678 100 1 \
		MR
	java -Xmx512m -cp .:${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.apps.IntegerScalingOfFloatingPointPixelData \
		/tmp/grayscale2013_nac_hncma_fp.dcm /tmp/grayscale2013_nac_hncma.dcm

/tmp/labelmap2013_nac_hncma.dcm:
	java -Xmx1g -cp ${PATHTOROOT} -Djava.awt.headless=true com.pixelmed.convert.NRRDToDicom \
		"${HOME}/Documents/Work/NCI/Slicer/BrainAtlas/2013/nac-hncma-atlas2013-Slicer4Version/Data/hncma-atlas.nrrd" \
		/tmp/labelmap2013_nac_hncma.dcm \
		"Brain^Atlas NAC HNCMA 2013" 2013 5678 101 1

testlabelmapbrainatlas2013_nac_hncma:	IndexedLabelMapToSegmentation.class /tmp/labelmap2013_nac_hncma.dcm /tmp/grayscale2013_nac_hncma.dcm
	java -Xms6g -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.IndexedLabelMapToSegmentation \
		/tmp/labelmap2013_nac_hncma.dcm \
		"${HOME}/Documents/Work/NCI/Slicer/BrainAtlas/2013/BrainAtlas-label_list_fields_RGB.csv" \
		/tmp/seg2013_nac_hncma.dcm \
		/tmp/grayscale2013_nac_hncma.dcm \
		102 "Segmented Brain Head Neck Muscles" BRAIN_ATLAS_2013 "NAC HNCMA Brain Atlas 2013" "NAC^Halle et al"
	#dcentvfy /tmp/seg2013_nac_hncma.dcm /tmp/grayscale2013_nac_hncma.dcm



/tmp/labelmap2013_nac_hncma_skin.dcm:
	java -Xmx1g -cp ${PATHTOROOT} -Djava.awt.headless=true com.pixelmed.convert.NRRDToDicom \
		"${HOME}/Documents/Work/NCI/Slicer/BrainAtlas/2013/nac-hncma-atlas2013-Slicer4Version/Data/skin.nrrd" \
		/tmp/labelmap2013_nac_hncma_skin.dcm \
		"Brain^Atlas NAC HNCMA 2013" 2013 5678 101 1

testlabelmapbrainatlas2013_nac_hncma_skin:	IndexedLabelMapToSegmentation.class /tmp/labelmap2013_nac_hncma_skin.dcm /tmp/grayscale2013_nac_hncma.dcm
	java -Xms6g -cp ${PATHTOROOT} -Djava.awt.headless=true \
		com.pixelmed.convert.IndexedLabelMapToSegmentation \
		/tmp/labelmap2013_nac_hncma_skin.dcm \
		"${HOME}/Documents/Work/NCI/Slicer/BrainAtlas/2013/BrainAtlas-label_list_fields_RGB.csv" \
		/tmp/seg2013_nac_hncma_skin.dcm \
		/tmp/grayscale2013_nac_hncma.dcm \
		102 "Segmented Brain Head Neck Muscles" BRAIN_ATLAS_2013 "NAC HNCMA Brain Atlas 2013" "NAC^Halle et al"
	#dcentvfy /tmp/seg2013_nac_hncma_skin.dcm /tmp/grayscale2013_nac_hncma_skin.dcm

