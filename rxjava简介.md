#RxJava
---
###基本流程
一般响应式编程的信息流如下所示：
Observable -> Operator 1 -> Operator 2 -> Operator 3 -> Subscriber
observable是事件的生产者，subscriber是事件最终的消费者。
Operators 即操作符：为了解决对Observable对象的变换的问题。用于在Observable和最终的Subscriber之间修改Observable发出的事件

1. Observable<String> stringObservable = Observable.create(new Observable.OnSub...){...}
2. Subscriber<String> subscriber = new Subscriber<String>() {...}
3. stringObservable.subscribe(subscriber);
通俗解释下上述代码：1为创建事件 2.


#### 响应式特点
Subscriber有三个方法

```java
.subscribe(new Subscriber<String>() {
    @Override
    public void onNext(String s) { System.out.println(s); }
    @Override
    public void onCompleted() { System.out.println("Completed!"); }
	@Override
    public void onError(Throwable e) { System.out.println("Ouch!"); }
    });
```

1.只要有异常发生onError()一定会被调用

这极大的简化了错误处理。只需要在一个地方处理错误即可以。

2.操作符不需要处理异常

将异常处理交给订阅者来做，Observerable的操作符调用链中一旦有一个抛出了异常，就会直接执行onError()方法。

3.你能够知道什么时候订阅者已经接收了全部的数据。





####Operators常用操作符

1. map 就是用来把把一个事件转换为另一个事件的。
2. flatMap 它可以返回任何它想返回的Observable对象。
3. filter(title -> title != null)  过滤某种不满足条件，比如!＝nullfilter()输出和输入相同的元素，并且会过滤掉那些不满足检查条件的。
4. take()输出最多指定数量的结果。 take(5)
5. doOnNext()允许我们在每次输出一个元素之前做一些额外的事情，比如这里的保存标题。.doOnNext(title -> saveTitle(title))
6. .subscribeOn(Schedulers.io()) 指定在Schedulers io中执行
7. .observeOn(AndroidSchedulers.mainThread()) 指定在主线程中执行
8. Observable.subscribe()返回一个Observable  其中纪录了是否与订阅者有关联 。调用unsubscribing方法可以取消关联，并立即停止整个调用链
9. 可自定义 