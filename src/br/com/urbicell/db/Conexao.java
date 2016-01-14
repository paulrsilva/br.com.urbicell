/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.urbicell.db;

import java.sql.*;


/**
 *
 * @author paulinorochaesilva
 */
public class Conexao {
    
    public static boolean conectado;
    
    public void conecte(){
        
        ResultSet rs;
        Statement MeuState;
        
        try{
            String url="jdbc:sqlserver://br.urbicell.com;databaseName=SmsServer";
            String user="sa";
            String pass="asfadas@2014";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con=DriverManager.getConnection(url,user,pass);
            System.out.println("Conectado com Sucesso");
            
            MeuState = con.createStatement();
            String SQL = "select ID, DirectionID, FromAddress, ToAddress, Body from Messages ORDER BY trace DESC";
            rs = MeuState.executeQuery(SQL);
            rs.next();
            for (int x=0; x<=10; x++) {
                rs.next();
                System.out.println(rs.getString("FromAddress"));   
            }
            rs.close();
            conectado = true;
            
            
        }catch(Exception e){
            e.printStackTrace();
            conectado = false;
        }
        
  
        System.out.println(conectado);
    }

   public void desconecte(){
       conectado = false;
   }
    
    
}
