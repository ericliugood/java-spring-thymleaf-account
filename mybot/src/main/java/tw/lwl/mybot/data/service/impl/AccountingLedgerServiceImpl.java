package tw.lwl.mybot.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.lwl.mybot.data.dao.AccountDao;
import tw.lwl.mybot.data.dao.AccountingLedgerDao;
import tw.lwl.mybot.data.dao.LedgerDao;
import tw.lwl.mybot.data.doo.AccountingLedger;
import tw.lwl.mybot.data.service.AccountingLedgerService;

import java.util.List;

@Service
public class AccountingLedgerServiceImpl implements AccountingLedgerService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    LedgerDao ledgerDao;

    @Autowired
    AccountingLedgerDao accountingLedgerDao;

    @Override
    public AccountingLedger addAccountingLedger(AccountingLedger accountingLedger) {
        if(accountingLedger.getLedger().getExpenditure()==true){
            if(accountingLedger.getMoney()>0){
                accountingLedger.setMoney(accountingLedger.getMoney()*(-1));
            }
        }
        else{
            if(accountingLedger.getMoney()<0){
                accountingLedger.setMoney(accountingLedger.getMoney()*(-1));
            }
        }


        return accountingLedgerDao.save(accountingLedger);
    }


    @Override
    public List<AccountingLedger> findAllAccountingLedger() {
        return accountingLedgerDao.findAll();
    }
//
    @Override
    public AccountingLedger modifyAccountingLedger(AccountingLedger accountingLedger) {
        AccountingLedger modifydata;
        modifydata = accountingLedgerDao.findById(accountingLedger.getId()).orElseThrow(RuntimeException::new);
        modifydata.setAccount(accountingLedger.getAccount());
        modifydata.setLedger(accountingLedger.getLedger());
        modifydata.setMoney(accountingLedger.getMoney());
        return accountingLedgerDao.save(modifydata);
    }
//
    @Override
    public void removeAccountingLedgerById(Long id) {

        accountingLedgerDao.deleteById(id);

    }
//
    @Override
    public AccountingLedger findAccountingLedgerById(Long id) {
        return accountingLedgerDao.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public int selectTotals() {
        return accountingLedgerDao.selectTotals();
    }
}
