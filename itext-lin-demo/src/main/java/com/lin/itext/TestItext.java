package com.lin.itext;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestItext {
    private static final String ORIG = "C:\\Users\\zeyu.lin\\Desktop\\index\\new.html";
    private static final String OUTPUT_FOLDER = "C:\\Users\\zeyu.lin\\Desktop\\";
    private static final String WATER_MARK_TEXT = "linlinlin";
    private static final String WORD_PATH = "C:\\Windows\\Fonts\\msyh.ttc,0";
    private static final String INPUT_PDF = "C:\\Users\\zeyu.lin\\Desktop\\output.pdf";
    private static final String Output_PDF = "C:\\Users\\zeyu.lin\\Desktop\\output1.pdf";

    public static void main(String[] args) throws IOException {
        FileInputStream htmlSource = new FileInputStream(ORIG);
        FileOutputStream pdfDest = new FileOutputStream(OUTPUT_FOLDER + "output.pdf");
        HtmlToPdfUtils.convertToPdf(htmlSource, WATER_MARK_TEXT, WORD_PATH, pdfDest);
//        HtmlToPdfUtils.setPdf(INPUT_PDF, Output_PDF);
    }
}
