package com.xtremeglory.data_structure;

import java.io.*;

public interface Cloneable<E> {
    @SuppressWarnings("unchecked")
    default  E clone(E obj) {
        if (!(obj instanceof Serializable)) {
            System.err.println("不支持的深拷贝操作");
        }
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bout);
            oos.writeObject(obj);

            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bin);
            return (E) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }
}