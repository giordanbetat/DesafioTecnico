package com.giordanbetat.desafioTecnico;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.giordanbetat.desafioTecnico.service.IReportService;
import com.giordanbetat.desafioTecnico.service.ReportService;

public class App {
	
	public static void main(String[] args) throws IOException {

		Logger logger = Logger.getLogger(App.class);
		BasicConfigurator.configure();
		
		try {
			String inDirectory = System.getProperty("user.home") + "/data/in/";
			String outDirectory = System.getProperty("user.home") + "/data/out/";
			
			IReportService service = new ReportService();
			
			logger.info(service.findDirectoryAndGenerateReport(inDirectory, outDirectory));
			
		} catch (NoSuchFileException e) {
			logger.error(e.getMessage());
		}
	}
}
