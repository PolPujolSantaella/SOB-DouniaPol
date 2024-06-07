/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

/**
 *
 * @author douni
 */
public interface UsuariCredencials {
    public Long findUser(String username);
    public boolean login(String username, String password);
    
}