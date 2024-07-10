package application;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Component
public class App {

    @Autowired
    private BankClientsApp bankClientsApp;

    @Autowired
    private TransferByPhoneApp transferByPhoneApp;

    @Autowired
    private DataBase database;

    public void run(int sourceUserId, int destinationUserId, int amount) {
        if (!bankClientsApp.isUserExist(sourceUserId)) {
            return;
        }

        if (!bankClientsApp.isUserExist(destinationUserId)) {
            return;
        }

        transferByPhoneApp.transfer(sourceUserId, destinationUserId, amount);
        database.transferMoney(sourceUserId, destinationUserId, amount);
    }

}
