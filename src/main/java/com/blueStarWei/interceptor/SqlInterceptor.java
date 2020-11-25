package com.blueStarWei.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Statement;

/**
 * MyBatis 允许使用插件来拦截的方法调用包括：
 *     Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 *     ParameterHandler (getParameterObject, setParameters)
 *     ResultSetHandler (handleResultSets, handleOutputParameters)
 *     StatementHandler (prepare, parameterize, batch, update, query)
 *
 *  拦截器顺序：
 *       Executor -> ParameterHandler -> StatementHandler -> ResultSetHandler
 *
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class,ResultHandler.class})
})
public class SqlInterceptor implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("------------------------bengin query-----------");
        Object target = invocation.getTarget();
        if(target instanceof StatementHandler) {
            StatementHandler handler = (StatementHandler) target;
            String sql = handler.getBoundSql().getSql();
            Object param = handler.getParameterHandler().getParameterObject();

            System.out.println("sql : "+sql.replace("?",param+""));
        }else if(target instanceof  Executor){
            Object[] args = invocation.getArgs();
            MappedStatement mappedStatement  = (MappedStatement) args[0];
            String methodName = mappedStatement.getId();
            System.out.println("method : "+methodName);

            BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
            String sql = boundSql.getSql();
            Object param = boundSql.getParameterObject();
            System.out.println("sql : "+sql.replace("?",param+""));
        }
        Object result = invocation.proceed();
        System.out.println("result : "+result);
        System.out.println("------------------------bengin end-----------");
        return result;
    }
}
