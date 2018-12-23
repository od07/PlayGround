package com.mytectra.springboot.playground;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class TestService {

//test your service	
@Test
 public void testService() throws Exception {
		URL url = new URL("http://localhost:8080/chocolates/Cadebury");

		URLConnection con = url.openConnection();
		con.setRequestProperty("content-type", "application/json");

		InputStreamReader osw = new InputStreamReader(con.getInputStream());
		int ch = 0;
		while(( ch = osw.read()) != -1) {
			System.out.print((char)ch);
		}
		
	}
	
}
