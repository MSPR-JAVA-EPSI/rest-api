package fr.epsi.mspr.restapi.visageapi;

import java.net.URI;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class FaceRecognitionStream {

	private static String subscriptionKey = "";
	private static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect";

	FaceRecognitionStream() {
		subscriptionKey = System.getenv().get("MSPR_MICROSOFT_API_KEY");
	}
	
	public static UUID getFaceId(byte[] bytes) {

		HttpClient httpclient = HttpClientBuilder.create().build();
		try {
			URIBuilder builder = new URIBuilder(uriBase);

			// Request parameters. All of them are optional.
			builder.setParameter("returnFaceId", "true");
			builder.setParameter("returnFaceLandmarks", "false");
			builder.setParameter("returnFaceAttributes", "");

			// Prepare the URI for the REST API call.
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);

			// Request headers.
			request.setHeader("Content-Type", "application/octet-stream");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			// Request body.
			ByteArrayEntity reqEntity = new ByteArrayEntity(bytes, ContentType.APPLICATION_OCTET_STREAM);
			request.setEntity(reqEntity);

			// Execute the REST API call and get the response entity.
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// Format and display the JSON response.
				String jsonString = EntityUtils.toString(entity).trim();
				System.out.println(jsonString);
				if (jsonString.charAt(0) == '[') {
					JSONArray jsonArray = new JSONArray(jsonString);
					JSONObject object = jsonArray.getJSONObject(0);
					return UUID.fromString(object.getString("faceId"));
				} else if (jsonString.charAt(0) == '{') {
					JSONObject jsonObject = new JSONObject(jsonString);
					return UUID.fromString(jsonObject.toString(2));
				}
			}
		} catch (Exception ex) {
			System.out.println(FaceComparaison.class.getName() + "> " + ex.getMessage());
		}
		return null;
	}

}