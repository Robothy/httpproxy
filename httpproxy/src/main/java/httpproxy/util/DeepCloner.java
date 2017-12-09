package httpproxy.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @深度复制工具类 深度复制对象。
 * @注意 被复制的对象必须实现Serializable接口
 * @author robothy
 *
 */
public class DeepCloner {
    
    private Object originObject = null;
    
    public DeepCloner(Object originObject){
	this.originObject = originObject; 
    }
    
    /**
     * @功能 深度复制一个对象
     */
    public Object clone() {
        Object obj = null;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
	    ObjectOutputStream oos = new ObjectOutputStream(baos);
	    
	    oos.writeObject(originObject);
	    oos.flush();
	    oos.close();

	    InputStream in = new ByteArrayInputStream(baos.toByteArray());
	    ObjectInputStream ois = new ObjectInputStream(in);
	    obj = ois.readObject();
	} catch (IOException e) {
	    e.printStackTrace();
	}catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
        return obj;
    }
    
    /**
     * @功能 深度复制一批对象
     * @param num 需要复制的对象的数量
     * @return 返回复制得到的对象
     */
    public ArrayList<Object> BatchClone(int num){
	ArrayList<Object> objects = new ArrayList<>();
	for(int i=0; i++<num;)
	    objects.add(clone());
	return objects;
    }
    
    
}