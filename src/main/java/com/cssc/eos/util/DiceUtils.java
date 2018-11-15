package com.cssc.eos.util;

import com.cssc.eos.crypto.util.EncryptUtils;

import java.util.Date;
import java.util.Random;

public class DiceUtils {
    public static long  computeRandom(String seedHash,String userSeedHash){
        String data=seedHash+userSeedHash.substring(0,40);
        return hashCombines(data.toCharArray())%100+1;
    }
    public static long hashCombines(char[] data){
        long count=0;
        for (char by:
                data) {
            count^=(long)by+(count<<8);
        }
        return  count;
    }
    public static void main(String[] args) {
        Date date = new Date();
        long timeMill = date.getTime();
        Random rand = new Random(timeMill);
        int count=0;
        for (int i = 0; i <100 ; i++) {
            String seed_hash= EncryptUtils.SHA256( String.valueOf(rand.nextInt(10000))) ;
            System.out.println(seed_hash);
//            String seed_hash="5ec68887c118ee1b9f6008b1441df4ffe0a31cdbbe17ffe46e06f4920624233d";
//            String user_seed_hash="d206553aab2da3c3b5aa8fa035d33062b7796caea934426aba029005333515e1";
            String user_seed_hash= EncryptUtils.SHA256( String.valueOf(rand.nextInt(10000))) ;
            System.out.println(user_seed_hash);
//        String user_seed_hash="5e07408562bedb8b60ce05c1decfe3ad16b72230";
            long s=computeRandom(seed_hash,user_seed_hash);
            if(s>50){
                count++;
            }
            System.out.println("args = [" +  s+ "]");
        }
        System.out.println("count :"+count);
    }
}
