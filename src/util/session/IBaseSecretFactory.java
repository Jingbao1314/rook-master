package util.session;

public interface IBaseSecretFactory {

    String createSecret(int userId) throws Exception;

    int checkSecret(String secret) throws Exception;

}
