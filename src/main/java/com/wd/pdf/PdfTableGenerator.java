package com.wd.pdf;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class PdfTableGenerator {

	// 默认样式
	private static final float MARGIN_LEFT = 50;
	private static final float MARGIN_TOP = 50;
	private static final float LINE_HEIGHT = 20f;
	private static final float TABLE_WIDTH = 500f;
	// 字体
	private static PDType0Font HeadFont = null;
	private static PDType0Font ColumnFont = null;
	private static final Color HEADER_BG = new Color(200, 255, 200); // 浅绿
	private static final Color PK_BG = new Color(220, 255, 220);
	private static final Color ROW_BG = Color.WHITE;

	public static class FieldInfo {

		public String name;
		public String type;
		public String comment;
		public boolean isPrimaryKey;

		public FieldInfo(String name, String type, String comment, boolean isPrimaryKey) {
			this.name = name;
			this.type = type;
			this.comment = comment;
			this.isPrimaryKey = isPrimaryKey;
		}
	}

	public void generateTablePdf(String tableName, String tableComment, List<FieldInfo> fields, String outputPath) throws IOException {

		PDDocument doc = new PDDocument();
		PDPage currentPage = new PDPage(PDRectangle.A4);
		doc.addPage(currentPage);

		float y = PDRectangle.A4.getHeight() - MARGIN_TOP;
		float maxY = MARGIN_TOP; // 最低绘制位置（防止覆盖页脚）

		HeadFont = PDType0Font.load(doc, new File("./src/main/resources/msyh.ttf"));
		ColumnFont = PDType0Font.load(doc, new File("./src/main/resources/msyhl.ttf"));
		try (PDPageContentStream cs = new PDPageContentStream(doc, currentPage)) {
			// 绘制表头
			drawHeader(cs, tableName, tableComment, y);
			y -= LINE_HEIGHT + 5;

			// 绘制字段行
			for (FieldInfo field : fields) {
				// 检查是否需要新页
				if (y < maxY) {
					cs.close();
					currentPage = new PDPage(PDRectangle.A4);
					doc.addPage(currentPage);
					y = PDRectangle.A4.getHeight() - MARGIN_TOP;
					new PDPageContentStream(doc, currentPage).close(); // 占位
					// 重新打开流（实际应复用，此处简化）
					// 更佳做法：封装分页逻辑
				}

				drawFieldRow(cs, field, y);
				y -= LINE_HEIGHT;
			}
		}

		doc.save(outputPath);
		doc.close();
	}

	private void drawHeader(PDPageContentStream cs, String tableName, String comment, float y) throws IOException {
		// 背景
		setFillColor(cs, HEADER_BG);
		cs.addRect(MARGIN_LEFT, y - LINE_HEIGHT, TABLE_WIDTH, LINE_HEIGHT);
		cs.fill();

		// 文字
		cs.beginText();
		cs.setFont(HeadFont, 12);
		cs.setNonStrokingColor(Color.BLACK);
		cs.newLineAtOffset(MARGIN_LEFT + 5, y - 15);
		cs.showText(tableName + " _ " + comment);
		cs.endText();
	}

	private void drawFieldRow(PDPageContentStream cs, FieldInfo field, float y) throws IOException {
		// 背景色
		Color bg = field.isPrimaryKey ? PK_BG : ROW_BG;
		setFillColor(cs, bg);
		cs.addRect(MARGIN_LEFT, y - LINE_HEIGHT, TABLE_WIDTH, LINE_HEIGHT);
		cs.fill();

		// 图标（用 Unicode 替代 SVG）
		String icon = ""; // 可替换为其他符号

		// 字段文本
		String text = icon + field.name + " : " + field.type + " _ " + field.comment;

		cs.beginText();
		cs.setFont(ColumnFont, 10);
		cs.setNonStrokingColor(Color.BLACK);
		cs.newLineAtOffset(MARGIN_LEFT + 5, y - 14);
		cs.showText(text);
		cs.endText();
	}

	private void setFillColor(PDPageContentStream cs, Color color) throws IOException {
		float r = color.getRed() / 255f;
		float g = color.getGreen() / 255f;
		float b = color.getBlue() / 255f;
		cs.setNonStrokingColor(r, g, b);
	}

	// ===== 使用示例 =====
	public static void main(String[] args) throws IOException {
		PdfTableGenerator generator = new PdfTableGenerator();

		List<FieldInfo> fields = new ArrayList<>();
		fields.add(new FieldInfo("role_id", "bigint", "角色ID", true));
		fields.add(new FieldInfo("role_name", "varchar(30)", "角色名称", false));
		fields.add(new FieldInfo("role_key", "varchar(100)", "角色权限字符串", false));

		generator.generateTablePdf("sys_role", "角色信息表", fields, "table_structure.pdf");
		System.out.println("PDF 生成成功: table_structure.pdf");
	}
}