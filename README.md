# mybatis-splitTable
mybatis工具＋分表
#使用
继承 AbstractMapperService

 # 如果要分表重写 getSplitType 指定分表策略，不分表返回null
    protected SplitType getSplitType() {
        return SplitType.ID_RANG;
    }
#自定义分表策略 配置SplitConfig
设置表名对应的分表策略和分表参数