package br.com.leonardo.unittest;

public class Metrics {
	public int absSum(Integer op1, Integer op2) {
		int retorno = 0;

		if (op1 == null && op2 == null) {
			retorno = 0;
		//} else if (op2 != null && op1 == null) { - else if antigo que dá 100% de branch coverage	
		} else if (op1 == null && op2 != null) {
			retorno = Math.abs(op2);

		} else if (op2 == null) {
			retorno = Math.abs(op1);

		} else {
			retorno = Math.abs(op1) + Math.abs(op2);
		}

		return retorno;
	}
}
