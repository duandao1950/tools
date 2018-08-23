package com.tools.mail;

public class Main {
    public static void main(String[] args) {
        try {
            new ReceiveMailHandler().receiveMail("zhoushu@mamahao.com", "DDffccvv1234");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}