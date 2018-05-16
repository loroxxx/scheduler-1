package com.jinhui.scheduler.domain.common;

/**
 * Created by jinhui on 2017/6/13.
 */
public interface IInstitution {
    enum Type{
        Channel,TA, Pay
    }
    IInstitution.Type type();
    InstitutionType institutionType();
    InstitutionCode institutionCode();
}
