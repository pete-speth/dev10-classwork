/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statstracker.model;

/**
 *
 * @author pspethmann
 */
public class Result<T> {
    
    private T output;
    private boolean valid;
    private String message;
    private boolean exitFlag;

    public T getOutput() {
        return output;
    }

    public void setOutput(T result) {
        this.output = result;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isExitFlag() {
        return exitFlag;
    }

    public void setExitFlag(boolean exitFlag) {
        this.exitFlag = exitFlag;
    }
    
    
}
