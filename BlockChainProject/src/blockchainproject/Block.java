/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainproject;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
/**
 *
 * @author matthewswitt
 */
public class Block {
    
    private String version;
    private Date Timestamp;
    private String hash;
    private String previousHash;
    private String data;
    
    public Block(String version, Date timestamp, String data) {
    
        this.version = version;
        this.Timestamp = timestamp;
        this.data = data;
        this.hash = computeHash();

    }
    
    // calculates the SHA-256 function on some data
    public String computeHash() {
        
        String dataToHash = "" + this.version + this.Timestamp + this.previousHash + this.data;
        
        MessageDigest digest;
        String encoded = "";
        
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
            encoded = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        this.hash = encoded;
        return encoded;
        

    }
    
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32) {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
    } 

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date Timestamp) {
        this.Timestamp = Timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
