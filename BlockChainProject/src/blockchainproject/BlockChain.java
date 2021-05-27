/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainproject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matthewswitt
 */
public class BlockChain {
    
    private List<Block> chain;
    
    public BlockChain() {
    
        chain = new ArrayList<Block>();
        chain.add(generateGenesis());
        
    }
    
    // generates the genesis block
    private Block generateGenesis() {
        Block genesis = new Block("0x200", new java.util.Date(), "<transactions>");
        genesis.setPreviousHash(null);
        genesis.computeHash();
        return genesis;
    }
    
    // adds a block to the chain
    public void addBlock(Block blk) {
        Block newBlock = blk;
        newBlock.setPreviousHash(chain.get(chain.size() - 1).getHash());
        newBlock.computeHash();
        this.chain.add(newBlock);
    }
    
    // displays a block
    public void displayBlock(int idx) {
        
        System.out.println("Block: " + idx);
        System.out.println("Version: " + chain.get(idx).getVersion());
        System.out.println("TimeStamp: " + chain.get(idx).getTimestamp());
        System.out.println("Previous Hash: " + chain.get(idx).getPreviousHash());
        System.out.println("Hash: " + chain.get(idx).getHash());
        
    }
    
    public Block getBlock(int idx) {
        
        var size = chain.size();
        
        
        for(int i = 0; i < size + 1; i++) {
        
            if (size - i == idx) {
                idx = i;
                break;
            }
        }
        
        return this.chain.get(chain.size() - idx);
        
    }
    
    // checks to make sure the hashes are valid
    public void isValid() {
        
        for(int i = 1; i < chain.size(); i++) {
            
            if (!(chain.get(i).getPreviousHash().equals(chain.get(i - 1).getHash()))) {
               System.out.println("Chain is not valid");
               return;
            }
                
            if (!(chain.get(i).getHash().equals(chain.get(i).computeHash()))) {
                System.out.println("Chain is not valid");
                return;
            }
            
            if (!(chain.get(i).getPreviousHash().equals(chain.get(i-1).computeHash()))) {
                System.out.println("Chain is not valid");
                return;
            }
        }
        
        System.out.println("Chain is valid");
    }
}
