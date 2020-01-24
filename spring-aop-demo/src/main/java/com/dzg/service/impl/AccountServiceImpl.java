package com.dzg.service.impl;

import com.dzg.service.IAccountService;

public class AccountServiceImpl implements IAccountService {
    @Override
    public void saveAccount() {
        System.out.println("保存账户信息");
//        int i = 1/0;
    }

    @Override
    public void updateAccount() {
        System.out.println("修改账户信息");
    }

    @Override
    public void deleteAccount() {
        System.out.println("删除账户信息");
    }
}
