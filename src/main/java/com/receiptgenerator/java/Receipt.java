package com.receiptgenerator.java;

import java.io.File;
import java.io.IOException;

import com.receiptgenerator.java.model.Item;
import com.receiptgenerator.java.model.Purchase;
import com.receiptgenerator.java.model.ReceiptCalculator;
import com.receiptgenerator.java.model.ReceiptConsole;
import com.receiptgenerator.java.model.ReceiptGenerator;

public class Receipt {
    public static void main(String[] importCarts) throws IOException {
    	
    	//Accessing the default input files from Assets Folder.
        String[] defaultCarts = {
                "src/main/resources/input1.txt",
                "src/main/resources/input2.txt",
                "src/main/resources/input3.txt"
        };
        
        //performing a check whether to go with command line Args or
        //defaultCarts as Input which are part of assets folder using terminary Operator
        String[] queuedCartFiles = (importCarts.length > 0) ? importCarts : defaultCarts;

        for (String cart : queuedCartFiles) {
            if (new File(cart).exists()){
            	ReceiptGenerator rScanner = new ReceiptGenerator(cart);
                Purchase purchase = rScanner.getPurchase();
                ReceiptCalculator calculate = new ReceiptCalculator(purchase.getInventory());
                ReceiptConsole display = new ReceiptConsole();
                for(Item item: purchase.getInventory()){
                    display.purchaseList(item);
                }
                display.lineBreak();
                display.salesTax(calculate.getTaxTotal());
                display.totalSale(calculate.getSaleTotal());
            }
        }
    }
}
