package com;

import model.Hospital;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospital")
public class HospitalService {
	Hospital itemObj = new Hospital();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readItems();
		
	}


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("H_Name") String H_Name, 
			@FormParam("H_Address") String H_Address, 
			@FormParam("H_City") String H_City,
			@FormParam("H_phonenumber") String H_phonenumber,
			@FormParam("H_Desc") String H_Desc
			) 
	{
		String output = itemObj.insertItem( H_Name, H_Address, H_City,H_phonenumber,H_Desc);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) { // Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();

		// Read the values from the JSON object
		String Hospital_ID = itemObject.get("Hospital_ID").getAsString();
		String H_Name = itemObject.get("H_Name").getAsString();
		String H_Address = itemObject.get("H_Address").getAsString();
		String H_City = itemObject.get("H_City").getAsString();
		String H_phonenumber = itemObject.get("H_phonenumber").getAsString();
		String H_Desc = itemObject.get("H_Desc").getAsString();

		String output = itemObj.updateItem(Hospital_ID, H_Name, H_Address, H_City, H_phonenumber,H_Desc);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	
{
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
		//Read the value from the element <itemID> 
		String Hospital_ID = doc.select("Hospital_ID").text();

		String output = itemObj.deleteItem(Hospital_ID);

		return output;
	}
	
	//read file hospitals from appointments
	
	@GET
	@Path("/HospitalAppointments/{Hospital_ID}")
	@Produces(MediaType.TEXT_HTML)
	public String readHospitalAppointmentsHistory(@PathParam("Hospital_ID") String Hospital_ID) {
		return itemObj.readHospitalAppointmentsHistory(Hospital_ID);
		//appointments details for selected hospital
	}

}