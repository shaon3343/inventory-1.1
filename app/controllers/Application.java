package controllers;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;


import models.Product;
import models.Receipts;
import dummy.DummyReceipt;
import dummy.HTMLGenerator;
import dummy.Jutility;
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
	
	/************************ SHOIKOITTA **********************/
	 /* public static Result provideJSONData() {

		  JsonNode json = request().body().asJson();

		  if (json == null) {
		   return badRequest("Expecting Json data");
		  } else {
		   String username = json.findPath("username").getTextValue();
		   String password = json.findPath("password").getTextValue();
		   if (username == null && password == null) {
		    return badRequest("Missing parameter.");
		   } else {
		    System.out.print("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" + username);
		    System.out.print("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP" + password);
		    Person person = new Person(username, password);

		    if (person.userName.equals("shoikat") && person.password.equals("123")) {

		     JsonNodeFactory jnf = JsonNodeFactory.instance;
		     ArrayNode arrayNode = new ArrayNode(jnf);

		     ObjectNode chAsJson = Json.newObject();

		     chAsJson.put("userId", 1);
		     chAsJson.put("apiKey", "C001");

		     arrayNode.add(chAsJson);

		     return ok(chAsJson);
		    } else {

		     return ok("");
		    }

		   }
		  }}*/
	
	/********************* SHOIKOITTA ENDS ***********************/

	public static Result getProductById(){

		//System.out.println(request().getQueryString("productId"));
		try{
			String pId = request().getQueryString("productId");
			Product prod = Product.findByIdAndQty(Integer.parseInt(pId));
			/*if(prod==null){
				return ok("NO");
			}*/
			ObjectNode jsonResp =  toJSON(prod);
			//	System.out.println("############ jsonResponse: "+jsonResp);

			//return ok(""+prod.productPrice);
			return ok(jsonResp);
		}catch(Exception e){
			Logger.info("EXCEPTION",e);
			ObjectNode jsonResp =  Json.newObject();
			jsonResp.put("prodName","");
			jsonResp.put("prodQty",0);
			jsonResp.put("prodPrice",0);
			jsonResp.put("prodCode","");
			return ok(jsonResp);
		}

	}



	private static ObjectNode toJSON(Product prod) {
		//JsonNode json = request().body().asJson();
		ObjectNode result = Json.newObject();
		if(prod==null){
			result.put("prodName","");
			result.put("prodQty",0);
			result.put("prodPrice",0);
			result.put("prodCode","");
		}
		else{
			result.put("prodName",prod.productName);
			result.put("prodQty",prod.productQty);
			result.put("prodPrice",prod.productPrice);
			result.put("prodCode", prod.productCode);
		}
		return result;
	}

	public static Result getHTML(){
		return ok(HTMLGenerator.generateROw());

	}
	public static Result checkProdQty(){
		int prodId=-1;
		int qty=-1;
		ObjectNode result = Json.newObject();
		try{
			prodId = Integer.parseInt(request().getQueryString("prodId"));
			qty = Integer.parseInt(request().getQueryString("prodQty"));
			List<Product> prod = Product.checkProdAndQty(prodId, qty);
			if(prod==null){
				result.put("isAvailable",false);
				result.put("prodName","");
				result.put("prodPrice",0);
				
			}
			else if(prod.isEmpty())
				result.put("isAvailable",false);
			else{
				result.put("prodName", prod.get(0).productName);
				result.put("prodPrice", prod.get(0).productPrice);
				result.put("isAvailable",true);
			}
		}catch(Exception e){
			Logger.info("Exception ",e);
			result.put("prodName","");
			result.put("prodPrice",0);
			result.put("isAvailable",false);
		}
		return ok(result);

	}

	public static Result submitReceipt(){
		
		//String json = request().getQueryString("jsonList");
		JsonNode json = request().body().asJson();
		System.out.println("JSON REQUEST: "+json);
		
		if(json == null) {
	        return badRequest("Expecting Json data");
	    } else {
	          	
	       List<Map<String,String> > prodList = Jutility.processJSON(json);
	       boolean checkAllProd = Product.checkAllProdQty(prodList);
	       if(checkAllProd){
	    	   
	    	   boolean savedRec = Receipts.saveReceipts(prodList);
	       }
	       
	       
	    }
		flash("receiptSaved","RECEIPT SAVED SUCCESSFULLY");
		return ok("OK");
	}
	
	
	
}
