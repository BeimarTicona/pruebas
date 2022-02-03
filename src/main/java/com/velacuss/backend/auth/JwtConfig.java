package com.velacuss.backend.auth;

public class JwtConfig {
    public static final String LLAVE_SECRETA = "alguna.clave.secreta.123456789";
    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAw5oIAGe0CKZJM/uU2JE3Gm7jAqCkAv/doyJmPw6yZ5Vn+5KI\n" +
            "uIaw9NbVO9PjseRLc03hjt4R13JjnT2YLGDjmRbXdEUBCvx15o6DVI6Y29e86sV+\n" +
            "7wdCHjifUiryJcTi0nd66ICyBO6kETl+PaOIBC7siFx5nd6s1m67rLWzp0y2LMF2\n" +
            "891KZ3oy+B2nwMD9Zach6g2+aIBNZkDjfaxeFQjZIiLTfVYUUT9dZS8UbmbrCEtm\n" +
            "0xZDEHtQRrlASREAd5JTDuM5HAtF0DCTeiwJcCG9tTDCXQ3jkiuXjY6MlxDa6FJv\n" +
            "WRxbHquzHEgOAgVmZm3jRezDmTAnYzWUtDDfSwIDAQABAoIBAQC2BlWD6v3DPtvI\n" +
            "oEq37wtUcfSXsV5v3CQpeA2miMrFUmhd2NOaxN1QO8xbzKeMGGOOfd910HrSAAVr\n" +
            "grJmOhQTlwoRjVWXH7GoSN82hS0DCRUpdXjWf+6rqQ26rXSCaWKbcczTKVCA/oN3\n" +
            "9ATi35foLLK5gzU6EMrPEmVcKAtzl3KEz/GWVNsLfk6Pt6Nzq0A+0YTQ1ui9avIG\n" +
            "IGPQetX/qWXD/iGOnXO0WZr+KoefqMjOI62VrbaQBlhklTt1aSdCfT0SxxFFAmyt\n" +
            "S3fW3ee4kklTBqC4kJ+jtQtQFBwqe3KTgSDsHItaGF443h6XHzdEMn/Umxal1fpR\n" +
            "Aqf4GsHBAoGBAO2YBHdZgLK8GTe5yTjMZIX1WLf1Pyt4K8wWnwy+lG5r/JDV20vJ\n" +
            "cXTGEwHRw+ngD9s+03bJAv3TcUHCr23upGhzWxIlf4dvjxEzsLsm9RgoZisXMocP\n" +
            "ERXCDX9fBl0Ihb5S23LDy0kpkgKf0npOj9ejGEQBd9hERnYL6jjhdlczAoGBANLB\n" +
            "ONDmYJ3HlKcqoC1s4vkeD4Cp20+kcJSmG7o7qmL3AeIfs6+cH0/d3AT5F/adYeOv\n" +
            "BZ1Kpz3LB7ROfNWVJ1knzxbNQ+j2ql8XeFoR4G23+8U76/tWYIDUHFo2fjHQBtnU\n" +
            "mN4R/pULTFPISEeAfk/nLJHsR3iq6NpDT1nDXveJAoGBAMhdfsa3sBULE6kvAEbp\n" +
            "94wQz/Sy2cbq3OSL3y18Oui0IJZ0Ay/yfb+gmr7aC4Hb9wNKBEMUWXV9LvHqIM9w\n" +
            "hS2gupifDuFEbz5ufQgXXnI0/5QvR+kmB551u7RCirdCTKeocHBkNbdJW1zjMzj5\n" +
            "r8yGOhx/sHWtfk5m1euKF6aJAoGAWa+SLwuql9ojv1ObYFlS6XJKZJVj16uBoTBi\n" +
            "4kibxIuayJB3FccZaBJgm9kownDNBGgpunrXWqt4ttkjtHxJh14yeWCWgp/ddqP1\n" +
            "OlcbK3W89Xz/ejCS1p/tBNLlturwz2fyOaZssR5it9CmOY9LsVMOG3byHz6T4rX8\n" +
            "IZXLZwkCgYAGXAbIMjcKeS0MOApDmaxGVpW0KU4mCHgDxzUojJzxpFo3XHy7Hat5\n" +
            "U+cTRJZzXxmSl+fUW/hXr+b3WYwEcLD8nc67TmkIXSnwmg+WSoX/jVQhU65oBD/3\n" +
            "GcqCle/KwNmb4L2rWaxoPOINEefIN24Rih3HIZCVixiko7f+dJ0EZg==\n" +
            "-----END RSA PRIVATE KEY-----";
    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw5oIAGe0CKZJM/uU2JE3\n" +
            "Gm7jAqCkAv/doyJmPw6yZ5Vn+5KIuIaw9NbVO9PjseRLc03hjt4R13JjnT2YLGDj\n" +
            "mRbXdEUBCvx15o6DVI6Y29e86sV+7wdCHjifUiryJcTi0nd66ICyBO6kETl+PaOI\n" +
            "BC7siFx5nd6s1m67rLWzp0y2LMF2891KZ3oy+B2nwMD9Zach6g2+aIBNZkDjfaxe\n" +
            "FQjZIiLTfVYUUT9dZS8UbmbrCEtm0xZDEHtQRrlASREAd5JTDuM5HAtF0DCTeiwJ\n" +
            "cCG9tTDCXQ3jkiuXjY6MlxDa6FJvWRxbHquzHEgOAgVmZm3jRezDmTAnYzWUtDDf\n" +
            "SwIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}
