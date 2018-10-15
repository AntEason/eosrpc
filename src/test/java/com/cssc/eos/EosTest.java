package com.cssc.eos;

import com.cssc.eos.api.EosApiRestClientImpl;
import com.cssc.eos.api.EosHelpRpcImpl;
import com.cssc.eos.util.Utils;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class EosTest
{
  private static Eos eos=new EosApiRestClientImpl("http://192.168.0.125:8891");
  private static String accountName="yichenaaaa";
  private static String owner_prKey="5Hpv1qmYsgCBeKqhPw2vSaDd4hbmQ2xzv4CV3pzsB5zT5gGo8ZZ";
  private static String owner_pubKey="EOS7ibFZXpKFbeFixZwW5dKTMiyYZhkeRVKVfw3zqyMJpzh2vp98A";
  private static String active_prKey="5KUFP3U25xvEjpp7o9mkr6ZfbUTES9patkE18ii9oiJoDPnabct";
  private static String active_pubKey="EOS6dz8fYqkmnVbYfvcDYb8pMhD6n4Zh79tvnidfRk3cjZ2x3jQrL";
  private static EosHelpRpc eosHelpRpc=new EosHelpRpcImpl(eos);

  @Test
  public void transfer(){
     String transfer= eosHelpRpc.transfer(active_prKey,accountName,"eosio","1.0000 SYS","eosio.token","test");
      System.out.println("transfer :"+transfer);
  }

  @Test
  public void buyram(){
    String buyRam  =  eosHelpRpc.buyRam(active_prKey,accountName,accountName,"200.0000 SYS");
    System.out.println("buyRam :"+buyRam);
  }

  @Test
  public void sellram(){
   String sellRam =   eosHelpRpc.sellRam(active_prKey,accountName,"100");
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
     System.out.println("createAsset :"+ eosHelpRpc.createAsset(active_prKey,accountName,"100000000000.0000 NBA","100000000000.0000 SYS","200","1"));
  }
  @Test
  public void exchangeBuy(){
    System.out.println("exchangeBuy :"+eosHelpRpc.exchangeBuy(active_prKey,accountName,"2000.0000 SYS","0.0000 NBA","bancor.fee"));
  }
  @Test
  public void exchangeSell(){
    System.out.println("exchangeBuy :"+eosHelpRpc.exchangeSell(active_prKey,accountName,"1000.0000 NBA","0.0000 SYS","bancor.fee"));
  }
  @Test
  public void accountAuthority(){
      eosHelpRpc.accountAuthority(owner_prKey,accountName,active_pubKey,"bancor.token");
  }
}
