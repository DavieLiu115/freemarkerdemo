package com.wd;

import com.alibaba.fastjson.JSONObject;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;
import freemarker.template.Version;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	public void testdo() throws Exception {
		BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
		TemplateHashModel staticModels = wrapper.getStaticModels();



		Configuration cfg = new Configuration(new Version("2.3.28"));
		cfg.setSharedVariable("templates", BeansWrapper.getDefaultInstance().getStaticModels());
		cfg.setClassForTemplateLoading(AppTest.class, "/templates");
		try (Writer out = new OutputStreamWriter(new FileOutputStream("demo.go"),
				StandardCharsets.UTF_8)) {
			String templatePath = "demo.ftl";
			Template template = cfg.getTemplate(templatePath);
			URL resource = AppTest.class.getClassLoader().getResource("mamiho.json");
			try (InputStream inputStream = resource.openStream()) {
				JSONObject jsonObject = JSONObject.parseObject(new String(inputStream.readAllBytes()));
				jsonObject.put("statics", staticModels);
				template.process(jsonObject, out);
			} catch (Exception ignored) {
			}
			// 输出结果
			String result = out.toString();
			System.out.println(result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
