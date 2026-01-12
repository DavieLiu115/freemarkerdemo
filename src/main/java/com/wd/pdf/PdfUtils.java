package com.wd.pdf;

import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

/**
 * @author lww
 * @date 2026-01-12 17:14
 */
public class PdfUtils {

	public static void main(String[] args) throws Exception {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage(PDRectangle.A4);
		doc.addPage(page);

		// 加载中文字体文件
		PDType0Font font = PDType0Font.load(doc, new File("./src/main/resources/msyhl.ttf"));
		try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
			cs.beginText();
			cs.setFont(font, 18);
			cs.newLineAtOffset(50, 750);
			cs.showText("Hello, PDFBox! 你好，PDFBox！");
			cs.endText();
		}

		doc.save("hello.pdf");
		doc.close();
	}

}
