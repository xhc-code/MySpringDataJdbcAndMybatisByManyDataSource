package org.example.utils;

public class ContextSwitchDataSourceUtils {

    private final static ThreadLocal<DataSourceEnum> Local_DATA_SOURCE = new ThreadLocal<>();

    public enum DataSourceEnum{
        DEFAULT(null),ONE("db1"),TWO("db2");
        private String dataSourceKey;
        public String getDataSourceKey(){
            return dataSourceKey;
        }
        public DataSourceEnum getThis(){
            return this;
        }

        DataSourceEnum(String dataSourceKey){
            this.dataSourceKey = dataSourceKey;
        }
        @Override
        public String toString() {
            return "DataSourceKeys{" +
                    "dataSourceKey='" + dataSourceKey + '\'' +
                    '}';
        }
    }

    public static void setDataSource(DataSourceEnum dataSourceKeys){
        if(getCurrentLocalDataSourceKey() != dataSourceKeys){
            Local_DATA_SOURCE.set(dataSourceKeys.getThis());
        }
    }

    public static DataSourceEnum getCurrentLocalDataSourceKey(){
        return Local_DATA_SOURCE.get();
    }

    public static void restoreDefault(){
        if(!isDefault()){
            setDataSource(DataSourceEnum.DEFAULT);
        }
    }

    public static boolean isDefault(){
        return DataSourceEnum.DEFAULT == getCurrentLocalDataSourceKey();
    }

}


