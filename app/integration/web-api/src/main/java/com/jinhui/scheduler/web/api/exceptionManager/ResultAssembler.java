/**
 * 
 */
package com.jinhui.scheduler.web.api.exceptionManager;


import com.jinhui.scheduler.web.api.vo.base.WebConstants;
import com.jinhui.scheduler.web.api.vo.base.WebResult;

public class ResultAssembler {

    /**
     * @param result
     * @param e
     */
    public static void makeErrorResult(WebResult result, Throwable e) {
        result.setResultCode(WebConstants.RESULT_FAIL_CODE);
        result.setMessage(e.getMessage());
    }

}
