package com.lin.itext;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.property.TextAlignment;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HtmlToPdfUtils {

    private static final String OUTPUT_FOLDER = "C:\\Users\\zeyu.lin\\Desktop\\output.pdf";

    /**
     * html转pdf
     *
     * @param inputStream  输入流
     * @param waterMark    水印
     * @param fontPath     字体路径，ttc后缀的字体需要添加<b>,0<b/>
     * @param outputStream 输出流
     * @date : 2021/1/15 14:07
     */
    public static void convertToPdf(InputStream inputStream, String waterMark, String fontPath, OutputStream outputStream) throws IOException {
        PdfWriter pdfWriter = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        //设置为A4大小
        pdfDocument.setDefaultPageSize(PageSize.A4);
        // 设置中文
        PdfFont sysFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        // 添加水印
//        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new WaterMarkEventHandler(waterMark));
        // 添加页脚
//        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new PageEventHandler(sysFont));

        //添加中文字体支持
        ConverterProperties properties = new ConverterProperties();
        FontProvider fontProvider = new FontProvider();
        fontProvider.addFont(sysFont.getFontProgram(), "UniGB-UCS2-H");

        //添加自定义字体，例如微软雅黑
        if (StringUtils.isNotBlank(fontPath)) {
            PdfFont microsoft = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H, false);
            fontProvider.addFont(microsoft.getFontProgram(), PdfEncodings.IDENTITY_H);
        }

        properties.setFontProvider(fontProvider);

        HtmlConverter.convertToPdf(inputStream, pdfDocument, properties);

        pdfWriter.close();
        pdfDocument.close();
    }

    public static void setPdf(String inputpath, String outputpath) throws IOException {
        PdfDocument pdfDoc =
                new PdfDocument(new PdfReader(inputpath), new PdfWriter(outputpath));
//        Document document = new Document(pdfDoc);
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