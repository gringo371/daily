package duro.imperio.angel.daily.libs.base;

/**
 * Created by Angel on 14/7/2016.
 */
public interface EventBus {
    void register(Object subscriber);
    void unRegister(Object subscriber);
    void post(Object event);
}