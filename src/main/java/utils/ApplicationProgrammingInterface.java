package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

public class ApplicationProgrammingInterface {

	private static final Logger LOGGER = Utils.getLogger(Utils.class.getName());

	/*
	COUPING-COHESION subteam 2: Method get và post của class này đang có LOGICAL COHESION
	Cải thiện: Tạo abstract class ApplicationProgrammingAbstract có các methods dùng 
		chung như setupConnection, allowMethods,... và 1 method abstract callApi 
	Sau đó tạo các method con extends ApplicationProgrammingAbstract và implement callApi
	Ví dụ: Tạo PostCaller và implement callApi tương tự method post của class hiện tại
		Tạo GetCaller và implement callApi tương tự method get của class hiện tại
	*/

	public static String get(String url, String token) throws Exception {
		LOGGER.info("Request URL: " + url + "\n");
		HttpURLConnection conn = setupConnection(url);

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder(); // using StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		response.append(inputLine + "\n");
		in.close();
		LOGGER.info("Response Info: " + response.substring(0, response.length() - 1));
		return response.substring(0, response.length() - 1);
	}

	public static String post(String url, String data) throws IOException {
		allowMethods("PATCH");
		HttpURLConnection conn = setupConnection(url);
		conn.setRequestMethod("PATCH");
		String payload = data;
		LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");

		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(payload);
		writer.close();
		BufferedReader in;
		String inputLine;
		if (conn.getResponseCode() / 100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);
		in.close();
		LOGGER.info("Response Info: " + response);
		return response.toString();
	}

	private static HttpURLConnection setupConnection(String url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		return conn;
	}

	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}
}
