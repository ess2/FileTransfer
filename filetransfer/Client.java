import java.net.*;
import java.io.*;
import java.util.*;

public class Client {

	private static String IP;
	private static int port;
	private static Socket sSocket;
	private static FileOutputStream outputFile;

	public static void getFile() {

		try {
			// definimos um diret�rio local para armazenamento do novo arquivo
			File getTransfer = new File("C:\\Desktop\\teste-copia.zip");
			outputFile = new FileOutputStream(getTransfer);

			/* criamos um array (de 1MB) para armazenar os elementos do arquivo e uma
			 * variavel para guardar o quanto j� foi lindo
			 */
			byte[] bufferByte = new byte[125000];
			int readBytes;

			// definimos o recebimento do arquivo (via socket)
			InputStream inputSocket = sSocket.getInputStream();

			/* enquanto houver dados restantes no array
			 * readBytes ser� != -1 e a leitura e o recebimentos dos elementos
			 * do array continuar� at� completar o arquivo no diretorio j� definido
			 */
			System.out.println("Recebendo arquivo...");
			while ((readBytes = inputSocket.read(bufferByte)) != -1) {
				outputFile.write(bufferByte, 0, readBytes);
				outputFile.flush();
			}

			System.out.println("Arquivo recebido!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// definindo a porta e o ip para criar o socket
		Scanner in = new Scanner(System.in);
		System.out.println("Defina a porta de conx�o:");
		port = in.nextInt();
		in.nextLine();
		System.out.println("Defina o IP de conex�o:");
		IP = in.nextLine();

		try {
			// criando a conex�o com o servidor por socket
			sSocket = new Socket(IP, port);
			System.out.println("Conectado com o servidor!");
			getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		in.close();
	}
}
