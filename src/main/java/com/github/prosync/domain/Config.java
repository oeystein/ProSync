/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.prosync.domain;

import java.util.ArrayList;

/**
 *
 * @author Rubenhag
 */
public class Config {

    private ArrayList<String> modeValues;
    private ArrayList<String> resolutionValues;
    private ArrayList<String> fpsValues;
    private String modeSelected;
    private String resolutionSelected;
    private String fpsSelected;

    public Config() {
    }

    public String getModeSelected() {
        return modeSelected;
    }

    public void setModeSelected(String selected) {
        this.modeSelected = selected;
    }

    public ArrayList<String> getModeValues() {
        return modeValues;
    }

    public void setModeValues(ArrayList<String> modeValues) {
        this.modeValues = modeValues;
    }

    public ArrayList<String> getResolutionValues() {
        return resolutionValues;
    }

    public void setResolutionValues(ArrayList<String> resolutionValues) {
        this.resolutionValues = resolutionValues;
    }

    public String getResolutionSelected() {
        return resolutionSelected;
    }

    public void setResolutionSelected(String resolutionSelected) {
        this.resolutionSelected = resolutionSelected;
    } 
    

    public String getFpsSelected() {
        return fpsSelected;
    }

    public void setFpsSelected(String fpsSelected) {
        this.fpsSelected = fpsSelected;
    }

    public ArrayList<String> getFpsValues() {
        return fpsValues;
    }

    public void setFpsValues(ArrayList<String> fpsValues) {
        this.fpsValues = fpsValues;
    }
    
    
}
