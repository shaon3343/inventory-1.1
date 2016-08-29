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
import models.SalesMen;
import dummy.DummyProduct;
import dummy.DummyReceipt;
import dummy.HTMLGenerator;
import dummy.Jutility;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import util.AppConst;
import views.html.*;

public class Application extends Controller {
	static Form<DummyReceipt> dummyReceipt = Form.form(DummyReceipt.class);
	public static Result testAutoSuggest(){
		return ok(testingAugoSuggest.render());
		
	}
	public static Result index() {
		return ok(index.render("Create Receipt",dummyReceipt));
	}
	public static Result getProductById(){
		try{
			String pId = request().getQueryString("productId");
			Product prod = Product.findByIdAndQty(Integer.parseInt(pId));			
			ObjectNode jsonResp =  toJSONProduct(prod);
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
		//	String pCode = request().getQueryString("term");
			
			final Map<String, String[]> data = request().body().asFormUrlEncoded();
			String pCode = data.get("term")[0];
			String exclude[] = data.get("exclude");
		//	exclude
			System.out.println("### TERM:"+pCode);
			
			//JsonNode json = request().body().asJson();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode json = mapper.readTree(exclude[0]);
			List<Map<String,String> > existProdList = new ArrayList<>(); 
			if(json!=null){
				 existProdList = Jutility.processJSON(json);
			}
			
			
			List<Product> prodList = Product.suggestProdList(pCode,existProdList);
		//	List<DummyProduct> dumProd = new ArrayList<DummyProduct>(); 
			
			/*for(Product p : prodList){
				DummyProduct dum = new DummyProduct();
				dum.id = p.id;
				dum.label=p.productName;
				dum.value = ""+p.productPrice;
				dumProd.add(dum);
			}*/
			
			final StringWriter sw =new StringWriter();
		    mapper = new ObjectMapper();
		    mapper.writeValue(sw, prodList);
		    String toJsonList = sw.toString();//use toString() to convert to JSON
		    sw.close(); 
		    return ok(toJsonList);
		}catch(Exception e){
			Logger.info("EXCEPTION",e);
			return ok("");
		}

	}

	private static ObjectNode toJSONProduct(Product prod) {
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
	
	private static ObjectNode toJSONSalesMan(SalesMen salesMen) {
		//JsonNode json = request().body().asJson();
		ObjectNode result = Json.newObject();
		if(salesMen==null){
			result.put(AppConst.salesManId,0);
			result.put(AppConst.salesManName,"");
			result.put(AppConst.salesManAddress,"");
			result.put(AppConst.salesManTotalDue,0);
			result.put(AppConst.salesManContact,"");
		}
		else{
			result.put(AppConst.salesManId,salesMen.id);
			result.put(AppConst.salesManName,salesMen.salesManName);
			result.put(AppConst.salesManAddress,salesMen.salesManAddress);
			result.put(AppConst.salesManTotalDue,salesMen.salesManTotalDue);
			result.put(AppConst.salesManContact,salesMen.salesManContact);
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
		boolean savedRec = false;
		if(json == null) {
	        return badRequest("Expecting Json data");
	    } else {
	          	
	       List<Map<String,String> > prodList = Jutility.processJSON(json);
	       boolean checkAllProd = Product.checkAllProdQty(prodList);
	       
	       if(checkAllProd){
	    	   
	    	   savedRec= Receipts.saveReceipts(prodList);
	       }
	       
	       
	    }
		String ret = "OK";
		ret = ((savedRec==true)?"OK":"NOT OK");
		
		if(ret.equals("NOT OK"))
			flash("receiptSaved","Failed to save receipt");
		else
			flash("receiptSaved","RECEIPT SAVED SUCCESSFULLY");
		return ok(ret);
	}
	
	public static Result getCustomerbyId(){
		try{
			String id =request().getQueryString("salesMenId");
			
			SalesMen man = SalesMen.findById(Integer.parseInt(id));
			ObjectNode jsonResp = toJSONSalesMan(man);
			return ok(jsonResp);
		}catch(Exception e){
			Logger.info("EXCEPTION",e);
			ObjectNode result =  Json.newObject();
			result.put(AppConst.salesManId,0);
			result.put(AppConst.salesManName,"");
			result.put(AppConst.salesManAddress,"");
			result.put(AppConst.salesManTotalDue,0);
			result.put(AppConst.salesManContact,"");
			return ok(result);
		}
	}
	
}
