package com.danarossa;

import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Entry {

    static Set<String> actions = Set.of("LOGIN", "LOGOFF", "REGISTER", "LOGIN_TRY");


    private final String action;
    private final BigInteger userId;
    private final String ip;
    private final Date timeStamp;


    public Entry() {
        this.action = getRandomAction();
        this.userId = getRandomBigInteger();
        this.ip = getRandomIpAddress();
        this.timeStamp = new Date();
    }


    public static Set<String> getActions() {
        return actions;
    }

    public String getAction() {
        return action;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public String getIp() {
        return ip;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "action='" + action + '\'' +
                ", userId=" + userId +
                ", ip='" + ip + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }

    private static String getRandomAction() {
        int index = new Random().nextInt(actions.size());
        Iterator<String> iter = actions.iterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }
        return iter.next();
    }


    public static BigInteger getRandomBigInteger() {
        Random rand = new Random();
        BigInteger n = BigInteger.valueOf(10000000000000L);
        return new BigInteger(n.bitLength(), rand);
    }

    public static String getRandomIpAddress() {
        Random r = new Random();
        return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
    }





}
