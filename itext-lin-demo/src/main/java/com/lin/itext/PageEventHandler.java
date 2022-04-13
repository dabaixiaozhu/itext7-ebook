package com.lin.itext;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

public class PageEventHandler implements IEventHandler {
    private PdfFont pdfFont;

    public PageEventHandler(PdfFont sysFont) {
        this.pdfFont = sysFont;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
        PdfDocument document = documentEvent.getDocument();
        PdfPage page = documentEvent.getPage();
        Rectangle pageSize = page.getPageSize();

        PdfCanvas pdfCanvas = new PdfCanvas(page.getLastContentStream(), page.getResources(), document);
        Canvas canvas = new Canvas(pdfCanvas, pageSize);
        float x = (pageSize.getLeft() + pageSize.getRight()) / 2;
        float y = pageSize.getBottom() + 15;

        if (document.getPageNumber(page) != document.getNumberOfPages()) {
            Paragraph paragraph = new Paragraph("第" + document.getPageNumber(page) + "页/共" + document.getNumberOfPages() + "页")
                    .setFontSize(10)
                    .setFont(pdfFont);

            canvas.showTextAligned(paragraph, x, y, TextAlignment.CENTER);
        }

        canvas.close();
    }
}
