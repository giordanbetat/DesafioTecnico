package com.giordanbetat.desafioTecnico.model;

import java.time.LocalDateTime;

public class Report {

	private int customerAmount;
	private int sellerAmount;
	private double sumBiggestSale;
	private double sumWorstSale;
	private String biggestSale;
	private String worstSelesman;
	private String reportFileInName;
	private String reportName;

	public Report() {
		this.reportName = "REPORT" + "-"
				+ LocalDateTime.now().toString().replace(":", "").replace(".", "");
	}

	public int getCustomerAmount() {
		return customerAmount;
	}

	public void setCustomerAmount(int customerAmount) {
		this.customerAmount = customerAmount;
	}

	public void setCountCustomerAmount() {
		this.customerAmount++;
	}

	public int getSellerAmount() {
		return sellerAmount;
	}

	public void setSellerAmount(int sellerAmount) {
		this.sellerAmount = sellerAmount;
	}

	public void setCountSellerAmount() {
		this.sellerAmount++;
	}

	public String getBiggestSale() {
		return biggestSale;
	}

	public void setBiggestSale(String biggestSale) {
		this.biggestSale = biggestSale;
	}

	public String getWorstSelesman() {
		return worstSelesman;
	}

	public void setWorstSelesman(String worstSelesman) {
		this.worstSelesman = worstSelesman;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportFileInName() {
		return reportFileInName;
	}

	public void setReportFileInName(String reportFileInName) {
		this.reportFileInName = reportFileInName;
	}

	public double getSumBiggestSale() {
		return sumBiggestSale;
	}

	public void setSumBiggestSale(double sumBiggestSale) {
		this.sumBiggestSale = sumBiggestSale;
	}

	public double getSumWorstSale() {
		return sumWorstSale;
	}

	public void setSumWorstSale(double sumWorstSale) {
		this.sumWorstSale = sumWorstSale;
	}

}
