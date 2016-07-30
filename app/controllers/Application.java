package controllers;

import models.Product;
import dummy.DummyReceipt;
import dummy.HTMLGenerator;
import play.*;
import play.data.Form;
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
    	   	
    	return ok(""+prod.productPrice);
    	}catch(Exception e){
    		Logger.info("EXCEPTION",e);
    		return ok("");
    	}
    	
    }
    
    public static Result getHTML(){
    	return ok(HTMLGenerator.generateROw());
    	
    }
    
    public static Result submitReceipt(){
    	
		return TODO;
    }
}
