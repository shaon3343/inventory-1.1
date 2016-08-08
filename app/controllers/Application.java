package controllers;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import models.Product;
import dummy.DummyReceipt;
import dummy.HTMLGenerator;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	static Form<DummyReceipt> dummyReceipt = Form.form(DummyReceipt.class);
	public static Result index() {
		return ok(index.render("Create Receipt",dummyReceipt));
	}

	public static Result getProductById(){

		//System.out.println(request().getQueryString("productId"));
		try{
			String pId = request().getQueryString("productId");
			Product prod = Product.findByIdAndQty(Integer.parseInt(pId));
			if(prod==null){
				return ok("NO");
			}
			ObjectNode jsonResp =  toJSON(prod);
			System.out.println("############ jsonResponse: "+jsonResp);
			
			//return ok(""+prod.productPrice);
			return ok(jsonResp);
		}catch(Exception e){
			Logger.info("EXCEPTION",e);
			return ok("");
		}

	}

	

	private static ObjectNode toJSON(Product prod) {
		JsonNode json = request().body().asJson();
		ObjectNode result = Json.newObject();
		result.put("prodName",prod.productName);
		result.put("prodQty",prod.productQty);
		result.put("prodPrice",prod.productPrice);
		result.put("prodCode", prod.productCode);
		return result;
	}

	public static Result getHTML(){
		return ok(HTMLGenerator.generateROw());

	}

	public static Result submitReceipt(){

		return TODO;
	}
}
