package client;
import java.io.*;
	import java.net.InetSocketAddress;
	import java.net.Socket;
	import java.net.SocketAddress;
	import java.nio.channels.Channels;
	import java.nio.channels.ServerSocketChannel;
	import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.net.ConnectException;
import commands_client.*;
import utils.*;

public class Main {
		
		public static void main(String[] args) {
			System.out.println("[CLIENT-VERSION] [LOCALHOST]");
			ConnectToServer connection = new ConnectToServer();
	        connection.server_tunnel();
	        System.out.println("That's it  (¬‿¬)");
		}
	    
}



