package controllers;

import java.io.File;
import java.util.ArrayList;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

import util.AppConst;
import util.ParseSpreadsheet;
import views.html.setInventory.*;

public class PushData extends Controller {
	
	public static Result uploadPage(){
		
		return ok(uploadproduct.render());

	}
	
	public static Result xlsupload(){
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart uploaded_file = body.getFile("uploaded_file");

		//if (uploaded_file == null || !uploaded_file.getContentType().equals("application/vnd.ms-excel")) {
		String fileName="";

		if(uploaded_file == null) {
			flash("FLASH_ERROR_UPLOAD", "Sorry, there was some problem. Please try again after some time");
			return redirect(routes.PushData.uploadPage());

		} else {
			String filename = uploaded_file.getFilename();
			String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());

			String excel = "xlsx";
			if(!extension.equals(excel)){
				flash("FLASH_ERROR_UPLOAD", "Sorry, please upload .xlsx file");
				return redirect(routes.PushData.uploadPage());
			}

			File fileXls = uploaded_file.getFile();
			String fileNameXls = uploaded_file.getFilename();
			String path = AppConst.EXCEL_UPLOAD_DIR + fileNameXls;
			
			boolean isParsed = new ParseSpreadsheet().getDataFromSpreadsheet(fileXls);
			
			if(isParsed){
				flash("FLASH_SUCCESS_UPLOAD", "Uploaded and saved to Database");
				return redirect(routes.PushData.uploadPage());
			}else{
				flash("FLASH_ERROR_UPLOAD", "EXCEL Format is not right");
				return redirect(routes.PushData.uploadPage());
			}
		}
		
	}
}
