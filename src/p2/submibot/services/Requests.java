package p2.submibot.services;

import p2.submibot.resources.Assignment;
import p2.submibot.resources.FileUploadPermission;
import p2.submibot.resources.Response;
import p2.submibot.resources.Submission;
import p2.submibot.util.MultipartUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Requests {

	private String token;
	private String course;

	public Requests(String token, String course) {
		this.token = "Bearer " + token;
		this.course = course;
	}

	public List<Assignment> getAssignments() throws Exception {
		URL urlObj = new URL("https://canvas.instructure.com/api/v1/courses/" + this.course + "/assignments");
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestProperty("Authorization", this.token);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		Gson gson = new Gson();
		@SuppressWarnings("unchecked")
		List<Assignment> assigs = (ArrayList<Assignment>) gson.fromJson(response.toString(),
				new TypeToken<ArrayList<Assignment>>() {
				}.getType());

		return assigs;
	}

	private String postFile(String assignment) throws IOException {
		URL urlObj = new URL("https://canvas.instructure.com/api/v1/courses/" + this.course + "/assignments/"
				+ assignment + "/submissions/self/files");
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", this.token);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

	public String submitAssignment(String assignment, String filename) throws IOException {
		Gson gson = new Gson();

		String r = this.postFile(assignment);
		FileUploadPermission re = (FileUploadPermission) gson.fromJson(r, new TypeToken<FileUploadPermission>() {
		}.getType());

		MultipartUtility mu = new MultipartUtility(re.toString(), "UTF-8");
		mu.addFilePart("", new File(filename));
		List<String> res = mu.finish();
		Response resp = (Response) gson.fromJson(res.get(0), new TypeToken<Response>() {
		}.getType());

		String respo = this.submit(assignment, resp.toString());
		Submission response = (Submission) gson.fromJson(respo, new TypeToken<Submission>() {
		}.getType());
		return response.toString();
	}

	private String submit(String assignment, String id) throws IOException {
		URL urlObj = new URL("https://canvas.instructure.com/api/v1/courses/" + this.course + "/assignments/"
				+ assignment + "/submissions");
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Authorization", this.token);
		con.getOutputStream().write(
				("submission%5Bsubmission_type%5D=online_upload&submission%5Bfile_ids%5D%5B%5D=" + id).getBytes());
		return readResponse(con);
	}

	private String readResponse(HttpURLConnection connection) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

	public boolean isValid() {
		
		try {
			URL urlObj = new URL("https://canvas.instructure.com/api/v1/users/self");
			HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
			connection.setRequestProperty("Authorization", this.token);
			
			readResponse(connection);

		} catch (IOException ioe) {
			return false;
		}
		
		return true;
	}
}