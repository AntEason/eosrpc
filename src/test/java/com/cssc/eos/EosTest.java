package com.cssc.eos;

import com.cssc.eos.api.EosApiRestClientImpl;
import com.cssc.eos.api.EosHelpRpcImpl;
import com.cssc.eos.crypto.ec.EosPrivateKey;
import com.cssc.eos.crypto.util.EncryptUtils;
import com.cssc.eos.entity.account.Account;
import com.cssc.eos.util.Utils;
import org.junit.Test;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class EosTest
{
  private static Map<String,String> keys=new HashMap<>();
  private static Eos eos=new EosApiRestClientImpl("http://192.168.0.125:8891");
  private static String accountName="yichenaaaa";
  private static EosHelpRpc eosHelpRpc=new EosHelpRpcImpl(eos);
  private static String active_prKey="5Hpv1qmYsgCBeKqhPw2vSaDd4hbmQ2xzv4CV3pzsB5zT5gGo8ZZ";
  private static String active_pubKey="EOS7ibFZXpKFbeFixZwW5dKTMiyYZhkeRVKVfw3zqyMJpzh2vp98A";
  private static String owner_prKey="5KUFP3U25xvEjpp7o9mkr6ZfbUTES9patkE18ii9oiJoDPnabct";
  private static String owner_pubKey="EOS6dz8fYqkmnVbYfvcDYb8pMhD6n4Zh79tvnidfRk3cjZ2x3jQrL";
  static {
    keys.put(owner_pubKey,owner_prKey);
    keys.put(active_pubKey,active_prKey);
  }
  @Test
  public void transfer(){
     String transfer= eosHelpRpc.transfer(keys,accountName,"eosio","1.0000 SYS","eosio.token","test");
      System.out.println("transfer :"+transfer);
  }

  @Test
  public void buyram(){
    String buyRam  =  eosHelpRpc.buyRam(keys,accountName,accountName,"1000.0000 SYS");
    System.out.println("buyRam :"+buyRam);
  }

  @Test
  public void sellram(){
   String sellRam =   eosHelpRpc.sellRam(keys,accountName,"100");
   System.out.println("sellRam :"+sellRam);
  }

  @Test
  public void getChaiInfo(){
      System.out.println("chainInfo :"+ Utils.toJson(eos.getChainInfo()));
  }

  @Test
    public void getramMarket(){
      System.out.println("ramMarket :"+eosHelpRpc.ramMarket());
  }

  @Test
    public void selectAsset(){
      System.out.println("selectAsset :"+eosHelpRpc.selectAsset(accountName));
  }
  @Test
    public void selectExchange(){
      System.out.println("selectExchange :"+eosHelpRpc.selectExchange("NBASYS"));
  }
  @Test
    public void selectExBuyStat(){
      System.out.println("selectExchange :"+eosHelpRpc.selectExBuyStat("NBA"));
  }
  @Test
  public void createAsset(){
     System.out.println("createAsset :"+ eosHelpRpc.createAsset(keys,accountName,"100000000000.0000 NBA","100000000000.0000 SYS","200","1"));
  }
  @Test
  public void exchangeBuy(){
    System.out.println("exchangeBuy :"+eosHelpRpc.exchangeBuy(keys,accountName,"2000.0000 SYS","0.0000 NBA","bancor.fee"));
  }
  @Test
  public void exchangeSell(){
    System.out.println("exchangeBuy :"+eosHelpRpc.exchangeSell(keys,accountName,"1000.0000 NBA","0.0000 SYS","bancor.fee"));
  }
  @Test
  public void accountAuthority(){System.out.println("accountAuthority :"+eosHelpRpc.accountAuthority(keys,accountName,active_pubKey,"bancor.token"));
  }
  @Test
  public void delegatebw(){
    System.out.println("delegatebw :"+eosHelpRpc.delegatebw(keys,accountName,"dawang2","1000.0000 SYS","1000.0000 SYS",false));
  }
  @Test
  public void undelegatebw(){
    System.out.println("undelegatebw :"+eosHelpRpc.undelegatebw(keys,accountName,"dawang2","1000.0000 SYS","1000.0000 SYS"));
  }
  @Test
  public void getaccount(){
    Map account=eos.getAccount(accountName);
    System.out.println("getaccount :"+ Utils.toJson(account));
  }
  @Test
  public void receipt(){
//    System.out.println("accountAuthority :"+eosHelpRpc.accountAuthority(keys,accountName,owner_pubKey,"dicegame"));
    int ramdom=(int)(1+Math.random()*(10-1+1));
    String hash=  EncryptUtils.SHA256(String.valueOf(ramdom));
    String systemHash=  EncryptUtils.SHA256(String.valueOf(ramdom+1));
    System.out.println("receipt :"+eosHelpRpc.receipt(keys,4,accountName,"bancor.fee",
            "10.0000 SYS",50,hash,
            systemHash,0));
  }
  @Test
  public void reveal(){
    System.out.println("reveal :"+
            eosHelpRpc.reveal(keys,4,
                    accountName,"7902699be42c8a8e46fbbb4501726517e86b22c56a189f7625a6da49081b2451"));
  }
  @Test
  public void bets(){
    System.out.println("bets :"+
            eosHelpRpc.bets());
  }
  @Test
  public void results(){
    System.out.println("results :"+
            eosHelpRpc.results());
  }
}
