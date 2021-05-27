/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainproject;

import static blockchainproject.Block.getSHA;
import static blockchainproject.Block.toHexString;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.lang.Math;
import java.text.DecimalFormat;

/**
 *
 * @author matthewswitt
 */
public class BlockChainProject {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BlockChain tcpCoin = new BlockChain();
        
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        
        double userAmount = 100.00;
        
        System.out.println("You start with " + df.format(userAmount) + " LD");
        System.out.println("Rules: You cannot receive or give away more LD than you currently have");
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Number of blocks in chain: ");
        int sizeOfChain = scan.nextInt();
        
        System.out.print("Enter the block number's hash you want to alter (-int to not alter): ");
        int alteredHash = scan.nextInt();
        
        int arr[] = new int[sizeOfChain];
        int idx_counter = 0;
        int loop_counter = 0;
        
        while (true) {
            System.out.print("What block would you like to perform transaction(s) on (-int to quit): ");
            int num = scan.nextInt();
            
            if ((num < 0) || (loop_counter > sizeOfChain)) {
                break;
            } else if (num >= sizeOfChain) {
                System.out.println("Invalid block");
            } else {
                arr[idx_counter] = num;
                idx_counter++;
                loop_counter++;
            }
             
            
        }
        
        System.out.println();
        
        
        for(int i = 0; i < sizeOfChain; i++) {
            
            String transactionHash = "";
                    
            for(int j = 0; j < loop_counter; j++) {
                
                if (arr[j] == i) {
                    
                    double transaction = 0;
                    
                    while (true) {
                        System.out.print("Enter transaction (negative value to give LD, positive to receive LD): ");
                        transaction = scan.nextDouble();
                        
                        if (Math.abs(transaction) > userAmount) {
                            System.out.print("You do not have enough money to complete this transaction!\n");
                        } else {
                            break;
                        }
                    }
                    
                    
                    String t = String.valueOf(transaction);
                    
                    try {
                        transactionHash = toHexString(getSHA(t));
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    
                    if (transaction < 0) {
                        userAmount -= transaction * -1;
                    } else {
                        userAmount += transaction;
                    }
                    
                    System.out.println();
                    break;
                }
            }
            
            Block blk = new Block("0x200", new java.util.Date(), "<transactions>");
            tcpCoin.addBlock(blk);
            
            if (alteredHash == i) {
                tcpCoin.getBlock(alteredHash).setHash("invalid");
            }
            
            tcpCoin.displayBlock(i);
            
            if (transactionHash != "") {
                System.out.println("Transaction Hash: " + transactionHash);
            }
            
            System.out.println();
            System.out.println("You currently have " + df.format(userAmount) + " LD");
            System.out.println();
            
            
            
        }
        
        System.out.print("After transactions, you have " + df.format(userAmount) + " LD\n");
        tcpCoin.isValid();
        
    }
    
}
