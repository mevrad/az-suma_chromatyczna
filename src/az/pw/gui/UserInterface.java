package az.pw.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import az.pw.Graph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.layout.mxStackLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

public class UserInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5580932666288000054L;

	private String[] colors = {"red", "yellow", "blue", "pink", "green", "white"};
	
	public UserInterface(Graph graphData, int[] properColors){
		setSize(new Dimension(340,250));
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		
		
	
	    
		graph.getModel().beginUpdate();
//		graph.setAllowDanglingEdges(false);
		graph.setCellsLocked(true);
		List<Object> vertices = new ArrayList<Object>();
		try {
			
			
			for(int i = 0; i< graphData.getVertices().length; i++){
				Object v = graph.insertVertex(parent, null, graphData.getVertices()[i], 0, 0, 30, 30,"fillColor="+colors[properColors[i]]);
				vertices.add(v);
			}
			
			boolean[][] adjancencyMatrix = graphData.getAdjacencyMatrix();
			for(int i =0; i<adjancencyMatrix.length; i++){
				for(int j = 0; j < i+1; j++){
					if(adjancencyMatrix[i][j]){
						graph.insertEdge(parent, null, null, vertices.get(i), vertices.get(j),"startArrow=none;endArrow=none");
					}
				}
			}
			
			//change color
			//graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[]{v1});
			
//			Object v = ((mxGraphModel) graph.getModel()).getCell("1");
//			graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "yellow", 	new Object[]{v});
//			//((mxGraphModel)graph.getModel()).getCells();
			
			mxOrganicLayout organicLayout = new mxOrganicLayout(graph);
//			Object cell = graph.getSelectionCell();
//			if (cell == null || graph.getModel().getChildCount(cell) == 0){
//				cell = graph.getDefaultParent();
//			}
//			organicLayout.setRadiusScaleFactor(1.0);  
//			organicLayout.setApproxNodeDimensions(false);
//			organicLayout.setEdgeCrossingCostFactor(8000);
//			organicLayout.setNodeDistributionCostFactor(organicLayout.getNodeDistributionCostFactor() * 5);
//			organicLayout.setEdgeDistanceCostFactor(organicLayout.getEdgeDistanceCostFactor() * 5);
//			organicLayout.setEdgeLengthCostFactor(organicLayout.getEdgeLengthCostFactor() / 10);
//			//organicLayout.setVertexLocation(vertices.get(3), 0, 0);
//			//organicLayout.setAverageNodeArea(30000);
			organicLayout.execute(parent);
			
		} finally{
			graph.getModel().endUpdate();
		}
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.scrollCellToVisible(vertices.get(vertices.size()-1), true);
		this.getContentPane().add(graphComponent, BorderLayout.CENTER);
		//getContentPane().add(graphComponent);
	}
}
