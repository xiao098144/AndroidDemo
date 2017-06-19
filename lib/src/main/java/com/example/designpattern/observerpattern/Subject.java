package com.example.designpattern.observerpattern;

/**
 * FileName:com.example.designpattern.observerpattern.Subject.java
 * Created on 2016/9/4
 * Version V1.0
 */
public interface Subject {

    public void registerObserver(Observer o);

    public void unRegisterObserver(Observer o);

    public void notifyDataChanged();

}
