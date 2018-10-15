package com.cssc.eos;

public interface EosHelpRpc {

    String transfer(String privacyKey, String from, String to, String amount, String contract, String memo);

    String buyRam(String privacyKey, String payer, String receiver, String amount);

    String sellRam(String privacyKey, String to, String amount);

    String ramMarket();

    /**
     * 创建资产
     * @param privacyKey 签名私钥
     * @param account 创建账户
     * @param asset 创建资产数量
     * @param eos  对应Eos
     * @param fee  手续费
     * @param transferbool 是否支持转账
     * @return
     */
    String createAsset(String privacyKey, String account, String asset, String eos, String fee, String transferbool);

    /**
     * 去中心交易所购买
     * @param privacyKey 签名私钥
     * @param account 购买账户
     * @param eos  系统币
     * @param asset 发行资产
     * @param feeAmont 手续费账号
     * @return
     */
    String exchangeBuy(String privacyKey, String account, String eos, String asset, String feeAmont);

    /**
     * 去中心交易所卖
     * @param privacyKey 签名私钥
     * @param account 卖出账户
     * @param asset 发行资产
     * @param eos 系统币
     * @param feeAmont 手续费账号
     * @return
     */
    String exchangeSell(String privacyKey, String account, String asset, String eos, String feeAmont);

    /**
     * 查询市场
     * @param pair 交易对名称
     * @return
     */
    String selectExchange(String pair);

    /**
     * 合约code授权
     * @param privacyKey
     * @param account
     * @param publicKey
     * @param code
     * @return
     */
    String accountAuthority(String privacyKey, String account, String publicKey, String code);
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


}
