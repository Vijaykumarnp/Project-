package com.xworkz.rentalapp.parkingconfiguration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.xworkz.rentalapp.Constants.Constants;


//creating dispatcher servlet
public class ParkingWebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ParkinConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		int maxSize= Constants.MAX_FILE_SIZE;
		MultipartConfigElement multipartConfigElement= new MultipartConfigElement(Constants.TEMP_LOCATION, maxSize, maxSize*2, maxSize/2);
		registration.setMultipartConfig(multipartConfigElement);
	}
	
	

}
