package com.br.manager.controlle.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas Brasilio dos Santos
 * @since 03-07-2016
 * @version 1.0
 * 
 */
public interface Command {
    public void execute(HttpServletRequest request, HttpServletResponse response, String commando);    
}
