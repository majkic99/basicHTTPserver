import java.net.ServerSocket;
import java.net.Socket;


public class ServerMain {
		private int redniBroj = 1;
		
		public ServerMain() throws Exception {
			
			
			ServerSocket ss = new ServerSocket(2020);
			System.out.println("Listening for connection on port 2020 ....");
			while (true) {
				Socket sock = ss.accept();
				
				
				ServerThread st = new ServerThread(sock, redniBroj);
				redniBroj++;
				Thread t = new Thread(st);
				t.start();
			}
		}
		
		
		
		public static void main(String[] args) {
			try {
				new ServerMain();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
}
