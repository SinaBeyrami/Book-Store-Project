package model;

import java.util.ArrayList;

public class DiscountCode {

    private static ArrayList<DiscountCode> allCodes = new ArrayList<>();
    private int amount;
    private String code;
    private User owner;

    public DiscountCode(int amount , String code){
        this.amount = amount;
        this.code = code;
        allCodes.add(this);
    }

    public static ArrayList<DiscountCode> getAllCodes() {
        return allCodes;
    }

    public void setOwner(Customer owner){
        this.owner = owner;
    }

    public static String showAllCodes(){
        String str = " ";
        int i = 1;
        for (DiscountCode discountCode : allCodes){
            if (allCodes.indexOf(discountCode) == (allCodes.size()-1))
                str = str + i + ") " + discountCode.code + " | amount=" + discountCode.amount + " --> user=" + discountCode.owner.getUsername();
            else{
                str = str + i + ") " + discountCode.code + " | amount=" + discountCode.amount + " --> user=" + discountCode.owner.getUsername() + "\n";
                i++;
            }
        }
        return str.substring(1);
    }

    public static boolean isEmpty(){
        if (allCodes.size() == 0)
            return true;
        return false;
    }

    public static void nothing(){
        return;
    }

    public static void removeCode(DiscountCode code){
        allCodes.remove(code);
    }

    public String getCode() {
        return code;
    }

    public int getAmount() {
        return amount;
    }
}
