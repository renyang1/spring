package com.ry.a08;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = "prototype")
@Component
public class F2 {
}
