
package controllers;

import java.util.Date;
import java.util.List;

import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.*;
import play.data.*;
import play.*;
import play.mvc.Http;
import play.mvc.Result;
import util.AppConst;
import views.html.setInventory.*;

public class HRProductManage extends Controller{

	static Form<Product> ProductForm = Form.form(Product.class);
	
	public static Result create() {
        return ok(create.render(ProductForm));
    }
	 public static Result save() {
		 Form<Product> filledForm = ProductForm.bindFromRequest();
		 Product product = filledForm.get();
		 
		 Product.create(product);
	   	 flash("success", AppConst.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.HRProductManage.list());
	    }

	 public static Result list(){
	    	List<Product> Products = Product.all();
	     	return ok(list.render(Products));
	    }

	 
	 public static Result show(Long id) {
			Product prod = Product.findById(id);
					
		  	if (prod == null) {
				flash("error", AppConst.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.HRProductManage.list());
			} else
				return ok(show.render(prod));
		}
	 
	 public static Result edit(Long id) {
		 Product prod = Product.findById(id);
			
		  	if (prod == null) {
				flash("error", AppConst.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.HRProductManage.show(id));
			}else
				return ok(edit.render(ProductForm.fill(prod)));
		}
	 
	 
	 public static Result update(){
			Form<Product> filledForm = ProductForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Product Product = filledForm.get();
			
			Long ProductId = Product.id;
			
			Product prod = Product.findById(ProductId);
			prod.productName = Product.productName;
			prod.productCode = Product.productCode;
			prod.productPrice = Product.productPrice;
			prod.productQty = Product.productQty;
			
			Product.update(prod);
			
			return ok(list.render(Product.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Product.delete(id);
		 flash("success", AppConst.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Product.all()));
	 }
	 
	 
}


