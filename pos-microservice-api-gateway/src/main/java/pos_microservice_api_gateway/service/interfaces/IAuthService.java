package pos_microservice_api_gateway.service.interfaces;

import pos_microservice_api_gateway.model.User;

public interface IAuthService {

    User signIn(User user);


}
