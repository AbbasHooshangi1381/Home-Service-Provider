package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import domain.other.Wallet;
import repository.WalletRepository;
import service.WalletService;

public class WalletServiceImpl extends BaseEntityServiceImpl<Integer, Wallet, WalletRepository>
        implements WalletService {
    public WalletServiceImpl(WalletRepository baseRepository) {
        super(baseRepository);
    }
}
