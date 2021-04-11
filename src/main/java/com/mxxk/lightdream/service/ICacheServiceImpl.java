package com.mxxk.lightdream.service;

import com.mxxk.lightdream.entity.dic.SystemDictTableEntity;
import com.mxxk.lightdream.mapper.SystemDictMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ICacheServiceImpl
 *
 * @auther zhang
 * @date 2020/6/6
 **/
@Service
public class ICacheServiceImpl implements ICacheService {

    public static final Logger log= LoggerFactory.getLogger(ICacheServiceImpl.class);

    @Autowired
    private SystemDictMapper systemDictMapper;

//    @Autowired
//    private TaskExecutor taskExecutor;

    @Override
    public void initDictCache() {
        List<SystemDictTableEntity> systemDictList = systemDictMapper.querySystemDictTable();
        log.info("初始化initDictCache");
//        if (!systemDictList.isEmpty()) {
//            for (SystemDictTableEntity systemDictTableEntity : systemDictList) {
//                if (null != systemDictTableEntity) {
//                    if (StringUtils.isNotBlank(systemDictTableEntity.getCode())
//                            && StringUtils.isNotBlank(systemDictTableEntity.getDictKey())) {
//                        RedisUtils.set(,
//                                systemDictTableEntity.getDictValue());
//                    }
//                }
//            }
//        }

    }

   @Override
   public void initExpressionCache() {
        long start = System.currentTimeMillis();
        log.info("Start Loading Expression into redis cache...");
//
//        // 查询表达式全量数据
//        List<ExpressionCacheDto> expressionCacheDtoList = mapper.getExpression();
//
//        // 校验是否有数据需要加载缓存
//        if (CollectionUtils.isEmpty(expressionCacheDtoList)) {
//            log.info("There Is Nothing Need To Cache!");
//            return;
//        }
//
//        // 需要处理的任务总数
//        int pageCount = expressionCacheDtoList.size();
//
//        // 每条线程处理的任务数
//        int pageSize = 300;
//
//        // 计算需要开启多少条线程
//        int threadCount = pageCount % pageSize == ConstantNumber.ZERO ? pageCount / pageSize
//                : pageCount / pageSize + ConstantNumber.ONE;
//
//        // 开启threadCount条线程
//        for (int pageNumber = 1; pageNumber <= threadCount; pageNumber++) {
//            // 计算分页参数
//            final int executeNumber = pageSize * pageNumber;
//            taskExecutor.execute(() -> executeCache(expressionCacheDtoList, pageCount, pageSize, executeNumber));
//        }
//
//        // 计算完成任务消耗时间
//        double cost = (System.currentTimeMillis() - start) / 1000.000;
//        log.info("Started Loading Expression Cache in {}", cost + " seconds");
//
//
    }
//
//    /**
//     * 将字段表达式刷入REDIS缓存
//     *
//     * @param expressionCacheDtoList
//     * @param pageCount
//     * @param pageSize
//     * @param executeNumber
//     */
//    private void executeCache(List<ExpressionCacheDto> expressionCacheDtoList, int pageCount, int pageSize,
//                              int executeNumber) {
//
//        // 校验执行最大数量是否大于总计数
//        executeNumber = executeNumber < pageCount ? executeNumber : pageCount;
//
//        // 声明参数
//        String expression;
//        Integer columnId;
//        Integer expressionTypeId;
//        StringBuilder sb = new StringBuilder();
//
//        // 多线程分页处理
//        // 例如Thread-1处理0(含)~300(不含)，Thread-2处理300~600(不含)
//        for (int j = executeNumber - pageSize; j < executeNumber; j++) {
//            ExpressionCacheDto expressionCacheDto = expressionCacheDtoList.get(j);
//            if (expressionCacheDto != null) {
//                // 清空StringBuilder
//                sb.delete(ConstantNumber.ZERO, sb.length());
//
//                // 获取字段Value
//                expression = expressionCacheDto.getExpression();
//                columnId = expressionCacheDto.getColumnId();
//                expressionTypeId = expressionCacheDto.getExpressionTypeId();
//
//                // 组织RedisKey
//                sb.append(RedisKeyPrefix.REDIS_EXPRESSION_KEY).append(columnId).append(UNDERLINE)
//                        .append(expressionTypeId);
//
//                // 存入缓存
//                RedisUtils.set(sb.toString(), expression);
//            } else {
//                log.info("cache value is null...");
//            }
//        }
//    }

}
