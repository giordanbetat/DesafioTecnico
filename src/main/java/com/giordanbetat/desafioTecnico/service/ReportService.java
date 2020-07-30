package com.giordanbetat.desafioTecnico.service;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.giordanbetat.desafioTecnico.model.Report;

public class ReportService implements IReportService {

	public Report verifyFiles(String fileDirectory) throws IOException {

		Report report = new Report();
		Stream<String> lines = Files.lines(Paths.get(fileDirectory));

		lines.forEach((line) -> {
			line = line.trim();
			String[] data = line.split("ç");

			if (data[0].contains("001")) {
				report.setCountSellerAmount();
			} else if (data[0].contains("002")) {
				report.setCountCustomerAmount();
			} else if (data[0].contains("003")) {

				String[] params = data[2].replace("[", "").replace("]", "").split(",");

				double sumSale = 0;

				for (String param : params) {
					String[] sale = param.split("-");
					sumSale += Double.parseDouble(sale[2]);
				}

				if (report.getSumWorstSale() > sumSale) {
					report.setSumWorstSale(sumSale);
					report.setWorstSelesman(data[3]);
				}

				if (report.getSumBiggestSale() < sumSale) {
					if (report.getSumBiggestSale() == 0) {
						report.setSumWorstSale(sumSale);
						report.setWorstSelesman(data[3]);
					}
					report.setSumBiggestSale(sumSale);
					report.setBiggestSale(data[1]);
				}
			}
		});

		lines.close();
		return report;
	}

	public void generateReport(String directory, Report[] reports) {
		try {
			Report reportComplete = new Report();
			File file = new File(directory);

			if (!file.isDirectory()) {
				file.mkdirs();
			}

			directory = directory + reportComplete.getReportName() + ".dat";
			file = new File(directory);
			Utils util = new Utils();

			if (file.createNewFile()) {

				FileWriter writer = new FileWriter(directory);
				String biggestSale = reports[0].getBiggestSale();
				String worstSalesman = reports[0].getWorstSelesman();

				for (Report report : reports) {
					writer.write(util.completeWith("=", 123));
					writer.write("\n");
					writer.write("Nome do arquivo: " + util.putSpace(report.getReportFileInName(), 20)
							+ "Relatorio gerado: " + report.getReportName());
					writer.write("\n");
					writer.write(util.completeWith("=", 123));
					writer.write("\n");

					writer.write(util.putSpace("Clientes", 20));
					writer.write(util.putSpace("Vendedores", 20));
					writer.write(util.putSpace("ID vendedor do mês:", 50));
					writer.write("Vendedor com menos vendas:");

					writer.write("\n");

					writer.write(util.putSpace(String.valueOf(report.getCustomerAmount()), 20));
					writer.write(util.putSpace(String.valueOf(report.getSellerAmount()), 20));
					writer.write(util.putSpace(String.valueOf(report.getBiggestSale()), 50));
					writer.write(report.getWorstSelesman());

					writer.write("\n");
					writer.write("\n");

					reportComplete.setCustomerAmount(reportComplete.getCustomerAmount() + report.getCustomerAmount());
					reportComplete.setSellerAmount(reportComplete.getSellerAmount() + report.getSellerAmount());
				}

				writer.write("\n");
				writer.write(util.completeWith("=", 123));
				writer.write("\n");
				writer.write("TOTAL:");
				writer.write("\n");
				writer.write(util.completeWith("=", 123));
				writer.write("\n");

				writer.write(util.putSpace("Clientes:", 20));
				writer.write(util.putSpace("Vendedores:", 20));
				writer.write(util.putSpace("ID melhor vendedor:", 50));
				writer.write("Vendedor com menos vendas:");

				writer.write("\n");

				writer.write(util.putSpace(String.valueOf(reportComplete.getCustomerAmount()), 20));
				writer.write(util.putSpace(String.valueOf(reportComplete.getSellerAmount()), 20));
				writer.write(util.putSpace(biggestSale, 50));
				writer.write(worstSalesman);

				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String findDirectoryAndGenerateReport(String inDirectory, String outDirectory) throws IOException {

		File file = new File(inDirectory);

		if (!inDirectory.equals(System.getProperty("user.home") + "/data/in/")) {
			throw new NoSuchFileException("O arquivo de entrada não foi localizado no diretorio de origem!");
		}

		if (!outDirectory.equals(System.getProperty("user.home") + "/data/out/")) {
			throw new NoSuchFileException("Não foi possível criar o relatorio no diretorio destino.");
		}

		String[] files = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return file.isDirectory() && name.endsWith(".dat");
			}
		});

		if (files.length == 0) {
			throw new NoSuchFileException("O arquivo de entrada não foi localizado!");
		}

		Report[] reports = new Report[files.length];
		for (int i = 0; i < files.length; i++) {
			reports[i] = verifyFiles(inDirectory + files[i]);
			reports[i].setReportFileInName(files[i]);
		}

		generateReport(outDirectory, reports);

		return "Relatorio gerado com sucesso!";
	}

}
