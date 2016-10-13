<h1 align="center">
  <a href="http://java.com/en"><img src="https://cloud.githubusercontent.com/assets/5771200/19331298/6f964780-9127-11e6-88bd-55ac19e1ad12.jpg" alt="Java" height="150"></a>
  <br>
  <br>
  Maths Tutor (Implementing RMI)
  <br>
  <br>
</h1>
<h4 align="center">A simple java client/server program to demonstrate RMI</h4>

<p align="center">
  <a href=""><img src="https://img.shields.io/travis/feross/standard/master.svg" alt="Passing"></a>
  <a href="https://java.com/en/"><img src="https://img.shields.io/badge/Java-1.8.0__101-brightgreen.svg" alt="Java 1.8.0"></a>
  <a href="https://opensource.org/licenses/BSD-2-Clause"><img src="https://img.shields.io/badge/License-BSD-blue.svg" alt="BSD License"></a>
</p>
<br>

## Table of Contents
- [Synopsis](#synopsis)
- [Install](#install)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [License](#license)

## Synopsis
The goal was to write a java application using Java TCP API Socket to implement a simple client/server
application using multi-threaded programming RMI.

The MathsTutor allows the user to practice arithmetic operations as chosen by 
the user. The user can select one of addition, subtraction, multiplication, or 
division. 

On receiving the user’s choice from the client side, the server has 
to send randomly generated double digit numbers to the client. The MathsTutor 
has got method to create arithmetic problems using randomly generated double
digit numbers. 

On receiving the problem, the client has to display the
problem and read the user input which is the answer consisting of an integer 
and send it to the server. The server has to invoke the method to execute 
the required operation, check the result and send a reply informing whether 
the answer is correct or incorrect to the client. 

On completion of 10 problems, the server has to send a summary of results, and 
allow the user choose another arithmetic operation if desired. The user should 
be able to stop the service by entering a single character ‘Q’ or ‘q’.
## Install
First, make a directory to install the files to and change to that directory using :
```bash
$ mkdir mathstutor && cd mathstutor
```
Then all you need to do is clone the project from github into the directory by using :
```bash
$ git clone https://github.com/josh-privata/MathsTutor_RMI.git
```
## Usage
##### Note:  [Java Runtime](https://java.com/en/download/) is required to run the preceding commands.
Initially the program needs to be compiled. After you have copied the *.java files to a directory, run the command :
```bash
$ javac *.java
```
Then run the program in a terminal window using the command :
```bash
$ java Server
```
And in a second terminal window use the command : 
```bash
$ java Client
```
## Screenshots
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331533/8c9198e8-9128-11e6-9d7b-3ef69bbd258e.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331536/8c9ae5a6-9128-11e6-9007-1d5f2c022932.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331532/8c8cbeae-9128-11e6-977e-becfa1ed1786.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331535/8c981808-9128-11e6-8cec-d5c27ff0bc6d.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331538/8cb8af50-9128-11e6-8808-c230958b7b0e.png" alt="Screenshot"></p>

## License
[BSD](LICENSE) Copyright (c) 2016 [Josh Cannons](http://joshcannons.com).
