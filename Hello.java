List和Set比较，各自的子类比较
对比一：Arraylist与LinkedList的比较
1、ArrayList是实现了基于动态数组的数据结构,因为地址连续，一旦数据存储好了，查询操作效率会比较高（在内存里是连着放的）。
2、因为地址连续， ArrayList要移动数据,所以插入和删除操作效率比较低。   
3、LinkedList基于链表的数据结构,地址是任意的，所以在开辟内存空间的时候不需要等一个连续的地址，对于新增和删除操作add和remove，LinedList比较占优势。
4、因为LinkedList要移动指针,所以查询操作性能比较低。

适用场景分析：
当需要对数据进行对此访问的情况下选用ArrayList，当需要对数据进行多次增加删除修改时采用LinkedList。

对比二：ArrayList与Vector的比较
1、Vector的方法都是同步的，是线程安全的，而ArrayList的方法不是，由于线程的同步必然要影响性能。因此，ArrayList的性能比Vector好。 
2、当Vector或ArrayList中的元素超过它的初始大小时，Vector会将它的容量翻倍，而ArrayList只增加50%的大小，这样。ArrayList就有利于节约内存空间。
3、大多数情况不使用Vector，因为性能不好，但是它支持线程的同步，即某一时刻只有一个线程能够写Vector，避免多线程同时写而引起的不一致性。
4、Vector可以设置增长因子，而ArrayList不可以。

适用场景分析：
1、Vector是线程同步的，所以它也是线程安全的，而ArrayList是线程异步的，是不安全的。如果不考虑到线程的安全因素，一般用ArrayList效率比较高。
2、如果集合中的元素的数目大于目前集合数组的长度时，在集合中使用数据量比较大的数据，用Vector有一定的优势。

对比三：HashSet与TreeSet的比较
1.TreeSet 是二叉树实现的，Treeset中的数据是自动排好序的，不允许放入null值 。
2.HashSet 是哈希表实现的，HashSet中的数据是无序的，可以放入null，但只能放入一个null，两者中的值都不能重复，就如数据库中唯一约束 。
3.HashSet要求放入的对象必须实现HashCode()方法，放入的对象，是以hashcode码作为标识的，而具有相同内容的String对象，hashcode是一样，所以放入的内容不能重复。但是同一个类的对象可以放入不同的实例。

适用场景分析：
HashSet是基于Hash算法实现的，其性能通常都优于TreeSet。我们通常都应该使用HashSet，在我们需要排序的功能时，我们才使用TreeSet。

大体回答如上，类似文章请移驾：
List,Set和Map详解及其区别和他们分别适用的场景



HashMap和ConcurrentHashMap的区别
1、HashMap不是线程安全的，而ConcurrentHashMap是线程安全的。
2、ConcurrentHashMap采用锁分段技术，将整个Hash桶进行了分段segment，也就是将这个大的数组分成了几个小的片段segment，而且每个小的片段segment上面都有锁存在，那么在插入元素的时候就需要先找到应该插入到哪一个片段segment，然后再在这个片段上面进行插入，而且这里还需要获取segment锁。
3、ConcurrentHashMap让锁的粒度更精细一些，并发性能更好。

大体回答如上，类似文章请移驾：
HashMap详解

至于两者的底层实现，你如果想通过一篇文章就理解了，那就too young了，好好找些博文+看源码去吧。



HashTable和ConcurrentHashMap的区别
它们都可以用于多线程的环境，但是当Hashtable的大小增加到一定的时候，性能会急剧下降，因为迭代时需要被锁定很长的时间。因为ConcurrentHashMap引入了分割(segmentation)，不论它变得多么大，仅仅需要锁定map的某个部分，而其它的线程不需要等到迭代完成才能访问map。简而言之，在迭代的过程中，ConcurrentHashMap仅仅锁定map的某个部分，而Hashtable则会锁定整个map。

大体回答如上，类似文章请移驾：
HashMap和HashTable到底哪不同？



String,StringBuffer和StringBuilder的区别
1、运行速度，或者说是执行速度，在这方面运行速度快慢为：StringBuilder > StringBuffer > String。
2、线程安全上，StringBuilder是线程不安全的，而StringBuffer是线程安全的。

适用场景分析：
String：适用于少量的字符串操作的情况
StringBuilder：适用于单线程下在字符缓冲区进行大量操作的情况
StringBuffer：适用多线程下在字符缓冲区进行大量操作的情况

大体回答如上，类似文章请移驾：
String、StringBuffer与StringBuilder介绍



wait和sleep的区别
1、sleep()方法是属于Thread类中的，而wait()方法，则是属于Object类中的。
2、sleep()方法导致了程序暂停执行指定的时间，让出cpu给其他线程，但是他的监控状态依然保持着，当指定的时间到了又会自动恢复运行状态。所以在调用sleep()方法的过程中，线程不会释放对象锁。
3、调用wait()方法的时候，线程会放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象调用notify()方法后本线程才进入对象锁定池准备获取对象锁进入运行状态。
