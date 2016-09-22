package com.mycompany.nncloudrestservice.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

import com.mycompany.nncloudrestservice.logic.UploadInput;

@Path("upload_inputs")
public class SetInputVector {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	public Response uploadInputVector(String x)
	{
		UploadInput ui = new UploadInput();
		ui.uploadInput(new JSONObject(x));
		return Response.status(204).build();
	}
}
