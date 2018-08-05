

package com.huan.HTed.core.impl;

import javax.servlet.http.HttpServletRequest;

import com.huan.HTed.core.IRequest;
import com.huan.HTed.core.IRequestListener;


public class DefaultRequestListener implements IRequestListener {
    
    @Override
    public IRequest newInstance() {
        return new ServiceRequest();
    }

    @Override
    public void afterInitialize(HttpServletRequest httpServletRequest, IRequest request) {

    }
}
