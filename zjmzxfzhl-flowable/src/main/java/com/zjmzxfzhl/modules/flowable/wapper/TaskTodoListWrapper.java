package com.zjmzxfzhl.modules.flowable.wapper;

import com.zjmzxfzhl.modules.flowable.common.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 庄金明
 * @date 2020年3月22日
 */
@Component
public class TaskTodoListWrapper implements IListWrapper {

    @Autowired
    private ResponseFactory responseFactory;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List execute(List list) {
        return responseFactory.createTaskResponseList(list);
    }
}
