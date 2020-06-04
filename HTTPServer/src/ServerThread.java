import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.stream.Stream;


public class ServerThread implements Runnable {
		
		private Socket socket;
		private int redniBroj;
		
		
		public ServerThread(Socket socket, int broj) {
			this.socket = socket;
			redniBroj = broj;
		}
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				System.out.println("Otvorena konekcija sa klijentom sa IP Adresom : " + socket.getInetAddress().getHostAddress() + " na portu: " + socket.getLocalPort() );
				
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
				
				Date today = new Date();
				String request = in.readLine();
				int i = 1;
				String typeOfRequest = null;
				String requestKey = null;
				
				while(in.ready()) {
					System.out.println(i + ": " + request);
					
					if (i == 1) {
						
						typeOfRequest = request.substring(0,request.indexOf(" "));
						
						request = request.substring(request.indexOf(" ")+1);
						
						request = request.substring(0, request.indexOf(" "));
						requestKey = request.toString();
						
						System.out.println("Tip zahteva je: " + typeOfRequest);
						System.out.println("Zahtev glasi: " + requestKey);
						
					}
					
					
					request = in.readLine();
					i++;
				
				}
				
				String response = "nista";
				
				if (typeOfRequest != null) {
					switch(typeOfRequest) {
					case("GET"):
						response = "Vracamo vam vasu stranicu";
						break;
					case("POST"):
						response = "Vasi unosi su zapamceni!";
						break;
					case("DELETE"):
						response = "To sto ste zeleli je izbrisano";
						break;
					case("VIEW"):
						response = "Pogled je prelep";
						break;
					case("PUT"):
						response = "Stavljeno";
						break;
					case("PATCH"):
						response = "Patch izvrsen";
						break;
					case("COPY"):
						response = "Kopirano";
						break;
						
						
						}
				}
				
				String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + "STA SMO URADILI ZA VAS: " + response + "\r\n" +  today + "\r\n"  ;
				
				socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
				
                
				
				socket.close();
				System.out.println("Zatvorena konekcija sa klijentom sa IP Adresom : " + socket.getInetAddress().getHostAddress() + " na portu: " + socket.getLocalPort() );
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

}
