//package az.pw.gui;
//
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.Rectangle;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//import az.pw.Graph;
//
//import com.mxgraph.layout.mxCircleLayout;
//import com.mxgraph.layout.mxCompactTreeLayout;
//import com.mxgraph.layout.mxFastOrganicLayout;
//import com.mxgraph.layout.mxGraphLayout;
//import com.mxgraph.layout.mxOrganicLayout;
//import com.mxgraph.layout.mxStackLayout;
//import com.mxgraph.model.mxGraphModel;
//import com.mxgraph.swing.mxGraphComponent;
//import com.mxgraph.util.mxConstants;
//import com.mxgraph.util.mxRectangle;
//import com.mxgraph.view.mxGraph;
//
//public class UserInterface extends JFrame {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -5580932666288000054L;
//
//	private String[] colors = {"red", "yellow", "blue", "pink", "green", "white"};
//	
//	public UserInterface(Graph graphData, int[] properColors){
//		//setSize(new Dimension(340,250));
//		this.setBounds(100, 100, 671, 510);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
////		JPanel panel = new JPanel();
////		JButton loadInput = new JButton("Load graph");
////		loadInput.addActionListener(new ActionListener(){
////
////			@Override
////			public void actionPerformed(ActionEvent arg0) {
////				openLoadFileWindow();				
////			}
////			
////		});
//	//	this.getContentPane().add(loadInput, BorderLayout.WEST);
//		
//		drawGraph(graphData, properColors);
//
//	}
//	
//	private void openLoadFileWindow(){
//		
//	}
//	
//	private void drawGraph(Graph graphData, int[] properColors){
//		mxGraph graph = new mxGraph();
//		Object parent = graph.getDefaultParent();
//	// graph.setMaximumGraphBounds(new mxRectangle(0, 0, 551, 551));
//	 
//		graph.getModel().beginUpdate();
//		graph.setCellsLocked(true);
//		List<Object> vertices = new ArrayList<Object>();
//		try {	
//			Random generator = new Random();
//			for(int i = 0; i< graphData.getVertices().length; i++){
//				Object v = graph.insertVertex(parent, null, graphData.getVertices()[i], generator.nextInt(600), generator.nextInt(200), 30, 30,"fillColor="+colors[properColors[i]]);
//				vertices.add(v);
//			}
//			boolean[][] adjancencyMatrix = graphData.getAdjacencyMatrix();
//			for(int i =0; i<adjancencyMatrix.length; i++){
//				for(int j = 0; j < i+1; j++){
//					if(adjancencyMatrix[i][j]){
//						graph.insertEdge(parent, null, null, vertices.get(i), vertices.get(j),"startArrow=none;endArrow=none");
//					}
//				}
//			}		
//			mxOrganicLayout organicLayout = new mxOrganicLayout(graph);
//			//organicLayout.setVertexLocation(vertices.get(3), 50, 50);
//			//organicLayout.setTriesPerCell(100);
//			organicLayout.execute(parent);			
//		} finally{
//			graph.getModel().endUpdate();
//		}
//		
//		mxGraphComponent graphComponent = new mxGraphComponent(graph);
//		graphComponent.scrollCellToVisible(vertices.get(vertices.size()-1), true);
//		this.getContentPane().add(graphComponent, BorderLayout.CENTER);
//	}
//}
