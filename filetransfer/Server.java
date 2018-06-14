import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

	public static ServerSocket sSocket;
	public static Socket cSocket;
	private static BufferedInputStream bufferInput;

	public static void fileTransfer() {

		try {
			/*
			 * definimos a localizacao do arquivo que queremos enviar e definimos um array
			 * para armazenar os elementos desse arquivo
			 */
			File toTransfer = new File("C:\\Desktop\\testes.zip");
			byte[] bufferByte = new byte[(int) toTransfer.length()];

			/*
			 * criamos uma copia do arquivo para leitura do mesmo e armazenamento dos
			 * elementos dele no array criado
			 */
			FileInputStream inputFile = new FileInputStream(toTransfer);
			bufferInput = new BufferedInputStream(inputFile);
			bufferInput.read(bufferByte, 0, bufferByte.length);

			// definimos o envio do arquivo (via socket)
			OutputStream outSocket = cSocket.getOutputStream();
			System.out.println("Enviando arquivo...");

			// lemos os objetos do array e enviamos os elementos do arquivo
			outSocket.write(bufferByte, 0, bufferByte.length);
			outSocket.flush();
			outSocket.close();
			System.out.println("Arquivo enviado!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Digite a Porta do Servidor:");
		int portID = teclado.nextInt();
		try {
			// criando o servidor na portID
			sSocket = new ServerSocket(portID);
			System.out.println("Aguardando conexão...");
			// conectando cliente
			cSocket = sSocket.accept();
			System.out.println("Cliente conectado!");
			fileTransfer();
			sSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		teclado.close();
	}

}