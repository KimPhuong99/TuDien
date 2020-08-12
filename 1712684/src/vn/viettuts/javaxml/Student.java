/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
package vn.viettuts.javaxml;
public class Student {
     private String Tu;
    private String Nghia;
    public String getTu()
    {
       return Tu;
    }
    public String getNghia()
    {
    return Nghia;
    }
    public void setTu(String tu)
    {
    this.Tu=tu;
    }
    public void setNghia(String nghia)
    {
        this.Nghia=nghia;
    }
    @Override
    public String toString() {
        return "\n***********Tu:" + Tu + "\n*Nghia:" + Nghia ;
    }
}
