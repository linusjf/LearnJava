package com.javacodegeeks.patterns.singletonpattern;
public class SingletonHolder {

    // Static member class member that holds only one instance of the
    // SingletonHolder class
    private static class Holder{
    public static SingletonHolder singletonInstance =
                                          new SingletonHolder();
    }
    // SingletonHolder prevents any other class from instantiating
    private SingletonHolder() {
    }

    // Providing Global point of access
    public static SingletonHolder getSingletonInstance() {
        return Holder.singletonInstance;
    }
}
