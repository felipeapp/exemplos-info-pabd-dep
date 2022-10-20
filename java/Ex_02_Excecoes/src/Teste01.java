import java.util.Scanner;

public class Teste01 {

	public static void main(String[] args) {

		Scanner leitor = new Scanner(System.in);

		System.out.print("Digite dois números inteiros separados por espaço: ");
		int numero1 = leitor.nextInt();
		int numero2 = leitor.nextInt();

		leitor.close();

		try {
			int divisao = numero1 / numero2;
			System.out.println("A divisão entre os valores é " + divisao);
		} catch (ArithmeticException e) {
			System.out.println("Erro ao realizar divisão!");
		}

	}

}
