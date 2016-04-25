package ee.degeetia.dumonitor.storage;

import ee.degeetia.dumonitor.common.config.properties.Property;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import org.json.*;

public class Store extends HttpServlet {

  private static final long serialVersionUID = 1L;
  static private Context context; // global vars are here
  
  // acceptable keys: identical to settable database fields
  public static String[] inKeys = {"personcode","action",
      "sender","receiver","restrictions",
      "sendercode","receivercode","actioncode",
      "xroadrequestid","xroadservice","usercode"};
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
  throws ServletException, IOException {    
    handleRequest(req, resp, false);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
  throws ServletException, IOException {
    handleRequest(req, resp, true);    
  }
  
  private void handleRequest(HttpServletRequest req, HttpServletResponse resp, boolean isPost)
  throws ServletException, IOException {
    try {
      context=Util.initRequest(req,resp,"application/json",Store.class);
      if (context==null) return;
      boolean ok=Util.parseInput(req, resp, context, inKeys, isPost); 
      if (!ok || context.inParams==null) return;      
      handleStoreParams();
    } catch (Exception e) {
      Util.showError(context,9,"unexpected error: "+e.getMessage()); 
    }     
    context.os.flush();
    context.os.close(); 
  }    
  
  /*
   *  Store parsed parameters passed as hashmap  
   */
  
  public static void handleStoreParams() 
  throws ServletException, IOException { 
    Connection conn = Util.createDbConnection(context);
    if (conn==null) return;       
    
    // insert data
    
    try {                  
      String sql = "insert into ajlog"
      + "(personcode,action,"
      + "sender,receiver,restrictions,sendercode,receivercode,"
      + "actioncode,xroadrequestid,xroadservice,usercode)"
      + " values "
      + "(?,?, ?,?,?,?,?, ?,?,?,?)";
      PreparedStatement preparedStatement = conn.prepareStatement(sql);
      for (int i=0; i<inKeys.length; i++) {
        if (context.inParams.get(inKeys[i])!=null) {
          preparedStatement.setString(i+1, context.inParams.get(inKeys[i]));          
        } else {
          preparedStatement.setNull(i+1, java.sql.Types.VARCHAR);
        }
      }  

      // execute insert SQL stetement
      int n = preparedStatement.executeUpdate();      
      if (n!=1) {
        Util.showError(context, 11, "record not stored");
      } else {
        Util.showOK(context);
      }
          
    } catch(Exception e) {
      Util.showError(context, 10, "database storage error: "+e.getMessage());
      return;    
    } finally {
        //It's important to close the connection when you are done with it
        try { conn.close(); } catch (Throwable ignore) { 
          /* Propagate the original exception
          instead of this one that you may want just logged */ }
    }
  }  
  
}