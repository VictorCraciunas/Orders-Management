package Business_Logic;

import Business_Logic.Validators.validators;
import Model.Client;


public class ClientBLL extends AbstractBll<Client> {

    @Override
    public boolean isValidElement(Client client) {
        if (validators.emailValidator(client.getEmail()) && validators.nameValidator(client.firstName) && validators.nameValidator(client.lastName)) {
            return true;
        }
        return false;
    }
}
