package com.littleppurio.client;

public class Socket {
    private Socket() {}

    private static class SingletonHolder {
        public static final Socket CLIENT = new Socket();
    }

    public static Socket getInstance() {
        return SingletonHolder.CLIENT;
    }
}
