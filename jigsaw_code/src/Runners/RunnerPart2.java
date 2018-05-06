package Runners;

import java.io.IOException;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import solution.Solution;

public class RunnerPart2 {
    /**
     * 演示脚本-2
     * 实验任务二：利用启发式搜索，求解随机5*5拼图（24-数码问题）
     * 注意：运行前要修改节点维数，将JigsawNode类中的dimension改为5
     * 要求：不修改脚本内容，程序能够运行，且得出预期结果
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // 检查节点维数是否为5
        if (JigsawNode.getDimension() != 5) {
            System.out.print("节点维数不正确，请将JigsawNode类的维数dimension改为5");
            return;
        }

        // 生成目标状态对象destNode: {25,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0};
        JigsawNode destNode = new JigsawNode(new int[]{25,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0});

        // 生成随机初始状态对象startNode：将目标状态打散，生成可解的随机初始状态
        JigsawNode startNode = Solution.scatter(destNode, 1000);
        //JigsawNode startNode = new JigsawNode(new int[]{22,22,15,4,9,12,13,8,6,10,14,21,19,1,5,3,11,2,16,23,18,20,0,7,17,24});

        // 生成jigsaw对象：设置初始状态节点startNode和目标状态节点destNode
        Jigsaw jigsaw = new Solution();

        // 执行启发式搜索示例算法
        System.out.println(jigsaw.ASearch(startNode, destNode));
    }

}
