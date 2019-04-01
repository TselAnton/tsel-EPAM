package convector;

import java.util.List;

public interface Convector<T, S> {
    S dtoToUser(T dto);
    T userToDto(S user);

    List<S> dtoListToUserList(List<T> dto);
    List<T> userListToDtoList(List<S> users);
}
