package com.example.rpcum.studentdirectory.MainScreens;

public class LoggedInTuple {

    public static String usernameLog;
    public static boolean loggedIn = false;

    public static class loggedInInner {


        public void toggle(String username) {
            if(usernameLog.equals(username)) {
                if (loggedIn == false) {loggedIn = true;}
                else if(loggedIn == true) {loggedIn = false;}
            }
        }

        public void logOut(String username) {
            if(usernameLog.equals(username)) {
                loggedIn = false;
            }
        }

        public void logIn(String username) {
            if(usernameLog.equals(username)) {
                loggedIn = true;
            }
        }
    }



}
