package com.blueStarWei.interceptor;

import com.blueStarWei.utils.MyBatisLogUtil;
import com.blueStarWei.utils.StringUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.util.List;

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
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})
})
public class SqlInterceptor implements Interceptor {

    private static final Log LOG = MyBatisLogUtil.getLog(SqlInterceptor.class);

    public Object intercept(Invocation invocation) throws Throwable {
        Object result;

        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement  = (MappedStatement) args[0];
        String methodName = mappedStatement.getId();
        String sql = showSql(mappedStatement.getConfiguration(), mappedStatement.getBoundSql(args[1]));

        CacheManager cacheManager = CacheManager.getInstance();
        //get cache from ehcache.xml.
        Cache cache = cacheManager.getCache("sql");
        int cacheKey = (methodName + sql).hashCode();
        Element element = cache.get(cacheKey);
        if(element != null) {
            result = element.getObjectValue();
            LOG.debug("From cache --    sql :"+ StringUtil.removeExtraWhitespaces(sql));
            LOG.debug("From cache -- result :"+result);
        }else {
            result = invocation.proceed();
            cache.put(new Element(cacheKey,result));
        }

        return result;
    }

    private String showSql(Configuration config, BoundSql boundSql) {
        String sql = boundSql.getSql();

        Object paramObj = boundSql.getParameterObject();
        List<ParameterMapping> paramMappings = boundSql.getParameterMappings();
        if (paramObj != null && paramMappings.size() > 0) {
            TypeHandlerRegistry typeHandlerRegistry = config.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(paramObj.getClass())) {
                sql = sql.replaceFirst("\\?", paramObj.toString());
            } else {
                MetaObject metaObject = config.newMetaObject(paramObj);
                for (ParameterMapping parameterMapping : paramMappings) {
                    String key = parameterMapping.getProperty();
                    if (metaObject.hasGetter(key)) {
                        Object value = metaObject.getValue(key);
                        sql = sql.replaceFirst("\\?", value.toString());
                    } else if (boundSql.hasAdditionalParameter(key)) {
                        Object value = boundSql.getAdditionalParameter(key);
                        sql = sql.replaceFirst("\\?", value.toString());
                    }
                }
            }
        }
        return sql;
    }
}
