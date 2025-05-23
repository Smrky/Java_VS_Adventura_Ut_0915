/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Třída, která definuje Subject pro Observer
 *
 * @author Ondřej Smrček
 */
public interface Subject {
    
    void registerObserver(Observer observer);
    
    void removeObserver(Observer observer);
    
    void notifyObservers();
    
}
