package data_structure.line.list;

public class ArrayList<E> implements List<E> {
    private Object[] array;
    private int capacity;
    private int size;

    private static final int DEFAULT_CAPACITY = 100;
    private static final int DEFAULT_INC_SIZE = 20;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.array = new Object[this.capacity];
    }

    @Override
    public void insert(int index, E e) {
        if (index < 0 && index > this.size) {
            return;
        }
        if (this.size == this.capacity) {
            this.resize();
        }
        System.arraycopy(this.array,index,this.array,index+1,this.size-index);
        this.array[index] = e;
        this.size++;
    }

    @Override
    public void insertLast(E e) {
        this.insert(this.size, e);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 && index >= this.size) {
            return null;
        }
        E elem= (E) this.array[index];
        if (index==0){
            this.array[0]=null;
        }else {
            System.arraycopy(this.array,index,this.array,index-1,this.size-index-1);
        }
        this.size--;
        return elem;
    }

    @Override
    public E removeLast() {
        return remove(this.size-1);
    }

    @Override
    public void update(int index, E e) {
        if (index >= 0 && index < this.size) {
            this.array[index] = e;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= 0 && index < this.size) {
            return (E) this.array[index];
        }
        return null;
    }

    @Override
    public E getLast() {
        return get(this.size-1);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < this.size; ++i) {
            if (e.equals(this.array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    public void resize() {
        Object[] new_array = new Object[this.capacity + DEFAULT_INC_SIZE];
        System.arraycopy(this.array, 0, new_array, 0, this.size);
        this.array = new_array;
        this.capacity += DEFAULT_CAPACITY;
    }
}
