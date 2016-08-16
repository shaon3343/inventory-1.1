package controllers;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
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
	public static Result testAutoSuggest(){
		return ok(testAutoSuggest.render());
		
	}
	public static Result index() {
		return ok(index.render("Create Receipt",dummyReceipt));
	}
	public static Result getProductById(){
		try{
			String pId = request().getQueryString("productId");
			Product prod = Product.findByIdAndQty(Integer.parseInt(pId));			
			ObjectNode jsonResp =  toJSON(prod);
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

	public static Result getProductList(){

		//System.out.println(request().getQueryString("productId"));
		try{
			String pCode = request().getQueryString("productCode");
			List<Product> prodList = Product.suggestProdList(pCode);
			final StringWriter sw =new StringWriter();
		    final ObjectMapper mapper = new ObjectMapper();
		    mapper.writeValue(sw, prodList);
		    String toJsonList = sw.toString();//use toString() to convert to JSON
		    sw.close(); 
		    return ok(toJsonList);
		}catch(Exception e){
			Logger.info("EXCEPTION",e);
			return ok("");
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
		
		JsonNode json = request().body().asJson();
		//System.out.println("JSON REQUEST: "+json);
		
		if(json == null) {
	        return badRequest("Expecting Json data");
	    } else {
	          	
	       List<Map<String,String> > prodList = Jutility.processJSON(json);
	       return ok(HTMLGenerator.generateROw(prodList));
	    }
		

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
	//	System.out.println("JSON REQUEST: "+json);
		
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
