//@@author cxingkai
package seedu.address.logic.login;

/**
 * Stores login state
 */
public class LoginState {

    private int state;

    LoginState(int state) {
        this.state = state;
    }

    public void updateState(int newState) {
        this.state = newState;
    }

    public int getState() {
        return state;
    }
}
