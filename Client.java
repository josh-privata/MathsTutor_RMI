/*
 * Copyright (c) 2016, josh
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * @filename Client.java
 *
 * @application RMI-MathsTutor
 *
 * @author Josh Cannons May 1st 2016
 *
 * @description
 *
 */
public class Client {

    // User Variables
    private static final String HOSTNAME = "localhost";
    private static final String SERVERNAME = "maths_server";
    private static final int PORT = 1099;
    // System Variables
    private static String input;
    private static boolean b;
    private static MathsTutorInterface maths;
    private static Scanner in;

    // Go for launch
    public static void main(String argv[]) {
        System.setProperty("java.security.policy", "AllPermission.policy");

        try {
            // Connect to RMI Registry
            Registry registry = LocateRegistry.getRegistry(HOSTNAME, PORT);
            ControllerInterface c = (ControllerInterface) registry.lookup(SERVERNAME);
            maths = c.newMathsTutor();
            System.out.println("[+] Successfully connected to registry on //"
                    + HOSTNAME + ":" + PORT + "/" + SERVERNAME);
            in = new Scanner(System.in);

            // User login
            System.out.print("Please enter your name : ");
            System.out.println(maths.setUser(in.nextLine()));

            // Begin MathsTutor operations
            b = true;
            while (b) {
                System.out.print(maths.getMenu());
                input = in.nextLine();
                if (input.equalsIgnoreCase("q")) {
                    System.out.println(maths.printAllResults());
                    System.out.println("[+] Connection to registry has been ended by the client");
                    b = false;
                    break;
                }
                maths.setOperation(input);
                maths.iterateAttempt();
                maths.setRight(0);
                for (int i = 0; i < maths.getNumberOfQuestions(); i++) {
                    System.out.print(maths.setProblem());
                    int response = 0;
                    try {
                        input = in.nextLine();
                        if (input.equalsIgnoreCase("q")) {
                            System.out.println(maths.printAllResults());
                            System.out.println("[+] Connection to registry has been ended by the client");
                            b = false;
                            break;
                        }
                        response = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                    }
                    maths.checkAnswer(response);
                }
                maths.saveResult();
                System.out.println("\n" + maths.printResult());
            }

        } catch (RemoteException | NotBoundException e) {
            System.out.println("[-] Failed to get connection to registry");
        }
    }
}
