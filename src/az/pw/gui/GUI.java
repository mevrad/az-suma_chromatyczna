package az.pw.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;

import az.pw.greedycoloring.GreedyColoring;
import az.pw.inputparser.ParseInputGraph;
import az.pw.secondalgorithm.SecondAlgorithm;

public class GUI extends JFrame {

	String styleSheet = "node {" + "   fill-color: black;" + "}"
			+ "node.marked {" + "   fill-color: red;" + "}";;

	String styleSheet1 = "node {" + "   fill-color: red;" + "}"
			+ "node.marked {" + "   fill-color: red;" + "}";;
	JLabel filePath;

	public GUI() {
		setVisible(true);
		setBounds(100, 100, 671, 510);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 0));

		JPanel graphInput = prepareGraphInput();
		panel.add(graphInput);

		this.getContentPane().add(panel, BorderLayout.WEST);
		this.validate();
		this.repaint();
	}

	private JPanel prepareGraphInput() {

		JPanel panel = new JPanel();
		JButton loadInput = new JButton("Load graph");
		loadInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openFile();
			}

		});

		filePath = new JLabel("Please choose file");
		panel.add(loadInput);
		panel.add(filePath);
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JPanel panel1 = new JPanel();
		JLabel inputState = new JLabel("Input accepted: ");
		JLabel error = new JLabel("Error: ");

		panel1.add(inputState);
		panel1.add(error);
		panel1.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JPanel mainPanel = new JPanel();
		BoxLayout mainLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(mainLayout);
		mainPanel.setBorder(BorderFactory.createTitledBorder("Graph input"));

		mainPanel.add(panel);
		mainPanel.add(panel1);
		// mainPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		return mainPanel;
	}

	private void openFile() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Text file", "txt");
		fileChooser.addChoosableFileFilter(filter);
		if (fileChooser.showDialog(null, "Open file") == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getAbsoluteFile().toString();
			parseInputFile(path);
			filePath.setText(fileChooser.getSelectedFile().getName());
		}
	}

	private void parseInputFile(String file) {
		ParseInputGraph pig = new ParseInputGraph(file);
		az.pw.Graph graph = pig.getGraph();
		startGreedyColoring(graph);
		// startSecondAlgorithm(graph);
	}

	private void startGreedyColoring(az.pw.Graph graph) {
		GreedyColoring gc = new GreedyColoring(graph);
		int[] properColors = gc.getProperColors();
		// draw graph
		drawGraph();
	}

	private void startSecondAlgorithm(az.pw.Graph graph) {
		new SecondAlgorithm(graph);
		// show result
	}

	private void drawGraph() {
		Graph graph = new SingleGraph("Single graph");
		graph.addAttribute("ui.stylesheet", styleSheet);
		graph.setAutoCreate(true);
		graph.setStrict(false);
		// graph.display();

		graph.addNode("T").setAttribute("ui.class", "marked");
		graph.addEdge("AB", "A", "B");
		graph.addEdge("BC", "B", "C");
		graph.addEdge("CA", "C", "A");
		graph.addEdge("AD", "A", "D");
		graph.addEdge("DE", "D", "E");
		graph.addEdge("DF", "D", "F");
		graph.addEdge("EF", "E", "F");

		for (Node node : graph) {
			node.addAttribute("ui.label", node.getId());
		}

		Viewer viewer = graph.display();
		// Viewer viewer = new Viewer(graph,
		// Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		// org.graphstream.ui.swingViewer.View view =
		// viewer.addDefaultView(false);
		View view = viewer.getDefaultView();

		this.getContentPane().add(view);
		

	}

}
