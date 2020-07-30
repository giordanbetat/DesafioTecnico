package com.giordanbetat.desafioTecnico.service;

import java.io.IOException;

import com.giordanbetat.desafioTecnico.model.Report;

public interface IReportService {

	public Report verifyFiles(String fileDirectory) throws IOException;
	
	public void generateReport(String directoty, Report[] reports);
	
	public String findDirectoryAndGenerateReport(String inDirectory, String outDirectory) throws IOException;
	
}
