/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.urbicell.db;

/*versão br.com.urbicell*/


import java.sql.*;


/**
 *
 * @author paulinorochaesilva
 */
public class Conexao {
    
    public static boolean conectado; //para o caso de querer usar o valor conectado em outra classe
    public static boolean ConexaoLocal; //para alternar entre BD local e externo
    public static boolean Proxy;
  
    private ClassLoader classLoader;  //implementacao do classloader para o WebStart
    Connection conn = null;
    private String userName=null;
    private String password=null;
    private String url=null;    
    
    private Driver driver;
    
    private String SQL=null;
    
    //Talvez as variáveis abaixo sejam utilizadas no webstart
    
    String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    Class driverClass;
    
    //
    
    public void conecte(){
        
        ResultSet rs;
        Statement MeuState;
        
        try{
            url="jdbc:sqlserver://br.urbicell.com;databaseName=SmsServer";
            userName="sa";
            password="asfadas@2014";
            Class.forName(driverClassName);
            conn=DriverManager.getConnection(url,userName,password);
            System.out.println("Conectado com Sucesso");
            
            MeuState = conn.createStatement();
            SQL = "select ID, DirectionID, FromAddress, ToAddress, Body from Messages ORDER BY trace DESC";
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
       if (conn != null){
           try {
               conn.close();
               System.out.println("conexao fechada");
               conectado=false;
           } catch (SQLException ex) {
               ex.printStackTrace();
               System.err.println("Deu pau: "+ex);
           }
       } else {
           System.out.println("A conexao já estava fechada.");
       }
       this.conectado=false;
   }
    
    
}
