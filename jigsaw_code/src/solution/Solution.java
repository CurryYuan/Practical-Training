package solution;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {
	double T=30;
	/**
	 * 拼图构造函数
	 */
	public Solution() {
	}

	/**
	 * 拼图构造函数
	 * @param bNode - 初始状态节点
	 * @param eNode - 目标状态节点
	 */
	public Solution(JigsawNode bNode, JigsawNode eNode) {
		super(bNode, eNode);
	}

	/**
	 *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
	 * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
	 * @return 搜索成功时为true,失败为false
	 */
	public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
		Queue<JigsawNode> temp=new LinkedList<JigsawNode>();
		Vector<JigsawNode> isVisited=new Vector<JigsawNode>();
		temp.add(bNode);
		isVisited.add(bNode);
		while(!temp.isEmpty()) {

			if(temp.peek().equals(eNode)) {
				currentJNode=temp.peek();
				getPath();
				System.out.println(getSearchedNodesNum());
				System.out.println(getSolutionPath());
				return true;
			}
			for(int i=0;i<4;++i) {
				JigsawNode aJigsawNode=new JigsawNode(temp.peek());     //java引用传递
				if(aJigsawNode.move(i)) {				
					if(!isVisited.contains(aJigsawNode)) {
						temp.add(aJigsawNode);
						isVisited.add(aJigsawNode);
					}
				}

			}
			temp.poll();
		}
		return false;
	}


	/**
	 *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
	 * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
	 * @param jNode - 要计算代价估计值的节点
	 */
	public void estimateValue(JigsawNode jNode) {
		int s = 0; // 后续节点不正确的数码个数
		int dimension = JigsawNode.getDimension();
		for (int index = 1; index < dimension * dimension; index++) {
			if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
				s++;
			}
		}
		
		
		jNode.setEstimatedValue(s+distance(jNode));
		//jNode.setEstimatedValue(s+SA(jNode));

	}
	
	private int distance(JigsawNode a) {
		int dis=0;
		int dimension=JigsawNode.getDimension();
		int[] arr=a.getNodesState();

		for(int i=1;i<dimension*dimension+1;++i) {
			if(arr[i]==0) {
				continue;
			}
			int position=findIndex(arr[i]);

			int x1=(i-1)/5;
			int y1=(i-1)%5;
			int x2=(position-1)/5;
			int y2=(position-1)%5;
			double d=Math.abs(x1-x2) + Math.abs(y1-y2);
			dis+=Math.floor(d);
		}
		return dis;
	}
	
	
	private int findIndex(int val) {
		int[] arr1=endJNode.getNodesState();
		for(int i=1;i<arr1.length;++i) {
			if(val==arr1[i]) {
				return i;
			}
		}
		return -1;
	}
	
	private int SA(JigsawNode jNode) {
		//System.out.println(distance(jNode));
		double t=T;
		Queue<JigsawNode> temp=new LinkedList<JigsawNode>();
		temp.add(jNode);
		//while(true) {
			for(int j=0;j<20;++j) {
				JigsawNode temp1=new JigsawNode(temp.peek());
				for(int i=0;i<4;++i) {
					JigsawNode aJigsawNode=new JigsawNode(temp.peek());     //java引用传递
					if(aJigsawNode.move(i)) {
						int d=distance(aJigsawNode)-distance(temp1);
						if(d<0) {
							temp.add(aJigsawNode);
						}
						else {
							//System.out.println(Math.exp(-d/t));
							//if(Math.exp(-d/t)>Math.random()) {
								temp.add(aJigsawNode);
								//System.out.println("1");
							//}

						}
					}

				}
				if(temp.size()>1) {
					temp.poll();
				}
				else {
					//System.out.println(distance(temp1));
					return distance(temp1);
				}

			}
			//System.out.println(t);
			/*if(t<20) {
				break;
			}
			//t*=0.8;
		}*/
		
		return distance(temp.peek());

		
	}
}
