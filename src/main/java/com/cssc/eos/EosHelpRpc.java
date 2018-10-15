package com.cssc.eos;

import java.util.Map;

public interface EosHelpRpc {

    String transfer(Map<String,String> keys, String from, String to, String amount, String contract, String memo);

    String buyRam(Map<String,String> keys, String payer, String receiver, String amount);

    String sellRam(Map<String,String> keys, String to, String amount);

    String ramMarket();

    /**
     * 创建资产
     * @param keys 签名私钥
     * @param account 创建账户
     * @param asset 创建资产数量
     * @param eos  对应Eos
     * @param fee  手续费
     * @param transferbool 是否支持转账
     * @return
     */
    String createAsset(Map<String,String> keys, String account, String asset, String eos, String fee, String transferbool);

    /**
     * 去中心交易所购买
     * @param keys 签名私钥
     * @param account 购买账户
     * @param eos  系统币
     * @param asset 发行资产
     * @param feeAmont 手续费账号
     * @return
     */
    String exchangeBuy(Map<String,String> keys, String account, String eos, String asset, String feeAmont);

    /**
     * 去中心交易所卖
     * @param keys 签名私钥
     * @param account 卖出账户
     * @param asset 发行资产
     * @param eos 系统币
     * @param feeAmont 手续费账号
     * @return
     */
    String exchangeSell(Map<String,String> keys, String account, String asset, String eos, String feeAmont);

    /**
     * 查询市场
     * @param pair 交易对名称
     * @return
     */
    String selectExchange(String pair);

    /**
     * 合约code授权
     * @param key
     * @param account
     * @param publicKey
     * @param code
     * @return
     */
    String accountAuthority(Map<String,String> key, String account, String publicKey, String code);
    /**
     * 查询账户
     * @param amount 账户名称
     * @return
     */
    String selectAsset(String amount);

    /**
     * 交易所的币种
     * @param asset
     * @return
     */
    String selectExBuyStat(String asset);

    /**
     * 抵押cpu net
     * @param keys
     * @param form
     * @param receiver
     * @param assetNet
     * @param assetCpu
     * @return
     */
    String delegatebw(Map<String,String> keys ,String form ,String receiver,String assetNet,String assetCpu);
    /**
     * 收回抵押cpu net
     * @param keys
     * @param form
     * @param receiver
     * @param assetNet
     * @param assetCpu
     * @return
     */
    String undelegatebw(Map<String,String> keys ,String form ,String receiver,String assetNet,String assetCpu);
}
