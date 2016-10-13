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

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

/**
 * @filename Server.java
 *
 * @application RMI-MathsTutor
 *
 * @author Josh Cannons May 1st 2016
 *
 * @description
 *
 */
public class Server {

    // User Variables
    // System Variables
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 1099;
    private static final String SERVERNAME = "maths_server";
    private static Registry registry;

    // Constructor
    public Server() throws RemoteException {
    }

    // Go for launch
    public static void main(String argv[]) {
        System.setProperty("java.rmi.server.hostname", HOSTNAME);
        System.setProperty("java.security.policy", "AllPermission.policy");

        try {
            try {
                registry = LocateRegistry.createRegistry(PORT);
            } catch (ExportException ex) {
                registry = LocateRegistry.getRegistry(PORT);
            }
            System.out.println("[+] RMI Registry successfully created on //"
                    + HOSTNAME + ":" + PORT + "/" + SERVERNAME);
            try {
                ControllerInterface c = new Controller();
                registry.rebind(SERVERNAME, c);
                System.out.println("[+] Interface successfully bound to RMI Registry");
            } catch (Exception e) {
                System.out.println("[-] Error binding interface to RMI registry."
                                    + " Please restart the server");
            }
        } catch (RemoteException e) {
            System.out.println("[-] Error creating creating RMI Registry");
        }
    }
}
