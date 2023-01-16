/**
 * 
 */
package com.kongstore.kongstore;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * @author 3189
 *
 */
public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		String templateName = "\\src\\main\\resources\\templates\\test";
		VelocityEngine velocityEngine = new VelocityEngine();// context.getBean(VelocityEngine.class);
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

		List<String> methods = new ArrayList<>();
		methods.add("GET");
		methods.add("POST");
		methods.add("PUT");
		methods.add("PATCH");

		
		
		
		VelocityContext velocityContext = new VelocityContext();

//		velocityContext.put("end", methods.size());
		velocityContext.put("range", methods);

//		velocityContext.put("path1", "/weather/{name}");			

		Template t = velocityEngine.getTemplate(templateName + ".vm");
		StringWriter stringWriter = new StringWriter();
		t.merge(velocityContext, stringWriter);

		final String finalRequest = stringWriter.toString();
		System.out.println("finalRequest-------->"+finalRequest);
	}

}
