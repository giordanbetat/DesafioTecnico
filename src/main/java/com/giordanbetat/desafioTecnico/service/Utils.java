package com.giordanbetat.desafioTecnico.service;

public class Utils {

	public String putSpace(String param, int qtd) {
        while (param.length() < qtd) {
            param += " ";
        }
        return param;
    }
    
    public String completeWith(String param, int qtd) {
        String returnParam = "";
        while (returnParam.length() < qtd) {
            returnParam += param;
        }
        return returnParam;
    }
	
}
