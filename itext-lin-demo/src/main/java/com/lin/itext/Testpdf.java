package com.lin.itext;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;

import java.io.IOException;

public class Testpdf {
    private static final String INPUT_PDF = "C:\\Users\\zeyu.lin\\Desktop\\output.pdf";
    private static final String OUTPUT_FOLDER1 = "C:\\Users\\zeyu.lin\\Desktop\\output1.pdf";

    public static void main(String[] args) throws IOException {
        PdfDocument pdfDoc =
                new PdfDocument(new PdfReader(INPUT_PDF), new PdfWriter(OUTPUT_FOLDER1));
        Document document = new Document(pdfDoc);
        Rectangle pageSize;
        PdfCanvas canvas;
        int n = pdfDoc.getNumberOfPages();
        for (int i = 1; i < n; i++) {
            PdfPage page = pdfDoc.getPage(i);
            pageSize = page.getPageSize();
            canvas = new PdfCanvas(page);
            // add new content
            canvas.beginText().setFontAndSize(
                            PdfFontFactory.createFont(FontConstants.HELVETICA), 7)
                    .moveText(pageSize.getWidth() / 2 - 7, 10)
                    .showText(String.valueOf(i))
                    .showText(" of ")
                    .showText(String.valueOf(n - 1))
                    .endText();
        }
        pdfDoc.close();
    }
}
