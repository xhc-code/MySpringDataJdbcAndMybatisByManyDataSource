package org.example.annon;

import org.example.utils.ContextSwitchDataSourceUtils;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {

    ContextSwitchDataSourceUtils.DataSourceEnum value();

}
