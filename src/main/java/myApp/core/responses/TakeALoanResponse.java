package myApp.core.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class TakeALoanResponse extends CoreResponse {

    private boolean isLoanSucceed;

    public TakeALoanResponse(List<CoreError> errors) {
        super(errors);
    }

    public TakeALoanResponse(boolean isLoanSucceed) {
        this.isLoanSucceed = isLoanSucceed;
    }
}