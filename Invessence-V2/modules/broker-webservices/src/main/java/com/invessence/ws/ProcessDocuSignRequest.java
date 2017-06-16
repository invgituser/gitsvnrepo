package com.invessence.ws;

import java.io.*;
import java.util.Scanner;

import com.invessence.ws.service.ServiceLayerImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 9/21/2016.
 */
public class ProcessDocuSignRequest
{
   public static void main(String[] args) {

      // using Scanner
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter acctNum: ");
      String acctNum = scanner.nextLine();
      System.out.print("Enter eventNum: ");
      String eventNum = scanner.nextLine();
      System.out.println("Entered acctNum is: " + acctNum+" and eventNum is: " + eventNum);

      ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("webServicesConfig.xml");
      ServiceLayerImpl serviceLayer = (ServiceLayerImpl) context.getBean("serviceLayerImpl");

      serviceLayer.processDCRequest(new Long(acctNum), Integer.parseInt(eventNum));
   }
}


//// using InputStreamReader
//try {
//   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//   System.out.print("Enter your name: ");
//
//   String name = reader.readLine();
//   System.out.println("Your name is: " + name);
//   } catch (IOException ioe) {
//   ioe.printStackTrace();
//   }

//   // using Console
//   Console console = System.console();
//if (console == null) {
//   System.out.println("No console: not in interactive mode!");
//   System.exit(0);
//   }
//
//   System.out.print("Enter your username: ");
//   String username = console.readLine();
//
//   System.out.print("Enter your password: ");
//   char[] password = console.readPassword();
//
//   System.out.println("Thank you!");
//   System.out.println("Your username is: " + username);
//   System.out.println("Your password is: " + String.valueOf(password));
//
//   // using Console with formatted prompt
//   String job = console.readLine("Enter your job: ");
//
//   String passport = console.readLine("Enter your %d (th) passport number: ", 2);
//
//   System.out.println("Your job is: " + job);
//   System.out.println("Your passport number is: " + passport);
