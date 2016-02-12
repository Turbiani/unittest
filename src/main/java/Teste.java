import java.math.BigDecimal;
import java.util.Scanner;


public class Teste {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(System.in);
		BigDecimal A = new BigDecimal(new String(entrada.nextLine()).replace(",", "."));
		BigDecimal B = new BigDecimal(new String(entrada.nextLine()).replace(",", "."));
		A = A.multiply(new BigDecimal("3.5"));
		B = B.multiply(new BigDecimal("7.5"));
		
		BigDecimal MEDIA = A.add(B);
		MEDIA = MEDIA.divide(new BigDecimal(11), 5 ,BigDecimal.ROUND_HALF_UP);
		
		System.out.println("PROD = " + MEDIA);
		
		
	}	
}
