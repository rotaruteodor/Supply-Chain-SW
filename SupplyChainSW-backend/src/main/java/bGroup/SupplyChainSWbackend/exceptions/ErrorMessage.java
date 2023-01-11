package bGroup.SupplyChainSWbackend.exceptions;

public class ErrorMessage {

    public static String getDoesNotExistErrorMessage(String entityName, Long id){
        return new StringBuilder().append(entityName)
                .append(" with id: ")
                .append(id.toString())
                .append(" doesn't exist")
                .toString();
    }

}
