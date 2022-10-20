import java.util.InputMismatchException;
import java.util.Scanner;

public class Teste02 {

	public static double lerNota(Scanner leitor, String mensagem) {
		while (true) {
			try {
				System.out.print(mensagem);
				double nota = leitor.nextDouble();

				if (nota >= 0 && nota <= 10)
					return nota;

				System.out.println("Nota inválida, tente novamente!");
			} catch (InputMismatchException e) {
				System.out.println("O valor digitado não é numérico!");
				leitor.nextLine();
			}
		}
	}

	public static void main(String[] args) {

		Scanner leitor = new Scanner(System.in);

		double nota1 = lerNota(leitor, "Digite a primeira nota: ");
		double nota2 = lerNota(leitor, "Digite a segunda nota: ");

		leitor.close();

		double media = (nota1 + nota2) / 2;
		System.out.println("A média aritmética do aluno é " + media);

		if (media >= 7)
			System.out.println("Aluno aprovado!");
		else if (media < 3)
			System.out.println("Aluno reprovado");
		else
			System.out.println("Aluno em recuperação!");

	}

}
