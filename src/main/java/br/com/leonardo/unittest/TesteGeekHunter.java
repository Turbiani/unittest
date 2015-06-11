package br.com.leonardo.unittest;

import java.util.ArrayList;

public class TesteGeekHunter {
	
	private static int[] calculos;
	
	private static ArrayList<Integer> calculos2 = new ArrayList<Integer>(); 
	
	public static void main(String[] args) {
		/*int n = 2;
		
		System.out.println(2*(n -1) + 3*(n-2));*/
		
		//calculaSn(1);
		
		//funciona mas sem com formula errada
		//calculaSn2(3);
		
		calculaSn3(1);

		
	}
	
	
	private static ArrayList<Integer> calculaSn3(int n){
		if(calculos2.size()==0){
			calculos2.add(2);
			calculos2.add(3);
		}
		//Dadon ArrayIndexOutOfBoundsException =/
		int calculo = 2*(calculos2.get(n -1)==null ? 0 :calculos2.get(n -1)) + 3*(calculos2.get(n-2)==null ? 0 : calculos2.get(n-2));
		//guardando o valor de sn
		calculos2.add(calculo);
		System.out.println(calculos2.get(n));
		n--;
		if(n>=1){
			calculaSn3(n);
		}else{
			return calculos2;
		}
		
		return null;
	}
	
	
	private static int[] calculaSn(int n){
		if(calculos==null){
			calculos = new int[n+1];
			calculos[0]   = 2;
			calculos[1]   = 3;
		}
		
		int calculo = 2*calculos[(n -1)] + 3*calculos[(n-2)];
		//guardando o valor de sn
		calculos[n] = calculo;
		n--;
		System.out.println(calculo);
		if(n>=1){
			calculaSn(n);
		}else{
			return calculos;
		}
		
		return null;
	}
	
	private static void calculaSn2(int n){
		//criar um array para gravar o valor de Sn para utilizar na formula
		int calculo = 2*(n -1) + 3*(n-2);
		n--;
		System.out.println(calculo);
		if(n>=1){
			calculaSn2(n);
		}		
		
	}
	
	

}
