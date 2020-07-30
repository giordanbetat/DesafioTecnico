package com.giordanbetat.desafioTecnico.test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.Assert;
import org.junit.Test;

import com.giordanbetat.desafioTecnico.service.IReportService;
import com.giordanbetat.desafioTecnico.service.ReportService;

public class ReportServiceTest {

	private IReportService service = new ReportService();
	
	@Test
	public void generateReport() throws IOException {
		
		String inDirectory = System.getProperty("user.home") + "/data/in/";
		String outDirectory = System.getProperty("user.home") + "/data/out/";
		
		Assert.assertEquals("Relatorio gerado com sucesso!", service.findDirectoryAndGenerateReport(inDirectory, outDirectory));		
	}
	
	@Test(expected = NoSuchFileException.class)
	public void insertInputDirectoryInvalid() throws IOException {
		
		String inDirectory = System.getProperty("user.home") + "/data/i";
		String outDirectory = System.getProperty("user.home") + "/data/out/";
		
		service.findDirectoryAndGenerateReport(inDirectory, outDirectory);		
	}
	
	@Test(expected = NoSuchFileException.class)
	public void insertInputDirectoryInvalid2() throws IOException {
		
		String inDirectory = System.getProperty("user.home") + "/data/in";
		String outDirectory = System.getProperty("user.home") + "/data/out/";
		
		service.findDirectoryAndGenerateReport(inDirectory, outDirectory);		
	}
	
	@Test(expected = NoSuchFileException.class)
	public void insertInputDirectoryNull() throws IOException {
		
		String inDirectory = System.getProperty("user.home") + null;
		String outDirectory = System.getProperty("user.home") + "/data/out/";
		
		service.findDirectoryAndGenerateReport(inDirectory, outDirectory);		
	}
	
	@Test(expected = NoSuchFileException.class)
	public void insertOutputDirectoryNull() throws IOException {
		
		String inDirectory = System.getProperty("user.home") + "/data/in";
		String outDirectory = System.getProperty("user.home") + null;
		
		service.findDirectoryAndGenerateReport(inDirectory, outDirectory);		
	}
		
}
