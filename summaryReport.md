---
date: 2018-05-06 12:32
status: public
title: summaryReport
---

# 实训总结报告
##阶段一
学习了Linux下的文本编辑器Vi/Vim的使用，了解了Java的基本用法，Java的自动编译工具Ant，单元测试工具Junit，通过写计算器小程序了解了Java GUI的使用。还有如何使用eclipse从现有文件新建工程和导入jar依赖，代码质量分析工具sonar。
##阶段二
了解了GridWorld的架构。
- Part2
学习了Bug类，知道如何实现Bug的移动和转向，和判断如何移动，并知道如何修改这些函数来实现对应的要求，如CircleBug，SpiralBug，ZBug，DancingBug。
- Part3
![](https://raw.githubusercontent.com/CurryYuan/Pictures/master/part3-relationships.jpg)
了解了Location类和Grid接口的实现，还有Actor类，Rock，Flower和Bug类都是继承的Actor类，编程练习是写一个Jumper类，也是继承于Actor类，自己可以自定义Jumper的行为，可以在设计文档中说明。
- Part4
了解了Critter类，这个类也是Actor的子类，不过它可以与其他Actor交互，这是通过它的五个函数来实现的。
```
ArrayList<Actor> getActors();
void processActors(ArrayList<Actor> actors);
ArrayList<Location> getMoveLocations();
Location selectMoveLocation(ArrayList<Location> locs);
void makeMove(Location loc);
```
并给出了子类ChameleonCritter 类和CrabCritter 类作为示例。ChameleonCritter可以根据周围的石头改编自己的颜色，CrabCritter则是只能左右横移并吃掉直接前方的Actor，包括左前和右前。
![](https://raw.githubusercontent.com/wiki/se-2018/se-2018.github.io/images/part4-actor-relationships.jpg)
编程练习是模仿给出的实例，实现几种特殊的Critter，主要方法就是根据要求修改五个函数中需要修改的函数，如获得周围Actor，处理获得的Actor，选择下一步的location。
- Part5 

Grid的数据结构
![](https://raw.githubusercontent.com/wiki/se-2018/se-2018.github.io/images/part5-grid-design.jpg)
首先定义一个接口，规定Grid类必须实现的一些方法，可以供其他类使用，然后就可以写具体的Grid如BoundedGrid和UnboundedGrid，但这些类之间有很多重复的方法，于是就再加一个抽象类AbstractGrid，实现重复的方法。
编程要求是实现我们自己的SparseGrid，主要是如何储存Grid的节点，可以用二维数组，也可以用一维数组加链表的方法，还可以用Map的方法储存实现BoundedGrid，也可以用自动扩容的二维数组来实现UnboundedGrid。最后可以比较这些方法的复杂度来判断它们的优劣。

## 阶段三
 - 图像处理
 
 了解了Bitmap图像的储存方式，以及如何使用Java按字节解析，主要是要了解前54字节代表的含义，以及将后面的像素储存到数组中，像素是以RGB值的形式储存的，每个值对应四个字节，分别是蓝色，绿色，红色，和保留字节（注意颜色顺序是反的），通常不用或用作Alpha通道，在这次实验中没有使用，提取彩色图像的色彩通道的话，就是将红色，绿色，蓝色分别保留并将其他颜色值设为0，灰度图像的话就是按照一个公式计算出灰度值，并将RGB都设为这个值。
 - MazeBug
 
![](https://raw.githubusercontent.com/wiki/se-2018/se-2018.github.io/images/maze-relationships-tree.jpg)

 这是一个让虫子自动走迷宫的程序，我们使用深度优先算法确定虫子走的路线，并加入了一点智能，记录虫子的方向选择情况，向哪个方向走得步数多，以后向那个方向走的概率就变大，如果最后走不通回退的话，那个方向的概率就减小。
 - n-Puzzle
 这是经典的拼图重排游戏，首先是盲目搜索，让我们写一个广度优先算法，这个没有多大问题，然后是启发式搜素，（如A*算法）利用问题拥有的启发信息来引导搜索，动态地确定搜索节点的排序，以达到减少搜索范围，降低问题复杂度的目的。
 
 我们要写的是估价函数，主要是根据放错位的数码个数和曼哈顿距离，来计算。一开始计算曼哈顿距离的时候，我没有考虑空白块不应该被计算，还有计算坐标的时候数组下标要减一，因为第0位存的是空白块的位置，导致Debug了很久。。。