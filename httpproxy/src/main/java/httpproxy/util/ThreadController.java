package httpproxy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @线程控制类 用于控制线程的运行以及线程数
 * @author robothy
 *
 */
public class ThreadController{
    
	private static final Logger LOGGER  = Logger.getLogger(ThreadController.class.getName());
    
    /**
     * 需要运行的所有线程对象
     */
    private List<Runnable> multiThreadObjects = null;
    
    /**
     * @param multiThreadObjects 需要多线程运行的对象 [objA, objB, objC ...]
     */
    public ThreadController(List<Runnable> multiThreadObjects){
	this.multiThreadObjects=multiThreadObjects;
    }
    
    /**
     * @param multiThreadObj 需要多线程运行的对象
     * @param threadNum 需要运行的数量
     * @说明 当运行的线程为同一个对象的复制品时，调用此构造方法。
     * @注意 被复制的对象需要实现Serializable接口
     */
    public ThreadController(Runnable multiThreadObj,Integer threadNum){	
	this.multiThreadObjects = this.cloneObjs(multiThreadObj, threadNum);
    }
    
    /**
     * @param multiThreadObjects 需要多线程运行的对象的集合，其中key为对象，value为数量
     */
    public ThreadController(Map<Runnable, Integer> multiThreadObjects){
	this.multiThreadObjects = new ArrayList<Runnable>();
	for (Entry<Runnable, Integer> entry : multiThreadObjects.entrySet()) {
	    Runnable object = entry.getKey();
	    Integer num = entry.getValue();
	    List<Runnable> objects = this.cloneObjs(object, num);
	    this.multiThreadObjects.addAll(objects);
	}
    }
    
    /**
     * @功能 复制一组实现Runnable接口的对象
     * @param obj 原件
     * @param num 复制品
     * @return 一组复制的对象
     */
    private List<Runnable> cloneObjs(Runnable obj,int num){
	List<Runnable> objects = new ArrayList<>();
	DeepCloner cloner = new DeepCloner(obj);
	List<Object> repetitions = cloner.BatchClone(num);
	for (Object object : repetitions) {
	    objects.add((Runnable)object);
	}
	return objects;
    }
    
    /**
     * @功能 运行线程
     */
    public void runThreads(){
	
	int threadNum = multiThreadObjects.size();
	/**
	 * 获取线程池并设置其容量
	 */
	if (threadNum<=0) {
		LOGGER.log(Level.SEVERE, "线程池的大小不能小于或等于0");
	}else {
	    
	    ExecutorService execs = Executors.newFixedThreadPool(threadNum);
	    
	    for (Runnable obj : multiThreadObjects) {
		execs.execute(obj);
	    }
	    LOGGER.log(Level.INFO, "线程启动完成");
	    
	    execs.shutdown();
	    try {
		execs.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	LOGGER.info("线程运行结束");
    }
    
    public int getThredNum() {
        return multiThreadObjects.size();
    }
}
