package com.tshaped.ecommerce.inventory.exceptions;

public class DuplicateInventoryException
        extends RuntimeException{
    public DuplicateInventoryException(String message){
        super(message);
    }
}