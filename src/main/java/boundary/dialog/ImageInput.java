package boundary.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import boundary.theme.ModernButton;
import boundary.theme.ModernLabel;

public class ImageInput extends JPanel
{
	private JButton uploadButton;
	private ArrayList<File> images;
	
	private GridBagConstraints gridBag;
	private JPanel imagePanel;
	private JPanel filePanel;
	private GridBagConstraints imageGridBag;
	
	public ImageInput()
	{
		super();
		this.setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setSize(300, 500);
		setMinimumSize(new Dimension(300, 300));
		setPreferredSize(getSize());
		setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
		filePanel = new JPanel(new GridBagLayout());
		gridBag = new GridBagConstraints();
		gridBag.insets = new Insets(5,5,5,5);
		gridBag.anchor = gridBag.EAST;
		gridBag.gridx = gridBag.gridy = 0;
		
		this.images = new ArrayList<>(5);
		initializeFields();
	}
	
	private void initializeFields()
	{
		imagePanel = new JPanel();
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
		
		uploadButton = ModernButton.createNewButtonPainted("Carica immagine", 150, 40);
		uploadButton.addActionListener(e -> chooseImages());
		
		this.add(imagePanel);
		this.add(uploadButton);
	}
	
	private void chooseImages() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileFilter(new FileNameExtensionFilter("Immagini JPG/PNG", "jpg", "jpeg", "png"));

        carica(chooser);
    }

	private void carica(JFileChooser chooser) 
	{
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) //Immagini selezionate
		{
            File[] files = chooser.getSelectedFiles();
            if (files.length > 5 || images.size() >= 5 ) {
                JOptionPane.showMessageDialog(this, "Puoi caricare al massimo 5 immagini", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            for (File f : files) 
            {
                if (f.length() > 10 * 1024 * 1024) {
                	JOptionPane.showMessageDialog(this, "Ogni immagine deve essere ≤ 10MB", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                addImmage(f);
            }
            refreshImagePanel();
        }
	}

    private void refreshImagePanel() {
        imagePanel.removeAll();
        imageGridBag = new GridBagConstraints();
        imageGridBag.gridx = 0; 
        imageGridBag.gridy = 0; 
        imageGridBag.anchor = GridBagConstraints.WEST;
        for (File f : images) {
        	createImageCard(f);
        }
        imagePanel.revalidate();
        imagePanel.repaint();
    }

	private void createImageCard(File f) 
	{
		JLabel imageLabel = ModernLabel.createLabel(f.getName(), Color.BLACK);
		JButton cancelUploadButton = ModernButton.createNewButtonPainted("Annulla", Color.RED, Color.WHITE, 100, 30);
		JPanel imageCard = new JPanel(new FlowLayout(FlowLayout.LEFT));
		imageCard.setBackground(Color.WHITE);
		cancelUploadButton.addActionListener(e -> annullaCaricamento(f, imageCard));
		
		imageCard.setPreferredSize(new Dimension(300, 40));
		imageCard.setMaximumSize(imageCard.getPreferredSize());
		
		imageCard.add(imageLabel);
		imageCard.add(cancelUploadButton);
		imagePanel.add(imageCard, imageGridBag);
		imageGridBag.gridy++;
	}

    private void annullaCaricamento(File f, JPanel card) {
		this.images.remove(f);
		imagePanel.remove(card);
		imageGridBag.gridy--;
		this.refreshImagePanel();
	}
    
    private void addImmage (File file)	//Aggiunta di un immagine 
    {
    	if(images.size() < 5)
    		images.add(file);
    	else
    		JOptionPane.showMessageDialog(this, "Puoi caricare al massimo 5 immagini", 
    				"Errore", JOptionPane.ERROR_MESSAGE);
    }
	public ArrayList<File> getImages() {
        return images;
    }
}
